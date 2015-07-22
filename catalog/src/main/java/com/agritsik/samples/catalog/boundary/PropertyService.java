package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Property;
import com.agritsik.samples.catalog.entity.Category;

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
public class PropertyService implements EntityChildService<Property>{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Property property, int parentId) {
        Category category = entityManager.find(Category.class, parentId);
        property.setCategory(category);

        entityManager.persist(property);
    }

    @Override
    public Property find(int id, int parentId) {
        Property property = entityManager.find(Property.class, id);
        return  (property.getCategory().getId() == parentId) ? property : null;
    }

    @Override
    public List<Property> find(int parentId) {
        return entityManager.createNamedQuery(Property.FIND_ALL_BY_CATEGORY)
                .setParameter("categoryId", parentId)
                .getResultList();
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
