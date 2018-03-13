package C2MVC.code.controller;

import C2MVC.code.services.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

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
