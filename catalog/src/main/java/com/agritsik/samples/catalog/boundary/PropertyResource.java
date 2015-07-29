package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Property;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by andrey on 7/29/15.
 */
@Path("/properties")
public class PropertyResource {

    @EJB
    PropertyService propertyService;


    @GET
    public List<Property> find(){
        return propertyService.find();
    }

}
