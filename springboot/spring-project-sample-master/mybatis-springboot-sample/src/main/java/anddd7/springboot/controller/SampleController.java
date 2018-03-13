package anddd7.springboot.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自动配置: 根据pom配置（实际上应该是根据具体依赖）来判断这是一个什么应用(web/mvc) ,并创建相应的环境
 */
@EnableAutoConfiguration
/**
 * 还有一类@RestController ,返回值直接作为HTTP Response的Body部分返回
 * 同@ResponseBody
 */
@Controller
public class SampleController {

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        /**
         * SpringApplication是用于从main方法启动Spring应用的类 ,默认会执行以下步骤：
         *
         * 创建一个合适的ApplicationContext实例 （取决于classpath）。
         * 注册一个CommandLinePropertySource，以便将命令行参数作为Spring properties。
         * 刷新application context，加载所有单例beans。
         * 激活所有CommandLineRunner beans。
         */
        SpringApplication.run(SampleController.class, args);
        /**
         * 直接使用SpringApplication 的静态方法run()即可 ,也可以创建实例，并自行配置需要的设置
         */
        //SpringApplication app = new SpringApplication(MyApplication.class);
        /* ... customize app settings here */
        //app.run(args)
    }
}
