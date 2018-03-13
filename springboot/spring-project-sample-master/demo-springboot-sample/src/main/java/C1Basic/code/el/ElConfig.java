package C1Basic.code.el;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;


@Configuration
@ComponentScan("C1Basic.code.el")
@PropertySource("classpath:el/test.properties")
public class ElConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("Love U")
    String normal;

    @Value("#{systemProperties['os.name']}")
    String osName;

    @Value("#{T(java.lang.Math).random()*100.00}")
    double randomNumber;

    @Value("#{demoReadFileService.addition}")
    String addition;

    @Value("classpath:el/test.txt")
    Resource testFile;

    @Value("http://www.baidu.com")
    Resource testUrl;

    @Value("${project.name}")
    String projectName;

    @Autowired
    Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource() {
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(randomNumber);
            System.out.println(addition);
            System.out.println(IOUtils.toString(testFile.getInputStream()));
            System.out.println(IOUtils.toString(testUrl.getInputStream()));
            System.out.println(projectName);
            System.out.println(environment.getProperty("project.creater"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ElConfig.class);
        ElConfig elConfig = context.getBean(ElConfig.class);
        elConfig.outputResource();
        context.close();
    }
}
