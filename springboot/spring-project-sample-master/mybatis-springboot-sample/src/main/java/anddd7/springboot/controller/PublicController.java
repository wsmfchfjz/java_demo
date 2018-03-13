package anddd7.springboot.controller;

import anddd7.springboot.controller.common.GlobalParm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    HttpSession session;

    @RequestMapping(value = "/defaultLogin", method = RequestMethod.GET)
    public String defaultLogin() {
        session.setAttribute(GlobalParm.USER_SESSION_KEY, new Object());
        return "Success";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello world";
    }


}
