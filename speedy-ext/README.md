speedy-ext详细介绍
----
此模块为`SpeedyFramework`功能扩展模块，拟补`speedy-base`中的功能不足，旨在将`SpeedyFramework`开发成为一个真正意义上的“快速开发平台”。

# speedy-ext功能介绍

`speedy-ext`提供的功能如下：
- [JPA功能扩展](#user-content-jpa功能扩展)
- Shiro集成及扩展
- 页面动态菜单控制支持
- Flash Message支持
- EHCache支持
- 基本WebMvcConfiguration配置
- Jsonp支持

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
