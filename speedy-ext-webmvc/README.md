speedy-ext-webmvc详细介绍
----
此模块定义了与MVC相关的各项功能

主要功能有：
- [页面动态菜单控制支持](#user-content-页面动态菜单控制支持)
- [FlashMessage支持](#user-content-flashmessage支持)
- [EHCache支持](#user-content-ehcache支持)
- [基本WebMvcConfiguration配置](#user-content-基本webmvcconfiguration配置)
- [Jsonp支持](#user-content-jsonp支持)

## 页面动态菜单控制支持
通过增加一个注解`@Menu`并赋予一个**唯一的**菜单标识，页面通过参数`nav_menu`得当前请求对应的菜单，并作相应处理来控制当前菜单高亮显示。

`@Menu`注解可以作用在类(Controller)上或者方法(Action Method)上。`Method Annotation`的优先级大于`Class Annotation`。如果加在了`Class`而没有在`Method`上加，则取值为`Class Annotation`上的值。如：
```java
@Menu("admin-restaurants")
@Controller
@RequestMapping("/admin/restaurants")
static class AdminRestaurantController {
    @Menu("admin-restaurants-index")
    @RequestMapping
    static String index(Model model){
        //……
    }
    @RequestMapping("/edit")
    static String edit(Model model){
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
static class AdminRestaurantController {
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
核心的控制处理在[NavigationHandlerInterceptor](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-ext-webmvc/src/main/java/io/github/gefangshuai/ext/interceptor/NavigationHandlerInterceptor.java)中，可以直接查看。

> 如果Controller没有添加`@Menu`，则默认的菜单标识为`index`，即`${nav_menu}`的取值为`index`

## FlashMessage支持
FlashMessage([FlashMessageUtils](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-ext-webmvc/src/main/java/io/github/gefangshuai/ext/utils/FlashMessageUtils.java))封装了Spring MVC的`RedirectAttributes.addFlashAttribute(key, value)`功能，提供三个静态方法，用于重定向`redirect`的传值，分别是：
- FlashMessageUtils.success(RedirectAttributes redirectAttributes, String message): key为`flash_message_success`, 发送成功消息到前台
- FlashMessageUtils.warning(RedirectAttributes redirectAttributes, String message): key为`flash_message_warning`, 发送警告消息到前台
- FlashMessageUtils.error(RedirectAttributes redirectAttributes, String message): key为`flash_message_error`, 发送错误消息到前台

前台分别通过`${flash_message_success}`、`${flash_message_warning}`、`${flash_message_error}`可以取到`message`值，并做相应处理。

## EHCache支持
`speedy-ext-webmvc`默认已开启对`EHCache`支持，在Maven工程的`resources`目录下添加`ehcache.xml`并进行个性化配置即可。如果想要做更多的控制，可重载[EHCacheConfiguration](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-ext-webmvc/src/main/java/io/github/gefangshuai/ext/spring/EHCacheConfiguration.java)

## 基本WebMvcConfiguration配置
请参见源码[WebMVCConfigurerAdapter](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-ext-webmvc/src/main/java/io/github/gefangshuai/ext/spring/WebMVCConfigurerAdapter.java)

- 开启国际化支持
- 开启多文件上传支持

## Jsonp支持

`speedy-ext-webmvc`已开启对Jsonp的支持，自己不需要再额外配置。
