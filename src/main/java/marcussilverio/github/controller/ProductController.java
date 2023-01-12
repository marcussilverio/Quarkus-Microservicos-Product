package marcussilverio.github.controller;

import marcussilverio.github.dto.Error;
import marcussilverio.github.dto.ProductDto;
import marcussilverio.github.service.ProductService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {
  @Inject
  ProductService service;

  @GET
  public Response getAllProducts(){
    try{
      return Response.ok(service.getAllProducts()).build();
    }catch (Exception err){
      err.printStackTrace();
      return Response.serverError().build();
    }
  }
  @GET
  @Path("/{id}")
  public ProductDto findProductById(@PathParam("id") Long id){
    return service.findProductById(id);
  }
  @PUT
  @Path("/{id}")
  @Transactional
  public Response changeProduct(@PathParam("id") Long id, ProductDto dto){
    try{
      service.changeProduct(id, dto);
      return Response.accepted().build();
    }catch (Exception err){
      err.printStackTrace();
      if(err.getMessage().equals("Constraint violation error"))
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("Field required not provided")).build();
      if(err.getMessage().equals("Not Found"))
        return Response.status(Response.Status.NOT_FOUND).build();
      return Response.serverError().build();
    }
  }
  @POST
  @Transactional
  public Response createNewProduct(ProductDto dto){
    try{
      service.createNewProduct(dto);
      return Response.status(Response.Status.CREATED).build();
    }catch (Exception err){
      err.printStackTrace();
      if(err.getMessage().equals("Constraint violation error"))
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("Field required not provided")).build();
      return Response.serverError().build();
    }
  }
  @DELETE
  @Path("{id}")
  @Transactional
  public Response deleteProduct(@PathParam("id") Long id){
    try{
      service.removeProduct(id);
      return Response.noContent().build();
    }catch (Exception err){
      err.printStackTrace();
      return Response.serverError().build();
    }
  }
}
