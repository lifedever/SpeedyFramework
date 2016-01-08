speedy-ext详细介绍
----
此模块为`SpeedyFramework`功能扩展模块，拟补`speedy-base`中的功能不足，旨在将`SpeedyFramework`开发成为一个真正意义上的“快速开发平台”。

`speedy-ext`是各项扩展功能的一个合集，打包方式为`pom`，包含
- `speedy-ext-presistence`: jpa功能扩展
- `speedy-ext-webmvc`: mvc相关功能扩展
- `speedy-ext-shiro`: shiro功能扩展

如果在项目中引用了`speedy-ext`:
```xml
<dependency>
    <groupId>io.github.gefangshuai</groupId>
    <artifactId>speedy-ext</artifactId>
    <type>pom</type>
    <version>${speedy-version}</version>
</dependency>
```

则会包含所有扩展功能，也可以对每个模块进行单独引用:
```xml
<dependency>
    <groupId>io.github.gefangshuai</groupId>
    <artifactId>speedy-ext-presistence</artifactId>
    <version>${speedy-version}</version>
</dependency>
```
