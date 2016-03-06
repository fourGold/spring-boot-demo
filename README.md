spring boot demo
===========
基于Spring boot框架,由Gradle构建,Swagger生成文档的后台API应用.

IDE
===
第一次构建

1. 配置数据库
更新`src/main/resources/application.properties`下的数据库配置

2. 构建项目

       gradle wrapper
       ./gradlew idea
       ./gradlew build

3. 运行项目

   开发模式

       ./gradlew bootRun
   支持热替换,即时修改即时更新

   服务器模式

       ./gradlew build
       java -jar build/libs/{your-jar-name}.jar
   不支持热替换


API文档
===
运行项目后,可以访问`http://localhost:8080/swagger-ui.html`查看API文档
