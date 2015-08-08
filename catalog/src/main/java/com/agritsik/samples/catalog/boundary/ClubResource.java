package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Club;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Created by andrey on 7/29/15.
 */
@Path("/clubs")
public class ClubResource {

    @Context
    UriInfo uriInfo;

    @EJB
    ClubService clubService;

    @POST
    public Response create(Club club){
        clubService.create(club);

        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(club.getId())).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("{id}")
    public Club find(@PathParam("id") int id){
        return clubService.find(id);
    }

    @GET
    public List<Club> find(){
        return clubService.find();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Club club){
        clubService.update(club);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        clubService.delete(id);
        return Response.noContent().build();
    }

}
