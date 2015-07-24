package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Item;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Created by andrey on 7/1/15.
 */

@Path("/items")
public class ItemResource {

    @Context
    UriInfo uriInfo;

    @EJB
    ItemService itemService;

    @POST
    public Response create(Item item){
        itemService.create(item);

        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(item.getId())).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("{id}")
    public Item find(@PathParam("id") int id){
        return itemService.find(id);
    }

    @GET
    public List<Item> find(){
        return itemService.find();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Item item){
        itemService.update(item);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        itemService.delete(id);
        return Response.noContent().build();
    }
}
