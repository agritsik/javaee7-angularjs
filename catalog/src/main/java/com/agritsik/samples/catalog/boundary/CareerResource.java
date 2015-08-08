package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Career;

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

@Path("/careers")
public class CareerResource {

    @Context
    UriInfo uriInfo;

    @EJB
    CareerService careerService;

    @POST
    public Response create(Career career){
        careerService.create(career);

        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(career.getId())).build();
        return Response.created(uri).build();
    }

    @GET
    public List<Career> findByPlayerId(@QueryParam("player_id") int playerId){
        return careerService.findByPlayerId(playerId);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        careerService.delete(id);
        return Response.noContent().build();
    }


}
