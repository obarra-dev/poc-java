package com.example.pocjakarta10.moreend.api;

import com.example.pocjakarta10.moreend.model.Product;
import com.example.pocjakarta10.moreend.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
public class ProductResource {

    @Inject
    private ProductService service;

    @GET
    @Produces("application/json")
    public Response getProducts() {
        List<Product> list = service.listAll();
        return Response.ok().status(Response.Status.OK).entity(list).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") Integer id) {
        Product p = service.findById(id);
        return Response.ok().entity(p).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        Product p = service.save(product);
        return Response.ok().status(Response.Status.CREATED).entity(p).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") Integer id, Product product) {
        if (service.findById(id) != null) {
            Product p = service.update(new Product(id, null));
            return Response.ok().status(Response.Status.OK).entity(p).build();
        } else {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") Integer id) {
        if (service.findById(id) != null) {
            service.delete(id);
            return Response.ok().status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
    }
}