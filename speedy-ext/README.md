speedy-ext详细介绍
----
此模块为`SpeedyFramework`功能扩展模块，拟补`speedy-base`中的功能不足，旨在将`SpeedyFramework`开发成为一个真正意义上的“快速开发平台”。

# speedy-ext功能介绍

`speedy-ext`提供的功能如下：
- [JPA功能扩展](#user-content-jpa功能扩展)
- [Shiro集成及扩展](#user-content-shiro集成及扩展)
- [页面动态菜单控制支持](#user-content-页面动态菜单控制支持)
- [FlashMessage支持](#user-content-flashmessage支持)
- [EHCache支持](#user-content-ehcache支持)
- [基本WebMvcConfiguration配置](#user-content-基本webmvcconfiguration配置)
- [Jsonp支持](#user-content-jsonp支持)

下面对每个功能进行详细说明。

## JPA功能扩展

本功能重新定义三个类：分别是`CoreModel`、`CoreDao`、`CoreService`，顾名思义，分别对“Model”、“Dao”、“Service”进行了扩展。

### CoreModel

CoreModel定义了三个字段，分别是`id`、`createdTime`、`updatedTime`，所有继承此类的model，id不需要在重新定义，同时`createdTime`和`updatedTime`这俩字段的增加，提供了对数据“创建日期”和“更新日期”的自动记录功能，基于Hibernate的“触发器”，这俩字段不需要自己维护，任何继承`CoreModel`的类数据库表中会自动生成并自动维护这俩字段。

CoreModel中还增加了两个方法：`isNew()`和`isNotNew()`，用来判断当前的数据对象是否已被持久化。

CoreModel使用很简单，只需要建立自己的Model，并继承CoreModel即可。
```java
@Entity
@Table(name = "user")
public class User extends CoreModel{
    private String realname;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
```

### CoreDao
CoreDao是`JpaRepository`和`JpaSpecificationExecutor`的合集，具体功能可参见[JPA Repositories](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.repositories)

使用CoreDao，只需要自己的Dao继承CoreDao即可：
```java
public interface UserDao extends CoreDao<User, Long>{
    User findByUsername(String username);
}
```

### CoreService
CoreService结合CoreDao，扩展了几个常用的CURD方法，详见源码[CoreService](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-ext/src/main/java/io/github/gefangshuai/ext/persistence/CoreService.java)

使用CoreService，需要将自己的Service继承CoreService，同时将自己的Dao进行注入：
```java
@Service
public class UserService extends CoreService<User, Long>{
    private UserDao userDao;
    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        super.coreDao = userDao;
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
```
> 注意`@Resource`注解或`@Autowire`注解要加在dao的Set方法上，否则CoreService中定义的方法将无法使用!

## Shiro集成及扩展
`speedy-ext`集成了对Shiro的扩展，`为什么是shiro而不是Spring Security呢？` 因为Shiro轻量、灵活、扩展性高。

主要功能有：
- Shiro基础框架集成。
- Shiro的`AuthorizingRealm`和`FormAuthenticationFilter`功能进行抽象，完成了大部分的配置。
- 增加UserModel超类，提供对登录名、密码、盐值的生成及保存。
- 增加ShiroUser类，用于保存登录用户的信息。
- 修复官方Shiro注解不支持对类注解的bug(这个Bug这么长时间了，官方竟然不修复……)。
- 定义了一套Shiro的Freemarker类库。

### 使用方法：

关于Shiro所有的配置，`speedy-ext`已基本完成，使用的时候，只需要关注`ShiroExtConfig`接口即可。

首先在项目中加入一个`Configuration`类，并继承`ShiroExtConfiguration`。为了能够在此类中使用`ApplicationContext`，我们还需要实现`ApplicationContextAware`接口。

然后完成`ShiroExtConfig`中的各个方法即可，具体参见[ShiroConfiguration](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-sample/src/main/java/io/github/gefangshuai/demo/config/ShiroConfiguration.java)

### 方法说明

`getShiroExtRealm()`方法主要是返回一个自定义的`ShiroExtRealm`类，实现`ShiroExtRealm`中的三个抽象方法
- getUserByUsername(String username)：根据用户名查询用户
- getRolesByUsername(String username)：根据用户名查询当前用户的角色列表
- getPermissionsByUsername(String username)：根据用户名查询当前用户的权限列表

`getShiroServerFormAuthenticationFilter()`方法主要是返回一个自定义的`ShiroExtFormAuthenticationFilter`，实现抽象方法`doSomethingOnLoginSuccess()`，顾名思义：登陆成功后，做的一些处理。

`getFilterChainDefinitionMap()`方法定义了Shiro的Url权限过滤链。

### 其它功能

#### UserModel的使用

UserModel中包含三个字段`username`、`password`、`salt`，分别代表“登陆名”、“密码”、“密码加密盐值”，此Model继承自`CoreModel`，故已包含`CoreModel`中的所有功能。

使用此类，只需继承此类即可：
```java
@Entity
@Table(name = "user")
public class User extends UserModel{
    private String realname;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
```

在处理注册用户的数据保存时，可以通过UserModel自动产生加密的密码及盐值，通过调用`user.encryptUser()`方法。详细参见[AccountController.saveUser](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-sample/src/main/java/io/github/gefangshuai/demo/controller/AccountController.java#L31)

### Shiro的Freemarker类库shiro.ftl使用

Shiro官方并没有对Freemaker提供支持，这里做了一个简单的支持，可以在项目中直接引用`shiro.ftl`，需要注意的是需要在项目的`application.preperties`配置文件中设置：
```bash
spring.freemarker.prefer-file-system-access=false
```
更多可参考：[Spring Boot 将Freemarker打包到其他jar并在项目中引用的简单方法](http://www.jianshu.com/p/ba2365bc7324)

## 页面动态菜单控制支持
通过增加一个注解`@Menu`并赋予一个**唯一的**菜单标识，页面通过参数`nav_menu`得当前请求对应的菜单，并作相应处理来控制当前菜单高亮显示。

`@Menu`注解可以作用在类(Controller)上或者方法(Action Method)上。`Method Annotation`的优先级大于`Class Annotation`。如果加在了`Class`而没有在`Method`上加，则取值为`Class Annotation`上的值。如：
```java
@Menu("admin-restaurants")
@Controller
@RequestMapping("/admin/restaurants")
public class AdminRestaurantController {
    @Menu("admin-restaurants-index")
    @RequestMapping
    public String index(Model model){
        //……
    }
    @RequestMapping("/edit")
    public String edit(Model model){
      //……
    }
}
```
当我们访问`/admin/restaurants`时，`${nav_menu}`值为`admin-restaurants-index`；
当我们访问`/admin/restaurants/edit`时，`${nav_menu}`值为`admin-restaurants`。

**下面请看一个示例：**

假如现在开发一个“商店列表”模块，点击“店铺列表”菜单，会展示所有的“店铺列表”，并且将“店铺列表”高亮显示。

首先店铺列表Controller增加`@Menu注解`
```java
@Menu("admin-restaurants")
@Controller
@RequestMapping("/admin/restaurants")
public class AdminRestaurantController {
    // ……
}
```
并赋予标识`admin-restaurants`。

然后前端页面可以直接用`${nav_menu}`获取当前展示的页面对应的菜单标识，并作出判断，动态添加菜单高亮的`class`
```html
<li <#if nav_menu?? && nav_menu?starts_with('admin-restaurants')>class="active" </#if> >
    <a href="/admin/restaurants"><i class="fa fa-home"></i> 店铺列表</a>
</li>
```
核心的控制处理在[NavigationHandlerInterceptor](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-ext/src/main/java/io/github/gefangshuai/ext/interceptor/NavigationHandlerInterceptor.java)中，可以直接查看。

> 如果Controller没有添加`@Menu`，则默认的菜单标识为`index`，即`${nav_menu}`的取值为`index`

## FlashMessage支持
FlashMessage([FlashMessageUtils](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-ext/src/main/java/io/github/gefangshuai/ext/utils/FlashMessageUtils.java))封装了Spring MVC的`RedirectAttributes.addFlashAttribute(key, value)`功能，提供三个静态方法，用于重定向`redirect`的传值，分别是：
- FlashMessageUtils.success(RedirectAttributes redirectAttributes, String message): key为`flash_message_success`, 发送成功消息到前台
- FlashMessageUtils.warning(RedirectAttributes redirectAttributes, String message): key为`flash_message_warning`, 发送警告消息到前台
- FlashMessageUtils.error(RedirectAttributes redirectAttributes, String message): key为`flash_message_error`, 发送错误消息到前台

前台分别通过`${flash_message_success}`、`${flash_message_warning}`、`${flash_message_error}`可以取到`message`值，并做相应处理。

## EHCache支持
`speedy-ext`默认已开启对`EHCache`支持，在Maven工程的`resources`目录下添加`ehcache.xml`并进行个性化配置即可。如果想要做更多的控制，可重载[EHCacheConfiguration](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-ext/src/main/java/io/github/gefangshuai/ext/spring/EHCacheConfiguration.java)

## 基本WebMvcConfiguration配置
请参见源码[WebMVCConfigurerAdapter](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-ext/src/main/java/io/github/gefangshuai/ext/spring/WebMVCConfigurerAdapter.java)

## Jsonp支持

`speedy-ext`已开启对Jsonp的支持，自己不需要再额外配置。
