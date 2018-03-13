package C1Basic.code.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DemoConfig {

    @Bean
    @Profile("dev")
    public DemoBean registerDev() {
        return new DemoBean("register from dev");
    }

    @Bean
    @Profile("test")
    public DemoBean registerTest() {
        return new DemoBean("register from test");
    }
}
