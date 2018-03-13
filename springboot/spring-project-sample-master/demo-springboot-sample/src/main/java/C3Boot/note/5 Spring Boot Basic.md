## SpringBoot基础
* 优点：*习惯优于配置* - 简化的Maven构建；默认的常用配置；内嵌Servlet容器可独立运行；没有分散且复杂的xml；
* 缺点：需要高度定制时仍旧需要大量的配置；代码+注解固化了不会变动的配置（Spring容器的相关配置），但是不代表不需要配置文件了（数据库连接等可能变动的配置）

## 新建一个SpringBoot项目
* 使用Maven创建，继承`spring-boot-starter-parent`，然后添加你需要模块的starter就可以了，各模块starter都包含会用到的所有依赖，非常清洁
* `spring-boot-dependencies`里定义了第三方jar的版本号（兼容可用的），免去检查兼容性的烦恼
* 参照教程，书写SpringBoot的启动器即可
