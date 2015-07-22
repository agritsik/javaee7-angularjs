package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Filter;
import com.agritsik.samples.catalog.entity.FilterGroup;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by andrey on 7/21/15.
 */
@Stateless
public class FilterService {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Filter filter, int filterGroupId){
        FilterGroup filterGroup = entityManager.find(FilterGroup.class, filterGroupId);
        filter.setFilterGroup(filterGroup);

        entityManager.persist(filter);
    }

    public List<Filter> find(int filterGroupId){
        return entityManager.createNamedQuery(Filter.FIND_ALL_BY_FILTER_GROUP)
                .setParameter("filterGroupId", filterGroupId)
                .getResultList();
    }

}
