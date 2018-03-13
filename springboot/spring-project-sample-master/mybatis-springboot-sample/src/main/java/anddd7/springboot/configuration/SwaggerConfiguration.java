package anddd7.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    /**
     * api查看设置，可以设置多个拦截不同的url
     */
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("anddd7.springboot"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 页面项目描述信息
     */
    private ApiInfo getApiInfo() {
        return new ApiInfo(" API",
                " API, all the applications could access the Object model data via JSON.",
                "0.1",
                "NO terms of service",
                new Contact("author", "", "xxxx@qq.com"),
                "@Copyright",
                ""
        );
    }
}
