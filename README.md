Speedy Framework
---

基于Spring Boot 的规范化平台开发框架

# 框架介绍

- speedy-base：spring boot基本配置依赖信息，最小化配置
- speedy-ext： 继承了`speedy-base`功能，并扩展了其他功能，详见[speedy-ext](https://github.com/gefangshuai/SpeedyFramework/tree/master/speedy-ext)
- speedy-utils：工具类库及自定义工具类的合集`speedy-base`默认依赖此模块
- speedy-sample：一个注册登录示例

每个模块详细功能请到具体模块目录下查看。

# 准备工作

首先将框架clone到本地

```bash
git clone git@github.com:gefangshuai/SpeedyFramework.git
```

然后将框架安装到本地仓库

```bash
mvn clean compile install
```

over!

# 快速入门

`SpeedyFramework`默认提供两种模式方便进行快速框架搭建：**基础模式**和**偷懒模式**

## 基础模式

所谓基础模式，即只有Spring Boot最基础的功能，其他扩展信息或框架，需要自己集成，如需用到Shiro做权限管理，需要自己进行对Shiro的集成。

使用基础模式，只需要添加对`speedy-base`的依赖即可：

```xml
<dependency>
    <groupId>io.github.gefangshuai</groupId>
    <artifactId>speedy-base</artifactId>
    <type>pom</type>
    <version>${speedy-version}</version>
</dependency>
```

## 偷懒模式

所谓偷懒模式，即除了集成Spring Boot基础功能以外，还包含其他第三方框架的集成，属于“一站式服务”，使用此模式后，对于一个要求不是很苛刻的项目，基本不需要再集成其他框架。

使用偷懒模式，只需要添加对`speedy-ext`的依赖即可：

```xml
<dependency>
    <groupId>io.github.gefangshuai</groupId>
    <artifactId>speedy-ext</artifactId>
    <type>pom</type>
    <version>${speedy-version}</version>
</dependency>
```

至于此模式已集成的各种功能及使用方法，请参见[speedy-ext](https://github.com/gefangshuai/SpeedyFramework/tree/master/speedy-ext)

两种模式各有优缺点，自行选择。建议使用`speedy-ext`，可以快速搭建开发平台，本框架后期也会对`speedy-ext`进行更强大的扩展。
