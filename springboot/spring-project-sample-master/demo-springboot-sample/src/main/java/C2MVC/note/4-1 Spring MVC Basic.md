## SpringMvc 快速搭建

* 依赖 : 这里直接使用SpringBoot的快速搭建
```xml
<!-- 包含常用的web/mvc等依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
```xml
<!-- 用于管理spring相关的依赖版本 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>${spring-boot.version}</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```
* 日志 : Spring4推荐使用logback
> 简历一个logback.xml文件进行日志配置 ,内容与log4j差不多

* 页面 : SpringBoot习惯把页面放置在resources下面

## 快速配置
用SpringBoot(注解风格)配置代替web.xml和spring-mvc.xml

* web.xml
```java
/**
 * @WebApplicationInitializer 用来配置Servlet3.0的接口 ,也就代替了web.xml ,里面配置的内容和xml配置基本一致 ,部署在tomcat时容器会自动寻找并加载这个实现
 * <p>
 * SpringBoot方式启动的话 ,可以通过配置类(类里定义servlet bean ,然后import到Application)
 */
public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MvcConfig.class);
        context.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
```
* SpringMvc
```java
@Configuration
@EnableWebMvc //开启一些默认配置MessageConverters,ViewResolvers等
@ComponentScan("demo2.springboot.mvc")
public class MvcConfig {
    /**
     * 注册视图转换器 ,为mvc返回的页面路径添加前后缀
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
}
```

## 页面和Controller
```java
@Controller
public class HelloController {

    @RequestMapping("/index")
    public String hello() {
        System.out.println("进入controller");
        return "index";
    }
}
```
省略`index.jsp`

## 部署
SpringBoot(-web)有自带的Tomcat ,先排除再关联Servlet3.0需要的包
用Maven打包成war ,部署在tomcat即可
```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
 </dependencies>
```

## SpringMvc常用注解
* `@Controller` 声明控制器Bean ,容器的DispatcherServlet会把控制器和url绑定
* `@RequestMapping` 指定访问路径 ,`produces`可指定返回资源的类型
* `@ResponseBody` 支持返回值放在response内 ,用于AJAX返回数据而非页面
* `@RequestBody` 允许参数在request内 ,通常处理POST体
* `@PathVarible` 接受路径中的参数 ,例如: `/user/add/10002` -> ( `/user/add/{id}` ) -> id=10002
* `@RestController` 等效 `@Controller + @ResponseBody`
```java
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String index(HttpServletRequest request) {
        return "url:" + request.getRequestURL() + "can access";
    }

    @RequestMapping(value = "/login/{userId}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String demoLogin(@PathVariable Integer userId, HttpServletRequest request) {
        return "url:" + request.getRequestURL() + "can access , id is :" + userId;
    }

    @RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String demoRegister(UserBean user, HttpServletRequest request) {
        return "url:" + request.getRequestURL() + "can access , User:[" + user.getId() + "," + user.getName() + "]";
    }

    @RequestMapping(value = {"/name1", "/name2"}, produces = "application/xml;charset=UTF-8")
    @ResponseBody
    public String demoMultiPath(HttpServletRequest request) {
        return "url:" + request.getRequestURL() + "can access";
    }
}
```

## MVC基本配置

* `DispatcherServlet`通常拦截所有URL ,而静态资源 js/html/css 需要直接访问 ,需要对Mvc进行配置
    * 静态资源放置在`resources`(根目录)下 
    * 配置类继承`WebMvcConfigurerAdapter`
    * 添加静态资源路径 ,覆盖`addResourceHandlers(ResourceHandlerRegistry registry)`方法

```java
public class MvcConfig extends WebMvcConfigurerAdapter {
    
    //...
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }
}
```

* 拦截器配置
    * 实现`HandlerInterceptor`接口 或者 继承`HandlerInterceptorAdapter`类 ,实现自定义拦截器
    * 在`WebMvcConfigurerAdapter`中添加interceptor
```java
public class DemoInterceptor extends HandlerInterceptorAdapter {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { return true; }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}
}

public class MvcConfig extends WebMvcConfigurerAdapter {
   
    //...
   @Bean
   public DemoInterceptor demoInterceptor() {
       return new DemoInterceptor();
   }
   @Override
    public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(demoInterceptor());
    }
}
```

* ControllerAdvice - 控制器行为总控 (类似AOP)
    * 使用`@ControllerAdvice`注解一个类
    * 注解ControllerAdvice类中的方法 ,对所有`@RequesMapping`的方法生效
```java
@ControllerAdvice //注解启动了一个总控的Controller ,里面的方法会应用到所有@RequestMapping方法 ,并根据注解的不同产生不同作用
public class DemoControllerAdvice {

    @ModelAttribute //在目标方法执行前 , 产生一个对象 , 并setAttribute
    public UserBean addAttribute() {
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model");
        return new UserBean(1, "admin");
    }

    @InitBinder // 针对WebDataBinder的预处理
    public void initBinder(WebDataBinder binder) {
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器");
    }

    @ExceptionHandler(NoClassDefFoundError.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView handleException(WebRequest request, NoClassDefFoundError e) {
        System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出NoClassDefFoundError异常时执行");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMsg", e.getMessage());
        return modelAndView;
    }
}
```

* 路径参数默认忽略"."后的 ,例如 /user/{xx.yy} ,接收到的只有 xx , 需要在Mvc配置中手动关闭
```java
public class MvcConfig extends WebMvcConfigurerAdapter {
    //...
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseRegisteredSuffixPatternMatch(false);
    }
}
```