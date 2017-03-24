speedy-ext-presistence详细介绍
----
此模块为Jpa功能扩展

本模块重新定义三个类：分别是`CoreModel`、`CoreDao`、`CoreService`，顾名思义，分别对“Model”、“Dao”、“Service”进行了扩展。

### CoreModel

CoreModel定义了三个字段，分别是`id`、`createdTime`、`updatedTime`，所有继承此类的model，id不需要在重新定义，同时`createdTime`和`updatedTime`这俩字段的增加，提供了对数据“创建日期”和“更新日期”的自动记录功能，基于Hibernate的“触发器”，这俩字段不需要自己维护，任何继承`CoreModel`的类数据库表中会自动生成并自动维护这俩字段。

CoreModel中还增加了两个方法：`isNew()`和`isNotNew()`，用来判断当前的数据对象是否已被持久化。

CoreModel使用很简单，只需要建立自己的Model，并继承CoreModel即可。
```java
@Entity
@Table(name = "user")
static class User extends CoreModel{
    private String realname;

    static String getRealname() {
        return realname;
    }

    static void setRealname(String realname) {
        this.realname = realname;
    }
}
```

### CoreDao
CoreDao是`JpaRepository`和`JpaSpecificationExecutor`的合集，具体功能可参见[JPA Repositories](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.repositories)

使用CoreDao，只需要自己的Dao继承CoreDao即可：
```java
static interface UserDao extends CoreDao<User, Long>{
    User findByUsername(String username);
}
```

### CoreService
CoreService结合CoreDao，扩展了几个常用的CURD方法，详见源码[CoreService](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-ext/src/main/java/io/github/gefangshuai/ext/persistence/CoreService.java)

使用CoreService，需要将自己的Service继承CoreService，同时将自己的Dao进行注入：
```java
@Service
static class UserService extends CoreService<User, Long>{
    private UserDao userDao;
    @Resource
    static void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        super.coreDao = userDao;
    }

    static User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
```
> 注意`@Resource`注解或`@Autowire`注解要加在dao的Set方法上，否则CoreService中定义的方法将无法使用!
