package com.agritsik.samples.catalog.entity;

import javax.persistence.*;

/**
 * Created by andrey on 7/21/15.
 */
@Entity
@Table(name = "countries")
@NamedQuery(name = Country.FIND_ALL, query = "SELECT c FROM Country c")
public class Country {

    public static final String FIND_ALL = "Country.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    public Country() {
    }

    public Country(String name) {
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
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
