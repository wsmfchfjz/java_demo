package anddd7.springboot.filter;

import anddd7.springboot.controller.common.GlobalParm;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

  private static List<String> excludePath = Arrays.asList(
      ".*/public/.*\\.html", //public页面
      ".*/public/(css|fonts|js)/.*", //静态资源
      ".*/public/(defaultLogin|login|register)", //登录注册
      ".*/oraclejet/.*");

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    //包装
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    //获取访问地址
    String url = request.getRequestURL().toString();
    logger.debug("进入拦截器 ,访问 {} ", url);

    //访问的不在排出路径内
    if (!isExcludePath(url)) {
      logger.debug("检查用户登录状态");
      //获取登录用户
      HttpSession session = request.getSession(true);
      Object user = session.getAttribute(GlobalParm.USER_SESSION_KEY);

      //判断是否登陆失效
      if (user == null) {
        logger.debug("登陆失效 ,请重新登录");
        //识别响应头
        String requestType = request.getHeader("X-Requested-With");
        //如果是ajax类型，响应logout给前台
        if (requestType != null && requestType.equals("XMLHttpRequest")) {
          response.setCharacterEncoding("UTF-8");
          response.getWriter().print("登陆失效 ,请重新登录");
          //如果是访问页面 直接返回首页
        } else {
          response.sendRedirect(request.getContextPath() + "/public/login.html");
        }
        return;
      }
    }

    //继续
    chain.doFilter(req, res);
  }

  @Override
  public void destroy() {

  }

  public boolean isExcludePath(String url) {
    return excludePath.stream().anyMatch(pattern -> Pattern.matches(pattern, url));
  }
}


