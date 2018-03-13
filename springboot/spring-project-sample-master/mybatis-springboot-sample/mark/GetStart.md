## 内置ServletContainer
| Name        | Servlet Version           | Java Version  |
| ------------- |:-------------:| -----:|
| Tomcat 8      | 3.1 | 7+ |
| Tomcat 7      | 3.0 | 6+ |
| Jetty 9.3     | 3.1 | 8+ |
| Jetty 9.2      | 3.1 | 7+ |
| Jetty 8      | 3.0 | 6+ |
你仍然可以部署Spring Boot项目到任何兼容Servlet3.0+的容器

## 使用SpringBoot
pom.xml继承spring-boot-starter-parent,只加入starter-web ,书写一个Controller即可使用
可执行mvn操作

## 创建starter
名字格式不能是spring-boot-starter-*，而是 *-spring-boot-starter。类似Maven插件的规则
