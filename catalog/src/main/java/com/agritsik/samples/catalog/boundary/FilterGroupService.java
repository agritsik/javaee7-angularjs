package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.FilterGroup;

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
public class FilterGroupService implements EntityService<FilterGroup> {

    @PersistenceContext
    EntityManager entityManager;

    public void create(FilterGroup filterGroup){
        entityManager.persist(filterGroup);
    }

    public FilterGroup find(int id){
        return entityManager.find(FilterGroup.class, id);
    }

    public FilterGroup update(FilterGroup filterGroup){
        return entityManager.merge(filterGroup);
    }

    public void delete(int id){
        FilterGroup filterGroup = entityManager.find(FilterGroup.class, id);
        entityManager.remove(filterGroup);
    }

    @Override
    public List<FilterGroup> find() {
        return entityManager.createNamedQuery(FilterGroup.FIND_ALL).getResultList();
    }
}
