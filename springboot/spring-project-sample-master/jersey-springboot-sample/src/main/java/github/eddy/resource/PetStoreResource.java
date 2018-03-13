package github.eddy.resource;

import github.eddy.exception.NotFoundException;
import github.eddy.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/store")
@Api(value = "/store", description = "Operations about store")
@Produces({"application/json", "application/xml"})
public interface PetStoreResource {

  @GET
  @Path("/order/{orderId}")
  @ApiOperation(value = "Find purchase order by ID",
      notes = "For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions",
      response = Order.class)
  @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Order not found")})
  Response getOrderById(
      @ApiParam(value = "ID of pet that needs to be fetched", allowableValues = "range[1,10]", required = true) @PathParam("orderId") Long orderId)
      throws NotFoundException;

  @POST
  @Path("/order")
  @ApiOperation(value = "Place an order for a pet",
      response = Order.class)
  @ApiResponses({@ApiResponse(code = 400, message = "Invalid Order")})
  Response placeOrder(
      @ApiParam(value = "order placed for purchasing the pet", required = true) Order order);

  @DELETE
  @Path("/order/{orderId}")
  @ApiOperation(value = "Delete purchase order by ID",
      notes = "For valid response try integer IDs with positive integer value. Negative or non-integer values will generate API errors")
  @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Order not found")})
  Response deleteOrder(
      @ApiParam(value = "ID of the order that needs to be deleted", allowableValues = "range[1,infinity]", required = true)
      @PathParam("orderId") Long orderId);
}
