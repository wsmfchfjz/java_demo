package C1Basic.code.aop.impl;

import java.lang.annotation.*;

/**
 * 注解本身是没有作用的，只是作为一个规则，关联拦截者和被拦截者
 */
@Target(ElementType.METHOD)//用于方法
@Retention(RetentionPolicy.RUNTIME)//可用于运行时
@Documented
public @interface AopAction {
    String name();
}
