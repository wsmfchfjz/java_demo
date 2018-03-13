package C3Boot.code;

import C3Boot.code.config.MVCConfig;
import C3Boot.code.config.WebSocketConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import({MVCConfig.class,
        WebSocketConfig.class
})
@SpringBootApplication
public class MyApplication {
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        return new ServletRegistrationBean(new DefaultServlet(), "*.html");// ServletName默认值为首字母小写，即myServlet
//    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
