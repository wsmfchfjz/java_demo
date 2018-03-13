package C1Basic.code.aop.impl;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Aop实现的地方
 */
@Aspect//声明一个切面
@Component//托管给Spring容器
public class AspectAopImpl {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 注解式，通过检查注解位置来拦截
     */
    @Pointcut("@annotation(C1Basic.code.aop.impl.AopAction)")
    public void annotationPointCut() {
    }

    @Before("annotationPointCut()")
    public void logBefore() {
        log.info("logBefore:现在时间是:" + new Date());
    }


    /**
     * 通过指定拦截位置和具体方法
     */
    @Pointcut("execution(* C1Basic.code.aop.service.DemoMethodAopService.*(..))")
    private void methodPointCut() {
    }

    @After("methodPointCut()")
    public void logAfter() {
        log.info("logAfter:现在时间是:" + new Date());
    }
}
