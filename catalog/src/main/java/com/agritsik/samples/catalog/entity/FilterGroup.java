package com.agritsik.samples.catalog.entity;

import javax.persistence.*;

/**
 * Created by andrey on 7/21/15.
 */
@Entity
@Table(name = "filter_groups")
@NamedQuery(name = FilterGroup.FIND_ALL, query = "SELECT fg FROM FilterGroup fg")
public class FilterGroup {

    public static final String FIND_ALL = "FilterGroup.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

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
        return "FilterGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
