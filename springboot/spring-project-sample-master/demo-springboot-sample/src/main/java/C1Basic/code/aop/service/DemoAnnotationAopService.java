package C1Basic.code.aop.service;

import C1Basic.code.aop.impl.AopAction;
import org.springframework.stereotype.Service;

/**
 * 注解式拦截，在对应方法上加上@AopAction，在拦截时通过检查注解进行拦截
 */
@Service
public class DemoAnnotationAopService {
    @AopAction(name = "注解式AOP")
    public String say() {
        return "Hello,Annotation World.";
    }
}
