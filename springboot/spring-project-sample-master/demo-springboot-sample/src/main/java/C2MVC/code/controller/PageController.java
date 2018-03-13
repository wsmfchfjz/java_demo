package C2MVC.code.controller;


import C2MVC.code.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @Autowired DemoService demoService;

    @RequestMapping("/index")
    public String getIndex() {
        return "index";
    }

    @RequestMapping("/pages/{pageName}")
    public String getPages(@PathVariable("pageName") String pageName) {
        return pageName;
    }

    @RequestMapping("/normal")
    public String testNormal(Model model) {
        model.addAttribute("msg", demoService.saySomething());
        return "index";
    }
}
