package C2MVC.code.controller;

import C2MVC.code.domain.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String index(HttpServletRequest request) {
        return "url:" + request.getRequestURL() + " can access";
    }

    @RequestMapping(value = "/login/{userId}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String demoLogin(@PathVariable Integer userId, HttpServletRequest request) {
        return "url:" + request.getRequestURL() + " can access , id is :" + userId;
    }

    @RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String demoRegister(UserBean user, HttpServletRequest request) {
        return "url:" + request.getRequestURL() + " can access , User:[" + user.getId() + "," + user.getName() + "]";
    }

    @RequestMapping(value = {"/name1", "/name2"}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String demoMultiPath(HttpServletRequest request) {
        return "url:" + request.getRequestURL() + " can access";
    }
}
