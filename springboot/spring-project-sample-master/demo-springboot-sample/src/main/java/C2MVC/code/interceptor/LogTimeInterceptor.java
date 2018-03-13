package C2MVC.code.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring 拦截器
 */
public class LogTimeInterceptor extends HandlerInterceptorAdapter {
    static final String START_TIME = "START_TIME";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME, startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute(START_TIME);
        request.removeAttribute(START_TIME);
        long endTime = System.currentTimeMillis();
        System.out.println("本次请求处理时间:" + new Long(endTime - startTime) + "ms");
    }
}
