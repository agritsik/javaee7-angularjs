package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Country;

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
public class CountryService implements EntityService<Country> {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Country country){
        entityManager.persist(country);
    }

    public Country find(int id){
        return entityManager.find(Country.class, id);
    }

    public List<Country> find() {
        return entityManager.createNamedQuery(Country.FIND_ALL).getResultList();
    }

    public Country update(Country country){
        return entityManager.merge(country);
    }

    public void delete(int id){
        Country country = entityManager.find(Country.class, id);
        entityManager.remove(country);
    }
}
