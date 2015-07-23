package com.agritsik.samples.catalog.entity;

import javax.persistence.*;

/**
 * Created by andrey on 7/21/15.
 */
@Entity
@Table(name = "categories")
@NamedQuery(name = Category.FIND_ALL, query = "SELECT c FROM Category c")
public class Category {

    public static final String FIND_ALL = "Category.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
