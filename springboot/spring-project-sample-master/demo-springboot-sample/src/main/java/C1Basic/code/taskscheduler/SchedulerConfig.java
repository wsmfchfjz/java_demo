package C1Basic.code.taskscheduler;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("C1Basic.code.taskscheduler")
@EnableScheduling//注解开启计划任务
public class SchedulerConfig {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SchedulerConfig.class);
    }
}
