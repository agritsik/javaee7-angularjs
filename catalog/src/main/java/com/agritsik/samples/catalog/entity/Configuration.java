package com.agritsik.samples.catalog.entity;

import javax.persistence.*;

/**
 * Created by andrey on 7/24/15.
 */
@Entity
@Table(name = "configuration")
@NamedQuery(name = Configuration.FIND_BY_ITEM_ID, query = "SELECT c FROM Configuration c WHERE c.item.id=:itemId")
public class Configuration {

    public static final String FIND_BY_ITEM_ID = "Configuration.findByItemId";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Property property;


    public Configuration() {
    }

    public Configuration(Item item, Property property) {
        this.item = item;
        this.property = property;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "id=" + id +
                ", item=" + item +
                ", property=" + property +
                '}';
    }
}
