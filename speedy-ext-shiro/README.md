speedy-ext-shiro详细介绍
----
此模块为Shiro扩展

`为什么是shiro而不是Spring Security呢？` 因为Shiro轻量、灵活、扩展性高。

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

> 注意：由于spring在4.2.x之后对静态资源的加载做了微调，请使用spring-boot 1.3.x版本。如果是spring-boot 1.2.7版本，则无法直接使用`shiro.ftl`，请讲`shiro.ftl`文件直接拷贝到自己的web项目的`/resources/templates`根目录下。
