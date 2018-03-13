package C1Basic.code.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoPublisher {

    @Autowired
    AbstractApplicationContext context;

    public void pulish() {
        context.publishEvent(new DemoEvent(this, "欢迎欢迎"));
    }
}
