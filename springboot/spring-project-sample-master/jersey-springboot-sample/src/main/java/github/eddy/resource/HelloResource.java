package github.eddy.resource;

import github.eddy.model.ResponseWrapper;
import github.eddy.services.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Hello resource", produces = "application/json")
public class HelloResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(HelloResource.class);

  @Autowired
  HelloService helloService;

  @GET
  @Path("hello/{name}")
  @ApiOperation(value = "Gets a hello resource. Version 1 - (version in URL)", response = String.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Hello resource found"),
      @ApiResponse(code = 404, message = "Hello resource not found")
  })
  public ResponseWrapper hello() {
    return new ResponseWrapper(4, helloService.hello());
  }

  public ResponseWrapper welcome(@ApiParam @PathParam("name") String name) {
    return new ResponseWrapper(4, helloService.welcome(name));
  }
}