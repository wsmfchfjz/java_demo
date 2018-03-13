## Web开发
SpringBoot提供了`spring-boot-starter-web`支持web开发，里面包括自动配置的servlet容器、http编码、上传文件、jackson、springMVC等

### Thymeleaf模板引擎
类似于JSP，将页面和SpringMVC的view绑定在一起，在页面中可以直接访问后端属性（通过session或model）

现在AJAX+SpringMVC会更简易灵活一些


### WebSocket
```java
 @MessageMapping("/welcome")
 @SendTo("/topic/getResponse")
 public TransResponse say(TransRequest msg) throws Exception{
    Thread.sleep(3*1000);
    return new TransResponse("Welcome,"+msg.getName()+"!");
}
```
页面通过`sockjs+stomp`连接到WebSocket :
订阅者通过`@SendTo("/topic/getResponse")`订阅主题(方法) ; 发布者通过`@MessageMapping("/welcome")`发布消息(执行方法).

