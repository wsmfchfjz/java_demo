package anddd7.springboot;

import anddd7.springboot.configuration.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

/**
 * 将spring的启动和配置单独出来
 *
 * @SpringBootApplication 等价于 :
 * -     @Configuration 通过@ImportResource引入xml文件,@Value读取键值 ,@Bean读取配置的bean实例
 * -     @ComponentScan 同xml中的自动扫描组件
 * -     @EnableAutoConfiguration
 * @EnableAutoConfiguration 下包含了一系列自动配置类的清单 ,并按顺序执行 ,在Spring3时代就有@EnableWebMvc注解
 * [SpringBoot之@EnableAutoConfiguration原理及自定义扩展 ](http://blog.csdn.net/xiaoyu411502/article/details/52770723)
 * <p>
 * MongoAutoConfiguration.class为例 :
 * -    使用了@Configuration ,标识是一个配置
 * -    定义了必要的Mongo对象@Bean
 * -    使用了@EnableConfigurationProperties ,将application.properties配置文件中的属性映射到Java类中 ,便于使用
 * -    @ConditionOnClass表明加载条件 - Mongo.class位于类路径上
 * -    @ConditionalOnMissingBean说明Spring Boot仅仅在当前上下文中不存在Mongo对象时，才会实例化一个Bean
 */

@Import({MyBatisConfiguration.class,
        SwaggerConfiguration.class,
        MapperScannerConfiguration.class,
        MVCConfiguration.class
})
@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {

        //一键自动启动
        SpringApplication.run(Application.class, args);

        //配置启动
//        SpringApplication app = new SpringApplication();
//        app.setBannerMode(Banner.Mode.OFF);

        //链式API构建器
//        new SpringApplicationBuilder()
//                .bannerMode(Banner.Mode.OFF)
//                .child(Application.class)
//                .run();

    }
}