package C2MVC.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

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
