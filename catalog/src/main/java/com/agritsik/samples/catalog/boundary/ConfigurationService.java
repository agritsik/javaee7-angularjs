package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Item;
import com.agritsik.samples.catalog.entity.Property;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by andrey on 7/24/15.
 */

@Stateless
public class ConfigurationService {

    @PersistenceContext
    EntityManager entityManager;


    public void create(int itemId, int propertyId){

        Item item = entityManager.find(Item.class, itemId);
        Property property = entityManager.find(Property.class, propertyId);

        item.getPropertyList().add(property);
        entityManager.persist(item);

    }

    public void delete(int itemId, int propertyId){

        Item item = entityManager.find(Item.class, itemId);
        Property property = entityManager.find(Property.class, propertyId);

        item.getPropertyList().remove(property);

    }
}
