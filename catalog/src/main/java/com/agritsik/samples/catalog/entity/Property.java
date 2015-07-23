package com.agritsik.samples.catalog.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by andrey on 7/21/15.
 */

@Entity
@Table(name = "properties")
@NamedQueries({
        @NamedQuery(name = Property.FIND_ALL, query = "SELECT p FROM Property p"),
        @NamedQuery(name = Property.FIND_ALL_BY_CATEGORY, query = "SELECT p FROM Property p WHERE p.category.id=:categoryId")
})
public class Property {

    public static final String FIND_ALL = "Property.findAll";
    public static final String FIND_ALL_BY_CATEGORY = "Property.findAllByCategory";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    Category category;

    private String name;

    public Property() {
    }

    public Property(String name) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                '}';
    }
}
