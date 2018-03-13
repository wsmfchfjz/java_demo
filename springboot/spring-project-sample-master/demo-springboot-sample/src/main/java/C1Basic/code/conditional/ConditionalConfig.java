package C1Basic.code.conditional;

import C1Basic.code.conditional.condition.LinuxCondition;
import C1Basic.code.conditional.condition.WindowsCondition;
import C1Basic.code.conditional.service.ListService;
import C1Basic.code.conditional.service.impl.LinuxServiceImpl;
import C1Basic.code.conditional.service.impl.WindowsServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowsList() {
        return new WindowsServiceImpl();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linuxList() {
        return new LinuxServiceImpl();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionalConfig.class);
        ListService listService = context.getBean(ListService.class);
        System.out.println("查看系统列表的命令是:" + listService.showListCmd());
    }
}
