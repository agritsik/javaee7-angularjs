package com.agritsik.samples.catalog.boundary;

import java.util.List;

/**
 * Created by andrey on 7/21/15.
 *
 * Optional interface that I use only for naming convention for methods for my services
 */
public interface EntityChildService<E> {

    void create(E e, int parentId);

    E find(int id, int parentId);

    List<E> find(int parentId);

    E update(E e);

    void delete(int id);

}
