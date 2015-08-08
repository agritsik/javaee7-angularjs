package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Club;

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
public class ClubService implements EntityService<Club>{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Club club) {
        entityManager.persist(club);
    }

    @Override
    public Club find(int id) {
        return entityManager.find(Club.class, id);
    }

    @Override
    public List<Club> find() {
        return entityManager.createNamedQuery(Club.FIND_ALL).getResultList();
    }

    @Override
    public Club update(Club club) {
        return entityManager.merge(club);
    }

    @Override
    public void delete(int id) {
        Club club = entityManager.find(Club.class, id);
        entityManager.remove(club);
    }
}
