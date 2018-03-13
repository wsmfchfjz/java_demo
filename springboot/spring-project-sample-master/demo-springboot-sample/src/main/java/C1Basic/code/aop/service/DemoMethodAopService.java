package C1Basic.code.aop.service;

import org.springframework.stereotype.Service;

@Service
public class DemoMethodAopService {
    public String say() {
        return "Hello, Normal World.";
    }
}
