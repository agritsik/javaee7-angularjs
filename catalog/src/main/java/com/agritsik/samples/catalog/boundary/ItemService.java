package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Item;

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
public class ItemService implements EntityService<Item> {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Item item){
        entityManager.persist(item);
    }

    public Item find(int id){
        return entityManager.find(Item.class, id);
    }

    public Item findWithProperties(int id){
        Item item = entityManager.find(Item.class, id);
//        item.getPropertyList().size(); // resolves n+1 problem
        return item;
    }

    public List<Item> find(){
        return entityManager.createNamedQuery("Item.findAll").getResultList();
    }

    public Item update(Item item){
        return entityManager.merge(item);
    }

    public void delete(int id){
        Item item = entityManager.find(Item.class, id);
        entityManager.remove(item);
    }

}
