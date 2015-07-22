package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Filter;
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
public class FilterService implements EntityChildService<Filter>{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Filter filter, int parentId) {
        FilterGroup filterGroup = entityManager.find(FilterGroup.class, parentId);
        filter.setFilterGroup(filterGroup);

        entityManager.persist(filter);
    }

    @Override
    public Filter find(int id, int parentId) {
        Filter filter = entityManager.find(Filter.class, id);
        return  (filter.getFilterGroup().getId() == parentId) ? filter : null;
    }

    @Override
    public List<Filter> find(int parentId) {
        return entityManager.createNamedQuery(Filter.FIND_ALL_BY_FILTER_GROUP)
                .setParameter("filterGroupId", parentId)
                .getResultList();
    }

    @Override
    public Filter update(Filter filter) {
        return entityManager.merge(filter);
    }

    @Override
    public void delete(int id) {
        Filter filter = entityManager.find(Filter.class, id);
        entityManager.remove(filter);
    }


}
