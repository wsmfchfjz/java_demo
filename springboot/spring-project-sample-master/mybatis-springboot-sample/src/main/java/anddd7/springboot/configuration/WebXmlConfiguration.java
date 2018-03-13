package anddd7.springboot.configuration;


import anddd7.springboot.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Filter;

/**
 * 对应web.xml中的所有设置
 */
@Configuration
public class WebXmlConfiguration {


    @Autowired
    DispatcherServlet dispatcherServlet;

    /*
    新的springboot不需要再设置servlet -> DispatcherServlet ,默认会拦截请求交给DispatcherServlet

    @Bean
    public ServletRegistrationBean defaultServletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(dispatcherServlet);
        registrationBean.addUrlMappings("*.ajax");
        return registrationBean;
    }
    */

    @Bean(name = "LoginFilter")
    public Filter getLoginFilter() {
        return new LoginFilter();
    }

    @Bean
    public FilterRegistrationBean loginFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(getLoginFilter());
        registrationBean.setName("LoginFilter");
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
