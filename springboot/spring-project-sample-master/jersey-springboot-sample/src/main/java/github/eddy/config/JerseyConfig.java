package github.eddy.config;

import github.eddy.resource.HelloResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import javax.annotation.PostConstruct;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

  @Value("${spring.jersey.application-path:/}")
  private String apiPath;


  public JerseyConfig() {
    this.registerEndpoints();
  }

  @PostConstruct
  public void init() {
    this.configureSwagger();
  }

  private void registerEndpoints() {
    register(HelloResource.class);
    register(io.swagger.jaxrs.listing.ApiListingResource.class);
    register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
    property(ServletProperties.FILTER_FORWARD_ON_404, true);
  }

  private void configureSwagger() {
    // Available at localhost:port/swagger.json
    this.register(ApiListingResource.class);
    this.register(SwaggerSerializers.class);

    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setConfigId("springboot-jersey-swagger-sample");
    beanConfig.setTitle("Spring Boot + Jersey + Swagger Sample");
    beanConfig.setVersion("v1");
    beanConfig.setSchemes(new String[]{"http"});
    //beanConfig.setHost("localhost:8002");
    beanConfig.setBasePath(this.apiPath);
    beanConfig.setResourcePackage("github.eddy");
    beanConfig.setContact("Eddy");
    beanConfig.setPrettyPrint(true);
    beanConfig.setScan(true);
  }
}