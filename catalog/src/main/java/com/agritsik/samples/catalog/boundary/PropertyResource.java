package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Property;

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

@Path("/categories/{category_id}/properties")
public class PropertyResource {


    @Context
    UriInfo uriInfo;

    @EJB
    PropertyService propertyService;


    @POST
    public Response create(@PathParam("category_id") int parentId, Property property){
        propertyService.create(property, parentId);

        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(property.getId())).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("{id}")
    public Property find(@PathParam("category_id") int parentId, @PathParam("id") int id){
        return propertyService.find(id, parentId);
    }

    @GET
    public List<Property> find(@PathParam("category_id") int parentId){
        return propertyService.find(parentId);
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Property property){
        propertyService.update(property);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        propertyService.delete(id);
        return Response.noContent().build();
    }

}
