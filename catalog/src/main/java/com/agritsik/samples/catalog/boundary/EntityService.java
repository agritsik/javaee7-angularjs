package com.agritsik.samples.catalog.boundary;

import java.util.List;

/**
 * Created by andrey on 7/21/15.
 *
 * Optional interface that I use only for naming convention for methods for my services
 */
public interface EntityService<E> {

    void create(E e);

    E find(int id);

    E update(E e);

    void delete(int id);

    List<E> find();

}
