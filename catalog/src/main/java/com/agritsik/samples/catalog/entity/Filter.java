package com.agritsik.samples.catalog.entity;

import javax.persistence.*;

/**
 * Created by andrey on 7/21/15.
 */

@Entity
@Table(name = "filters")
@NamedQueries({
        @NamedQuery(name = Filter.FIND_ALL, query = "SELECT f FROM Filter f"),
        @NamedQuery(name = Filter.FIND_ALL_BY_FILTER_GROUP, query = "SELECT f FROM Filter f WHERE f.filterGroup.id=:filterGroupId")
})

public class Filter {

    public static final String FIND_ALL = "Filter.findAll";
    public static final String FIND_ALL_BY_FILTER_GROUP = "Filter.findAllByFilterGroup";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    FilterGroup filterGroup;

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

    public FilterGroup getFilterGroup() {
        return filterGroup;
    }

    public void setFilterGroup(FilterGroup filterGroup) {
        this.filterGroup = filterGroup;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
