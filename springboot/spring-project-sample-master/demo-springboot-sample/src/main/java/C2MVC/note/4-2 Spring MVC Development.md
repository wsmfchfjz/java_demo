## 文件上传

* 引入commons-fileupload包
* MVC配置对媒体资源的处理
* Controller处理 ,保存收到的文件
```java
public class MvcConfig extends WebMvcConfigurerAdapter {
    
    //...
    
    /**
     * 对multipart类型 (文件)的默认处理设置
     * 由commons-upload实现 需要引入
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
}

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(MultipartFile file) {
        try {
            File newFile = new File("D:/upload/" + file.getOriginalFilename());
            FileUtils.writeByteArrayToFile(newFile, file.getBytes());
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败 : " + e.getMessage();
        }
    }
}
```

## MessageCovertor

* Spring内置了很多 ,默认Jackson
* 如果需要自定义 ,继承 AbstractHttpMessageCovertor

## 服务端推送

* Ajax心跳 : 频率不好控制 ,服务器压力
* WebSocket
* 异步等待 ,服务器抓住请求 ,等待推送时再返回(Server Send Event) - 实质还是浏览器不断请求 异步处理


#### SSE : 需要浏览器支持 ,使用SourceEvent去不断地请求服务器(异步 ,监听到返回再进行下一步) ,
    服务器可以hold这个连接直到合适的时候

```java
@Controller
public class SSEController {
    @RequestMapping(value = "/push", produces = "text/event-stream")
    @ResponseBody
    public String push() {
        Random r = new Random();
        try{
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "data:Testing 1,2,3"+r.nextInt()+"\n\n";
    }
}
```
```javascript
if (!!window.EventSource) {
    //设置连接后端的方法(url)
    var source = new EventSource('push');

    source.addEventListener('message', function (e) {
        //监听正常返回的消息
    });

    source.addEventListener('open', function (e) {
        //监听打开连接时
    }, false);

    source.addEventListener('error', function (e) {
        //监听error
    }, false);
} 
```

#### servlet 3.0+ 开启异步方法
* 使用DeferredResult ,异步返回 ,页面使用Ajax循环访问即可

```java
/**
* 控制器调用 具有异步特性的service层 ,在调用结束后控制器就完成任务
* 由service(实质上时DeferredResult)去控制何时返回响应给客户端
*/
@Controller
public class AysncController {
    @Autowired PushService pushService;

    @RequestMapping(value = "/defer")
    @ResponseBody
    public DeferredResult<String> defer() {
        return pushService.getAysncUpdate();
    }
}

@Service
public class PushService {
    /**
     * @DeferredResult 是用来实现异步请求的(业务逻辑耗时很长)
     * 原servlet流程: request->servlet.service()->执行业务逻辑(servlet阻塞)->response
     * 新的servlet流程: request->创建子线程执行业务逻辑->servlet结束(但不反悔response)->子线程结束返回response(子线程中有req,res)
     */
    DeferredResult<String> deferredResult;

    /**
     * 方法创建了一个新的DeferredResult 并直接返回 ,servlet接受到这个result过后就结束任务并返回线程池
     * 而request和response移交到了DeferredResult内 ,待setResult后 ,才会返回
     */
    public DeferredResult<String> getAysncUpdate() {
        deferredResult = new DeferredResult();
        return deferredResult;
    }

    @Scheduled(fixedDelay = 3000)
    public void refresh() {
        deferredResult.setResult(String.valueOf(System.currentTimeMillis()));
    }
}
```
## SpringMVC的测试
* Spring-test + Junit：使用一些模拟的组件对MVC部分进行单元测试
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMVCConfig.class})
@WebAppConfiguration("src/main/resources")//标示web资源位置，默认webapp，spring一般是resources
public class TestControllerIntegrationTests {
    private MockMvc mockMvc;//模拟的mvc对象，使用MockMvcBuilders构造

    //测试时注入bean和各种模拟的部件
    @Autowired private DemoService demoService;
    @Autowired WebApplicationContext context;
    @Autowired MockHttpSession session;
    @Autowired MockHttpServletRequest request;

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
```



