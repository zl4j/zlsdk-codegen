## 目录

### 项目介绍

- zlsdk-codegen是一款开源的代码生成工具包。目前可以生成Entity、Mapping、Mapper、Service、Controller代码，很大程度上降低了开发人员的工作量
- 适用于使用Springboot、Mybatis进行快速构建开发。提供简洁、灵活的API，使开发人员尽量只用关注上层代码



### 开发环境

- JDK1.8
- Maven3.0+



### 快速开始

1. 在需要生成代码的项目中引入以下包：

```xml
<dependency>
    <groupId>com.github.zlcb</groupId>
    <artifactId>zlsdk-codegen</artifactId>
    <version>1.0.3</version>
    <optional>true</optional>
</dependency>
```

2、复制以下代码到主方法（main）（**建议单独创建一个类**）

```java
package com.github.zlcb.zlboot;

import com.github.zlcb.zlsdk.codegen.SmartCodeGenerator;
import com.github.zlcb.zlsdk.codegen.config.*;
import com.github.zlcb.zlsdk.codegen.database.Driver;

/**
 * @author Zhongl
 * @date 2020/05/23 18:33
 */
public class CodeGenApp {

    public static void main(String[] args) {
    	SmartCodeGenerator.run(GlobalConfig.configurer()
                .dataSource(DataSourceConfig.configurer()
                        .setDriver(Driver.MySql)
                        .setUrl("jdbc:mysql://localhost:3306/db?useSSL=false&serverTimezone=UTC")
                        .setUsername("root")
                        .setPassword("root")
                        .config())
                .config());
    }

}
```

3、修改数据源信息，启动 **main()** 方法



### 使用指南

#### 依赖包说明

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.1</version>
        </dependency>
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.13</version>
        </dependency>
```



#### 配置类说明

##### GlobalConfig

| 配置项         | 配置说明                                                     | 是否必须 | 默认值                                          |
| -------------- | ------------------------------------------------------------ | -------- | ----------------------------------------------- |
| basePackage    | 基础包名，用于生成其他类时，作为包的前缀，例如：com.github.zlcb.service | 否       | com.github.zlcb                                 |
| mappingPackage | 映射文件包名                                                 | 否       | mapping                                         |
| author         | 类文件注释中的@author                                        | 否       | CodeGen                                         |
| dataSource     | 代码生成的数据源                                             | **是**   | 默认为空，详见[DataSource](#DataSource)         |
| strategy       | 代码生成策略                                                 | 否       | 默认策略，详见[StrategyConfig](#StrategyConfig) |
| template       | 生成代码的模板（默认使用velocity）                           | 否       | 默认模板，详见[TemplateConfig](#TemplateConfig) |
| customizer     | 自定义数据入口                                               | 否       | 空                                              |

##### DataSource

| 配置项     | 说明               | 是否必须 | 默认值                                                       |
| ---------- | ------------------ | -------- | ------------------------------------------------------------ |
| driver     | 数据库驱动（枚举） | 否       | true                                                         |
| driverName | 数据库驱动名称     | 否       | com.mysql.cj.jdbc.Driver，低版本需要修改为：com.mysql.jdbc.Driver |
| url        | 数据库连接地址     | 是       | 空                                                           |
| username   | 数据库用户名       | 是       | true                                                         |
| password   | 数据库用户密码     | 否       | 空                                                           |

##### StrategyConfig

| 配置项             | 说明                                                         | 是否必须 | 默认值 |
| ------------------ | ------------------------------------------------------------ | -------- | ------ |
| generateBase       | 是否生成基类，包含：IMapper、IService、BaseService           | 否       | false   |
| generateEntity     | 是否生成实例类                                               | 否       | true   |
| generateMapping    | 是否生成数据映射文件                                         | 否       | true   |
| generateMapper     | 是否生成数据访问类                                           | 否       | true   |
| generateService    | 是否生成服务接口和服务实现类                                 | 否       | true   |
| generateController | 是否生成访问控制类                                           | 否       | true   |
| allowOverrideFile  | 文件已存在时，是否允许直接覆盖旧文件                         | 否       | false  |
| allowRepeatFile    | 是否允许生成重复文件，在不允许覆盖旧文件的情况下生效，以时间戳为后缀的方式生成新文件 | 否       | true   |
| tablePrefix        | 表名前缀，生成类名时，忽略指定的表前缀                       | 否       | 空     |
| fixedTableNames    | 指定需要生成的表                                             | 否       | 空     |
| ignoreTableNames   | 指定需要忽略的表，在未指定需要生成表的情况下生效             | 否       | 空     |

##### TemplateConfig

| 配置项      | 说明                                | 是否必须 | 默认值                       |
| ----------- | ----------------------------------- | -------- | ---------------------------- |
| iMapper     | IMapper接口模板，暂时不允许自定义   | 否       | /codegen/imapper.java.vm     |
| iService    | IService接口模板，暂时不允许自定义  | 否       | /codegen/iservice.java.vm    |
| baseService | BaseService类模板，暂时不允许自定义 | 否       | /codegen/baseService.java.vm |
| r           | R类模板，暂时不允许自定义           | 否       | /codegen/r.java.vm           |
| mapping     | 数据映射文件模板，可自定义          | 否       | /codegen/mapping.xml.vm      |
| entity      | 实体类文件模板，可自定义            | 否       | /codegen/entity.java.vm      |
| mapper      | 数据访问类文件模板，可自定义        | 否       | /codegen/mapper.java.vm      |
| service     | 服务接口文件模板，可自定义          | 否       | /codegen/service.java.vm     |
| serviceImpl | 服务实现类文件模板，可自定义        | 否       | /codegen/serviceImpl.java.vm |
| controller  | 访问控制类文件模板，可自定义        | 否       | /codegen/controller.java.vm  |

##### PackageConfig（内置）

| 配置项      | 说明                              | 值（根据basePackage解析来）                             |
| ----------- | --------------------------------- | ------------------------------------------------------- |
| iMapper     | IMapper接口模板，暂时不允许自定义 | ${basePackage}.common.mapper                            |
| iService    | IService包名                      | ${basePackage}.common.service                           |
| baseService | BaseService包名                   | ${basePackage}.common.service                           |
| r           | R类包名                           | ${basePackage}.common                                   |
| entity      | 实体类包名                        | ${basePackage}.entity                                   |
| mapper      | 数据访问类包名                    | ${basePackage}.mapper                                   |
| service     | 服务接口包名                      | ${basePackage}.service<br />${basePackage}.service.impl |
| controller  | 访问控制类包名                    | ${basePackage}.controller                               |

##### OutputPathConfig（内置）

| 配置项      | 说明                | 值（根据basePackage解析来）                                  |
| ----------- | ------------------- | ------------------------------------------------------------ |
| iMapper     | IMapper生成目录     | ${user.dir}/src/main/java/${basePackage}/common/mapper       |
| iService    | IService生成目录    | ${user.dir}/src/main/java/${basePackage}/common/service      |
| baseService | BaseService生成目录 | ${user.dir}/src/main/java/${basePackage}/common/service      |
| r           | R类生成目录         | ${user.dir}/src/main/java/${basePackage}b/common             |
| entity      | 实体类生成目录      | ${user.dir}/src/main/java/${basePackage}/entity              |
| mapper      | 数据访问类生成目录  | ${user.dir}/src/main/java/${basePackage}/mapper              |
| service     | 服务接口生成目录    | ${user.dir}/src/main/java/${basePackage}/service<br />${user.dir}/src/main/java/${basePackage}/service/impl |
| controller  | 访问控制类生成目录  | ${user.dir}/src/main/java/${basePackage}/controller          |

#### 方法说明

##### run()

```java
package com.github.zlcb.zlboot;

import com.github.zlcb.zlsdk.codegen.SmartCodeGenerator;
import com.github.zlcb.zlsdk.codegen.config.*;
import com.github.zlcb.zlsdk.codegen.database.Driver;

/**
 * @author Zhongl
 * @date 2020/05/23 18:33
 */
public class CodeGenApp {

    public static void main(String[] args) {
        SmartCodeGenerator.run(GlobalConfig.configurer()
                .basePackage("com.github.zlboot")
                .mappingPackage("mapping")
                .author("CodeGen")
                .dataSource(DataSourceConfig.configurer()
                        .setDriver(Driver.MySql)
                        .setDriverName("com.mysql.cj.jdbc.Driver")
                        .setUrl("jdbc:mysql://localhost:3306/db?useSSL=false&serverTimezone=UTC")
                        .setUsername("root")
                        .setPassword("root")
                        .config())
                .strategy(StrategyConfig.configurer()
                        .setGenerateBase(true) //是否生成基类
                        .setGenerateEntity(true) //是否生成Entity
                        .setGenerateMapper(true) //是否生成Mapper
                        .setGenerateService(true) //是否生成Service，包含接口和实现
                        .setGenerateController(true) //是否生成Controller
                        .setGenerateMapping(true) //是否生成mapping映射文件
                        .addFixedTableNames("up_application") //指定生成的表
                        .addIgnoreTableNames("") //指定忽略的表，在未指定生成表的情况下才会生效
                        .setTablePrefix("up_") //指定表前缀，生成类名时，忽略指定的表前缀
                        .setAllowOverrideFile(false)
                        .setAllowRepeatFile(true)
                        .config())
                .template(TemplateConfig.configurer()
                        .setEntityFileName("/codegen/entity.java.vm")
                        .setMapperFileName("/codegen/mapper.java.vm")
                        .setServiceFileName("/codegen/service.java.vm")
                        .setServiceImplFileName("/codegen/serviceImpl.java.vm")
                        .setControllerFileName("/codegen/controller.java.vm")
                        .setMappingFileName("/codegen/mapping.java.vm")
                        .config())
                .metadata(new MetaDataCustomizer() {
                    @Override
                    protected void customize() {
                        this.push("name", "Zhongl");
                        this.push("age", 18);
                    }
                })
                .config());
    }

}
```

##### peek()（查看表信息）

> 查看表的相关信息，在需要指定生成表的时候可以先行查看相关表信息

```java
package com.github.zlcb.zlboot;

import com.github.zlcb.zlsdk.codegen.SmartCodeGenerator;
import com.github.zlcb.zlsdk.codegen.config.*;
import com.github.zlcb.zlsdk.codegen.database.Driver;

/**
 * @author Zhongl
 * @date 2020/05/23 18:33
 */
public class CodeGenApp {

    public static void main(String[] args) {
		SmartCodeGenerator.peek(DataSourceConfig.configurer()
                .setDriver(Driver.MySql)
                .setUrl("jdbc:mysql://localhost:3306/db?useSSL=false&serverTimezone=UTC")
                .setUsername("root")
                .setPassword("root")
                .config());
    }
}

```

```java
以上代码输出：
=============================
NAME             COMMENT     
================ ============
up_application   应用系统        
up_dept          部门信息        
up_role          角色信息        
up_user          人员信息        
up_user_dept_rel 人员部门关系      
up_user_role_rel 人员角色关系      
=============================
Rows:6
```

##### peek()（查看表字段信息）

```java
package com.github.zlcb.zlboot;

import com.github.zlcb.zlsdk.codegen.SmartCodeGenerator;
import com.github.zlcb.zlsdk.codegen.config.*;
import com.github.zlcb.zlsdk.codegen.database.Driver;

/**
 * @author Zhongl
 * @date 2020/05/23 18:33
 */
public class CodeGenApp {

    public static void main(String[] args) {
		SmartCodeGenerator.peek(DataSourceConfig.configurer()
                .setDriver(Driver.MySql)
                .setUrl("jdbc:mysql://localhost:3306/db?useSSL=false&serverTimezone=UTC")
                .setUsername("root")
                .setPassword("root")
                .config(), "up_application"); //指定表名
    }
}
```

```java
以上输出内容：
====================================================================
NAME             DATA_TYPE PRIMARY_KEY AUTO_INCREMENT COMMENT       
================ ========= =========== ============== ==============
id               bigint    Yes         Yes            唯一标识          
name             varchar   No          No             应用名称          
code             varchar   No          No             应用代码          
uri              varchar   No          No             应用服务URL       
logo             blob      No          No             应用logo        
sub_logo         blob      No          No             应用副logo       
title            varchar   No          No             应用标题          
sub_title        varchar   No          No             应用副标题         
order_num        int       No          No             排序号           
status           char      No          No             状态            
create_user_id   varchar   No          No             创建者标识         
create_real_name varchar   No          No             创建者姓名         
create_time      datetime  No          No             创建时间          
update_user_id   varchar   No          No             更新者标识         
update_real_name varchar   No          No             更新者姓名         
update_time      datetime  No          No             更新时间          
--------------------------------------------------------------------
Rows:16
```



### 如何交流、反馈？

- 邮箱：996cxy@gmail.com
- qq群：584847393
- github：https://github.com/zlcb


