package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Property;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by andrey on 7/21/15.
 */
@Stateless
@LocalBean
public class PropertyService implements EntityService<Property>{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Property property) {
        entityManager.persist(property);
    }

    @Override
    public Property find(int id) {
        return entityManager.find(Property.class, id);
    }

    @Override
    public List<Property> find() {
        return entityManager.createNamedQuery(Property.FIND_ALL).getResultList();
    }

    @Override
    public Property update(Property property) {
        return entityManager.merge(property);
    }

    @Override
    public void delete(int id) {
        Property property = entityManager.find(Property.class, id);
        entityManager.remove(property);
    }
}
