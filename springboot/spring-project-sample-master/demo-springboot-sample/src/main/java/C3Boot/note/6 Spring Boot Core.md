## SpringBoot 核心

###  基本配置
* @SpringBootApplication：核心注解，组合了常用的三大注解
    * @Configuration：通过@ImportResource引入xml文件,@Value读取键值 ,@Bean读取配置的bean实例
    * @ComponentScan：同xml中的自动扫描组件
    * @EnableAutoConfiguration：下包含了一系列自动配置类的清单 ,并按顺序执行 ,在Spring3时代就有@EnableWebMvc注解
    * 通过注解的exclude属性，可以关闭特定的配置
* SpringApplication是内置容器的启动器
    * 定制Banner：在resources下新建banner.txt，里面放上显示的banner字符图案
    * 关闭Banner：获取SpringApplication对象，有一些设置函数
* 默认配置文件application.yaml：可以自定义设置Tomcat容器和Spring
* 通过stater引入其他模块
* @ImportResource：引入必要的xml或其他类型的配置文件

### 外部配置
* 命令行：java -jar xx.jar --server.port=9090（等效于加在application.yaml里）
* 常规配置：application.yaml里添加属性，入口类（配置类）中使用@Value可以直接引入
* 类型安全配置：通过@ConfigurationProperties的prefix（属性前缀）、locations（配置文件位置），可以批量引入配置到类中

### 日志配置
SpringBoot支持多种日志框架，默认Logbcak（轻量级）；可以通过application.yaml配置也可以额外使用xml

### Profile
使用spring.profiles.active开启相应的配置（prod/dev）

### SpringBoot的运行原理
* 自动配置的相关代码都在`spring-boot-autoconfigure`里，输出debug日志可以看到自动配置开启的内容
* @SpringBootApplication-》@EnableAutoConfiguration-》EnableAutoConfigurationImportSelector-》META-INF/spring.factories（包含自动配置类列表）
* 核心注解@Conditional，根据条件加载自动的配置

示例-Http编码
```xml
<!-- 以前的xml配置 -->
<filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
    </init-param>
</filter>
```
```java
/**
* HttpEncodingAutoConfiguration中根据条件去加载CharacterEncodingFilter
*/
@Configuration
@EnableConfigurationProperties(HttpEncodingProperties.class)//开启属性注入
@ConditionalOnWebApplication//是web项目
@ConditionalOnClass(CharacterEncodingFilter.class)//CharacterEncodingFilter在classpath
@ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)//spring.http.encoding=enable时开启，不设置默认开启
public class HttpEncodingAutoConfiguration {

	private final HttpEncodingProperties properties;

	public HttpEncodingAutoConfiguration(HttpEncodingProperties properties) {
		this.properties = properties;
	}

	@Bean
	@ConditionalOnMissingBean(CharacterEncodingFilter.class)//容器中没有Bean时创建
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
		filter.setEncoding(this.properties.getCharset().name());
		filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
		filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
		return filter;
	}
	//...
}
```

### 实现自己的starter
* 使用@ConfigurationProperties读取配置-》配置类
* 功能类（service）
* 构造自动配置类-》以配置类的内容作为条件将功能类注入成bean
* 注册自动配置类到spring.factories
* maven打包成stater
* 其他工程直接引用jar，@Autowired 功能类bean即可