package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Player;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by andrey on 6/27/15.
 */
@Stateless
@LocalBean
public class PlayerService implements EntityService<Player> {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Player player){
        entityManager.persist(player);
    }

    public Player find(int id){
        return entityManager.find(Player.class, id);
    }

    public List<Player> find(){
        return entityManager.createNamedQuery(Player.FIND_ALL).getResultList();
    }

    public Player update(Player player){
        return entityManager.merge(player);
    }

    public void delete(int id){
        Player player = entityManager.find(Player.class, id);
        entityManager.remove(player);
    }

}
