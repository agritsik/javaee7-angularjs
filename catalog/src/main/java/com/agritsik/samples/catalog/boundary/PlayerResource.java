package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Player;

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

@Path("/players")
public class PlayerResource {

    @Context
    UriInfo uriInfo;

    @EJB
    PlayerService playerService;

    @POST
    public Response create(Player player) {
        playerService.create(player);

        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(player.getId())).build();
        return Response.created(uri).build();
    }

//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @GET
    @Path("{id}")
    public Player find(@PathParam("id") int id) {
        return playerService.find(id);
    }

    @GET
    public List<Player> find() {
        return playerService.find();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Player player) {
        playerService.update(player);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        playerService.delete(id);
        return Response.noContent().build();
    }
}
