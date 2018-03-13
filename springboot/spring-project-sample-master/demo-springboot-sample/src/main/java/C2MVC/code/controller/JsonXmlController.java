package C2MVC.code.controller;

import C2MVC.code.domain.UserBean;
import C2MVC.code.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest")
public class JsonXmlController {
    @Autowired DemoService demoService;

    @RequestMapping(value = "/getJson", produces = "application/json;charset=UTF-8")
    public UserBean getJson(HttpServletRequest request) {
        return new UserBean(2, "我是JSON");
    }

    @RequestMapping(value = "/getXml", produces = "application/xml;charset=UTF-8")
    public UserBean getXml(HttpServletRequest request) {
        return new UserBean(5, "我是XML");
    }

    @RequestMapping(value = "/testRest", produces = "text/plain;charset=UTF-8")
    public String testRest() {
        return demoService.saySomething();
    }
}
