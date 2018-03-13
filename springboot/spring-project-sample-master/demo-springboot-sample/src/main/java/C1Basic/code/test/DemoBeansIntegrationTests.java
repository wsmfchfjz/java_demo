package C1Basic.code.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DemoConfig.class})
@ActiveProfiles("dev")
public class DemoBeansIntegrationTests {
    @Autowired
    private DemoBean demoBean;

    @Test
    public void contentTest() {
        String content = demoBean.getContent();
        Assert.assertEquals(content, "register from dev");
    }

}
