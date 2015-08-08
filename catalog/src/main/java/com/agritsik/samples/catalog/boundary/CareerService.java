package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Career;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by andrey on 7/24/15.
 */

@Stateless
public class CareerService {

    @PersistenceContext
    EntityManager entityManager;


    public void create(Career career){
        entityManager.persist(career);
    }

    public List<Career> findByPlayerId(int playerId){
        return entityManager.createNamedQuery(Career.FIND_BY_PLAYER_ID).setParameter("playerId", playerId).getResultList();
    }

    public void delete(int id){
        Career career = entityManager.find(Career.class, id);
        entityManager.remove(career);
    }
}
