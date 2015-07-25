package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Configuration;

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

@Path("configuration")
public class ConfigurationResource {

    @Context
    UriInfo uriInfo;

    @EJB
    ConfigurationService configurationService;

    @POST
    public Response create(Configuration configuration){
        configurationService.create(configuration);

        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(configuration.getId())).build();
        return Response.created(uri).build();
    }

    @GET
    public List<Configuration> findByItemId(@QueryParam("item_id") int itemId){
        return configurationService.findByItemId(itemId);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id){
        configurationService.delete(id);
    }


}
