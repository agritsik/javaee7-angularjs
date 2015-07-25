package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Configuration;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by andrey on 7/24/15.
 */

@Stateless
public class ConfigurationService {

    @PersistenceContext
    EntityManager entityManager;


    public void create(Configuration configuration){
        entityManager.persist(configuration);
    }

    public List<Configuration> findByItemId(int itemId){
        return entityManager.createNamedQuery(Configuration.FIND_BY_ITEM_ID).setParameter("itemId", itemId).getResultList();
    }

    public void delete(int id){
        Configuration configuration = entityManager.find(Configuration.class, id);
        entityManager.remove(configuration);
    }
}
