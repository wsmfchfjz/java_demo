package C1Basic.code.scope;

import C1Basic.code.scope.service.DemoPrototypeService;
import C1Basic.code.scope.service.DemoSingletonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("C1Basic.code.scope")
public class ScopeConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);

        DemoSingletonService singletonService1 = context.getBean(DemoSingletonService.class);
        DemoSingletonService singletonService2 = context.getBean(DemoSingletonService.class);
        System.out.println("s1与s2是否相等？" + singletonService1.equals(singletonService2));

        DemoPrototypeService prototypeService1 = context.getBean(DemoPrototypeService.class);
        DemoPrototypeService prototypeService2 = context.getBean(DemoPrototypeService.class);
        System.out.println("p1与p2是否相等？" + prototypeService1.equals(prototypeService2));
    }
}
