package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by andrey on 6/27/15.
 */
@Stateless
public class ItemService {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Item item){
        entityManager.persist(item);
    }

    public Item find(int id){
        return entityManager.find(Item.class, id);
    }

    public Item update(Item item){
        return entityManager.merge(item);
    }

    public void remove(int id){
        Item item = entityManager.find(Item.class, id);
        entityManager.remove(item);
    }

}
