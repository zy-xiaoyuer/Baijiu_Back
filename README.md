# Baijiu_Back
Baijiu_Back - 白酒网站后台管理系统

简介

    Baijiu_Back 是一个基于 Spring Boot 框架的白酒网站后台管理系统。该系统旨在提供一套完整的后台解决方案，包括用户管理、酒诗管理（按朝代和按地域）、酒器管理、酒画管理等功能，
    每个管理部分都有增删改查功能以支持白酒网站的日常运营和管理。
技术栈

    Spring Boot：作为项目的核心框架，提供快速开发、自动配置和简化部署的能力。
    MyBatis-Plus：增强版的 MyBatis，简化数据库操作，提高开发效率。
    MySQL：关系型数据库，用于存储系统数据。
    Druid：数据库连接池，提供高效的数据库连接管理。
    Swagger：API 文档生成工具，方便前后端联调。
    Lombok：通过注解简化 Java 类的编写，如自动生成 getter/setter 方法。
    Freemarker：模板引擎，用于生成动态网页内容。

环境要求

    JDK 17 或更高版本
    Maven 3.6.x 或更高版本
    MySQL 5.7 或更高版本

快速开始

    1. 克隆项目
    bash
    git clone https://github.com/your-username/Baijiu_Back.git  
    cd Baijiu_Back

    2. 配置数据库

    在 src/main/resources/application.properties 或 application.yml 文件中配置数据库连接信息。

    properties
    spring.datasource.url=jdbc:mysql://localhost:3306/baijiu?useSSL=false&serverTimezone=UTC  
    spring.datasource.username=root  
    spring.datasource.password=yourpassword  
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


    3.编译和运行

    bash
    mvn clean install  
    mvn spring-boot:run

    或者，如果你使用的是 IDE（如 IntelliJ IDEA 或 Eclipse），可以直接运行主类 com.baijiu.BaijiuBackApplication。
    4.访问系统

    系统启动后，默认访问地址是 http://localhost:9000。
    依赖管理

    项目的依赖管理通过 Maven 进行，所有依赖项已在 pom.xml 文件中定义。
