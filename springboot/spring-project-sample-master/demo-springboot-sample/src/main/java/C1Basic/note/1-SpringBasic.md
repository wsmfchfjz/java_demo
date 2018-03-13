## Spring配置方式
* 配置文件`xml`（Spring 1.x） 》》 
* 注解`@annotation`（Spring 2.x+Jdk1.5）》》
* Java配置（Spring 3/4）

**`SpringBoot`就是基于Java配置（注解的应用）**

##Spring组件
* 核心组件（Core）
    * -Core：核心工具类，Spring其他模块的主要依赖
    * -Bean：对`bean`的支持
    * -Context：运行时容器
    * -Context-Support：容器对第三方包的集成支持
    * -Expression：使用表达式语言查询操作运行时的对象
* AOP
    * -AOP：基于代理的AOP支持
    * -Aspects：基于AspectJ的AOP支持
* Messaging
    * -Messaging：对消息架构和协议的支持
* Web
    * -Web：基础的集成功能，为Web项目提供Spring容器
    * -Webmvc：基于Servlet的Spring MVC
    * -WebSocket：提供WebSocket功能
    * -Webmvc-Portlet：支持Portlet环境
* Data Access/Integration
    * -JDBC：以JDBC方式访问数据库
    * -TX：事务支持
    * -ORM：对象关系映射支持
    * -OXM：对象xml映射支持
    * -JMS：对JMS的支持

## Spring生态圈（独立项目）
* -Boot：使用默认配置快速开发
* -XD：简化大数据应用开发
* -Cloud：分布式系统开发工具集
* -Data：对主流关系型/NoSql数据库的支持
* -Integration：通过消息机制对企业集成模式的支持
* -Batch：简化优化大数据批处理
* -Security：认证授权
* -Social：与社交网络API的继承
* -Mobile：对手机设备的识别
* -Web Services：基于协议有限的SOAP/Web服务
* -Session：提供API管理用户会话
* ...

## Spring原则
* 使用POJO进行轻量级和最小侵入式开发
* 通过依赖注入和基于接口编程降低耦合
* 通过AOP和默认习惯进行声明式编程
* 使用AOP和模板减少模式化（重复的）代码

## 依赖注入 （IOC+DI）
即由Spring容器创建管理Bean（POJO类），通过注解/XML/JAVA告诉容器应该将Bean何时放在何地。

声明Bean：
* @Component 通用
* @Service 在业务逻辑层使用
* @Repository 数据访问层使用
* @Controller 展现层使用

引入Bean：
* @Autowired Spring提供的注解
* @Inject/@Resource JSR-330/JSR-250提供的注解

## JAVA配置
@Configuration 表示这个类是一个配置类，等效于一个xml文件
@Bean 注解一个方法，将方法返回值注册为一个bean

* 配置注解化
```xml
<context:component-scan base-package="com.controller"></context:component-scan>
```
```java
@ComponentScan("com.controller") 
```

* 配置Java化
```xml
<bean class="com.util.MySwaggerConfig"/>
```
```java
@Configuration
public class JavaConfig{
    @Bean
    public MySwaggerConfig mySwaggerConfig(){
        return new MySwaggerConfig();
    } 
}
```

## 使用Aspectj实现AOP

有两种方式，一个是监听方法（通过配置目标路径），一个是监听注解（在目标方法上放置特殊注解）
* 定义注解，作为被监听的标识
```java
/**
 * 注解本身是没有作用的，只是作为一个规则，关联拦截者和被拦截者
 */
@Target(ElementType.METHOD)//用于方法
@Retention(RetentionPolicy.RUNTIME)//可用于运行时
@Documented
public @interface AopAction {
    String name();
}
```

* 书写被拦截的Service(注解式拦截，需要注解Service的方法)
```java
/**
 * 注解式拦截，在对应方法上加上@AopAction，在拦截时通过检查注解进行拦截
 */
@Service
public class DemoAnnotationAopService {
    @AopAction(name = "注解式AOP")
    public String say() {
        return "Hello,Annotation World.";
    }
}

@Service
public class DemoMethodAopService {
    public String say() {
        return "Hello, Normal World.";
    }
}
```

* 定义拦截器实现，定义拦截的对象和处理方法
```java
/**
 * Aop实现的地方
 */
@Aspect//声明一个切面
@Component//托管给Spring容器
public class AspectAopImpl {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 注解式，通过检查注解位置来拦截
     */
    @Pointcut("@annotation(demo.springboot.aop.impl.AopAction)")
    public void annotationPointCut() {}
    
    @Before("annotationPointCut()")
    public void logBefore() { log.info("logBefore:现在时间是:" + new Date()); }

    /**
     * 通过指定拦截位置和具体方法
     */
    @Pointcut("execution(* demo.springboot.aop.service.DemoMethodAopService.*(..))")
    private void methodPointCut() {}

    @After("methodPointCut()")
    public void logAfter() { log.info("logAfter:现在时间是:" + new Date()); }
}
```


## 启用AOP
注入配置文件，开启AspectJ
```java
/**
* 用注解代替了xml
* <aop:aspectj-autoproxy proxy-target-class="true"/>
*/
@EnableAspectJAutoProxy
@Configuration
@ComponentScan("demo.springboot.aop")//在Application入口整体扫描，可省略此行
public class AopConfiguration {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
         /**
         * 测试两种Aop
         */
         DemoAnnotationAopService annotationAopService = context.getBean(DemoAnnotationAopService.class);
        DemoMethodAopService methodAopService = context.getBean(DemoMethodAopService.class);

        System.out.println(annotationAopService.say());
        System.out.println(methodAopService.say());
        context.close();
    }
}
```






