package C2MVC.code.config;

import C2MVC.code.domain.UserBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice //注解启动了一个总控的Controller ,里面的方法会应用到所有@RequestMapping方法 ,并根据注解的不同产生不同作用
public class MyControllerAdvice {

    @ModelAttribute //在目标方法执行前 , 产生一个对象 , 并setAttribute
    public UserBean addAttribute() {
        //System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model");
        return new UserBean(1, "admin");
    }

    @InitBinder // 针对WebDataBinder的预处理
    public void initBinder(WebDataBinder binder) {
        //System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView handleException(WebRequest request, Exception e) {
        //System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出NoClassDefFoundError异常时执行");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMsg", e.getMessage());
        return modelAndView;
    }
}