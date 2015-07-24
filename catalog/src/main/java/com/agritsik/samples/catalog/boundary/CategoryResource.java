package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Category;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Created by andrey on 7/24/15.
 */
@Path("/categories")
public class CategoryResource {

    @Context
    UriInfo uriInfo;

    @EJB
    CategoryService categoryService;

    @POST
    public Response create(Category category){
        categoryService.create(category);

        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(category.getId())).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("{id}")
    public Category find(@PathParam("id") int id){
        return categoryService.find(id);
    }

    @GET
    public List<Category> find(){
        return categoryService.find();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Category category){
        categoryService.update(category);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        categoryService.delete(id);
        return Response.noContent().build();
    }



}
