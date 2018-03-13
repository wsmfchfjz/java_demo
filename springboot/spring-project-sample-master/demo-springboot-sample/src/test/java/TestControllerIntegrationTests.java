import C2MVC.code.config.SpringMVCConfig;
import C2MVC.code.services.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMVCConfig.class})
@WebAppConfiguration("src/main/resources")//标示web资源位置，默认webapp，spring一般是resources
public class TestControllerIntegrationTests {
    private MockMvc mockMvc;//模拟的mvc对象，使用MockMvcBuilders构造

    //测试时注入bean和各种模拟的部件
    @Autowired
    private DemoService demoService;
    @Autowired
    WebApplicationContext context;
    @Autowired
    MockHttpSession session;
    @Autowired
    MockHttpServletRequest request;


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .build();
    }

    @Test
    public void testNormalController() throws Exception {
        //模拟发送请求
        mockMvc.perform(MockMvcRequestBuilders.get("/normal"))
                //各种预期结果
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.view()
                        .name("index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/classes/views/index.jsp"))
                .andExpect(MockMvcResultMatchers.model()
                        .attribute("msg", demoService.saySomething()));
    }

    @Test
    public void testRestController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/testRest"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(demoService.saySomething()));
    }
}
