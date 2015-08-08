package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Country;

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
@Path("/countries")
public class CountryResource {

    @Context
    UriInfo uriInfo;

    @EJB
    CountryService countryService;

    @POST
    public Response create(Country country){
        countryService.create(country);

        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(country.getId())).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("{id}")
    public Country find(@PathParam("id") int id){
        return countryService.find(id);
    }

    @GET
    public List<Country> find(){
        return countryService.find();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Country country){
        countryService.update(country);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        countryService.delete(id);
        return Response.noContent().build();
    }



}
