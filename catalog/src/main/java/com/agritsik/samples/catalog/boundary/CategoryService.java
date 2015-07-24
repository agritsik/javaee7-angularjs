package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Category;

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
public class CategoryService implements EntityService<Category> {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Category category){
        entityManager.persist(category);
    }

    public Category find(int id){
        return entityManager.find(Category.class, id);
    }

    @Override
    public List<Category> find() {
        return entityManager.createNamedQuery(Category.FIND_ALL).getResultList();
    }

    public Category update(Category category){
        return entityManager.merge(category);
    }

    public void delete(int id){
        Category category = entityManager.find(Category.class, id);
        entityManager.remove(category);
    }
}
