package C1Basic.code.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

    @Bean
    @Profile("dev")
    public DemoBean devBean() {
        return new DemoBean("获取dev");
    }

    @Bean
    @Profile("test")
    public DemoBean testBean() {
        return new DemoBean("获取test");
    }

    public static void main(String[] args) {
        /**
         * jvm中设置默认值
         * spring.profiles.active=dev
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProfileConfig.class);

        DemoBean bean = context.getBean(DemoBean.class);
        System.out.println(bean.getContent());

        context.close();
    }
}
