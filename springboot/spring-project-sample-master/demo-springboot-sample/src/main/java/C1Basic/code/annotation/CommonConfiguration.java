package C1Basic.code.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;


@Target(ElementType.TYPE)/* Class, interface (including annotation type), or enum declaration */
@Retention(RetentionPolicy.RUNTIME)/* 保留至运行时 ,运行时可以检测到这个注解 */
@Documented/* 被javadoc记录 */
/**
 * 组合注解
 *
 * 组合注解类似继承 ,可以使用同名函数(属性) 覆盖 被组合的注解中的函数(属性)
 */
@Configuration
@ComponentScan
public @interface CommonConfiguration {
    String[] value() default {};
}
