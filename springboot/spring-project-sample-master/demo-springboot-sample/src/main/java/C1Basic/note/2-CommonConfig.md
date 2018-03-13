## Bean的Scope -`demo.springboot.scope`
* Singleton：一个Spring容器只有一个，默认选项
* Prototype：每次调用创建一个新的Bean
* Requst：给每个requst创建一个新的Bean
* Session：给每个session创建一个新的Bean

```java
@Service
@Scope("prototype")
public class DemoPrototypeService {
}
```

## SpringEL -`demo.springboot.el`
@PropertySource：在Java中引入properties/xml文件
@Value：注入值到对应的属性中，来源可以是文件/url/静态方法等，通过表达式访问
```java
@PropertySource("classpath:el/test.properties")
public class ElConfig {
    @Value("Love U")
    String normal;

    @Value("#{systemProperties['os.name']}")
    String osName;

    @Value("#{T(java.lang.Math).random()*100.00}")
    double randomNumber;

    @Value("#{demoReadFileService.addition}")
    String addition;

    @Value("classpath:el/test.txt")
    Resource testFile;

    @Value("http://www.baidu.com")
    Resource testUrl;

    @Value("${project.name}")
    String projectName;
}
```

## Bean的工作流程 -`demo.springboot.prepost`
构造函数-》init方法-》destroy方法
* 可以通过@Bean(initMethod=" ",destroyMethod=" ")指定Bean的生存周期方法
* 可以通过@PostConstruct@PreDestroy，在Bean内部预先注解好生存周期方法
```java
//Bean方式
public class BeanWayService {
    public BeanWayService() {}
    
    public void init() {}
    public void destroy() {}
}

//JSR方式
public class JSR250WayService {
    public JSR250WayService() {}
   
    @PostConstruct
    public void init() {}
    @PreDestroy
    public void destroy() {}
}

//实际使用
public class ProPostConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    BeanWayService beanWayService() { return new BeanWayService(); }

    @Bean
    JSR250WayService jsr250WayService() { return new JSR250WayService(); }
}
```

## Profile 不同环境的配置切换
在JVM或者application.yaml里设置 spring.profiles.active=xxx ,spring里会根据Profile名称匹配
* 使用@Profile(...)注解需要切换的@Bean，
* 设置application-dev.yaml/application-test.yaml等不同配置
```java
public class ProfileConfig {
    @Bean
    @Profile("dev")
    public String devBean() { return "获取dev"; }

    @Bean
    @Profile("test")
    public String testBean() { return "获取test"; }
}
```

## 事件
Bean与Bean的消息通信：定义事件-》定义监听-》发布事件
* 自定义事件，继承ApplicationEvent（还有一些内置的Event）
```java
public class DemoEvent extends ApplicationEvent {
    private String msg;

    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() { return msg; }

    public void setMsg(String msg) {  this.msg = msg; }
}
```
* 定义监听器，实现ApplicationListener<T>，指定监听的事件类
```java
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent event) {
        String msg = event.getMsg();
        System.out.println("收到了DemoEvent的消息：" + msg);
    }
}
```
* 发送事件，通过获取AbstractApplicationContext，执行publishEvent函数发布事件
```java
@Component
public class DemoPublisher {

    @Autowired
    AbstractApplicationContext context;

    public void pulish() {
        context.publishEvent(new DemoEvent(this, "欢迎欢迎"));
    }
}
```
* 测试
```java
@Configuration
@ComponentScan("demo.springboot.event")
public class EventConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher publisher = context.getBean(DemoPublisher.class);
        publisher.pulish();
        context.close();
    }
}
```