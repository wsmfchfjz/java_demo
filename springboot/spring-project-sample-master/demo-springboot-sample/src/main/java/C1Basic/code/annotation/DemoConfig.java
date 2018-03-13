package C1Basic.code.annotation;

import C1Basic.code.annotation.service.DemoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@CommonConfiguration("C1Basic.code.annotation")
public class DemoConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        DemoService demoService = context.getBean(DemoService.class);
        demoService.output();
    }
}
