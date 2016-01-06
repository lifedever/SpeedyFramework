speedy-base详细介绍
----

此模块为Spring boot基础配置，打包方式为`pom`

# 功能说明

此模块亦为SpeedyFramework的核心基础模块，如果希望搭建一个只有Spring Boot基础功能的框架，只需要添加此模块依赖即可：
```xml
<dependency>
  <groupId>io.github.gefangshuai</groupId>
  <artifactId>speedy-ext</artifactId>
  <version>${speedy-version}</version>
</dependency>
```
不需要在去繁琐依赖各个`spring-boot-starter-*`，默认集成的功能查看[pom.xml](https://github.com/gefangshuai/SpeedyFramework/blob/master/speedy-base/pom.xml)可知。

值得注意的是，`speedy-base`默认去掉了`spring-boot-starter-logging`而集成了使用更加广泛灵活的`log4j`，只需要在项目的`/resources`目录下添加`log4j.properties`文件，并进行相关配置即可。

同时模板引擎默认继承了灵活高效的`Freemarker`。

# 基础扩展

如果不满足自己的需求，想对其中的个别依赖进行修改，完全可以交由maven完成，这里举一个简单例子：

![举个栗子](../lizi.jpg)