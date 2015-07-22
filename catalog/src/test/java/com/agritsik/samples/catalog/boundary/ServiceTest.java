package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Filter;
import com.agritsik.samples.catalog.entity.FilterGroup;
import com.agritsik.samples.catalog.entity.Item;
import junit.framework.TestCase;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by andrey on 6/27/15.
 */
@RunWith(Arquillian.class)
public class ServiceTest extends TestCase {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "com.agritsik")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    ItemService itemService;

    @EJB
    FilterGroupService filterGroupService;

    @EJB
    FilterService filterService;

    @Test
    @InSequence(1)
    public void testItemCRUD() throws Exception {

        // Create
        Item item = new Item();
        item.setName("Samsung Galaxy S6");
        item.setPrice(BigDecimal.valueOf(199.99));

        itemService.create(item);
        assertNotNull(item.getId());

        // Read
        Item savedItem = itemService.find(item.getId());
        System.out.println(savedItem);
        assertNotNull(savedItem);

        // Read all
        List<Item> items = itemService.find();
        System.out.println(items);
        assertEquals(1, items.size());

        // Update
        BigDecimal newPrice = BigDecimal.valueOf(299.99);
        savedItem.setPrice(newPrice);
        Item updatedItem = itemService.update(savedItem);
        System.out.println(updatedItem);
        assertEquals(newPrice, updatedItem.getPrice());

        // Delete
        itemService.delete(updatedItem.getId());
        assertNull(itemService.find(updatedItem.getId()));

    }

    @Test
    @InSequence(2)
    public void testFilterGroupCRUD() throws Exception {

        // Create
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.setName("Type");

        filterGroupService.create(filterGroup);
        assertNotNull(filterGroup.getId());

        // Read
        FilterGroup savedFilterGroup = filterGroupService.find(filterGroup.getId());
        System.out.println(savedFilterGroup);
        assertNotNull(savedFilterGroup);

        // Read all
        List<FilterGroup> filterGroups = filterGroupService.find();
        System.out.println(filterGroups);
        assertEquals(1, filterGroups.size());

        // Update
        String editedName = "Edited Type";
        filterGroup.setName(editedName);
        FilterGroup updatedfilterGroup = filterGroupService.update(filterGroup);
        System.out.println(updatedfilterGroup);
        assertEquals(editedName, updatedfilterGroup.getName());

        // Delete
        filterGroupService.delete(updatedfilterGroup.getId());
        assertNull(filterGroupService.find(updatedfilterGroup.getId()));


    }

    @Test
    @InSequence(3)
    public void testFilterCRUD() throws Exception {

        // create filter group
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.setName("brand");
        filterGroupService.create(filterGroup);

        // create filter
        Filter filter = new Filter();
        filter.setName("Nokia");

        filterService.create(filter, filterGroup.getId());
        assertNotNull(filter.getId());

        // find filters by group
        List<Filter> filters = filterService.find(filterGroup.getId());
        assertEquals(1, filters.size());

        // update filter
        Filter savedFilter = filterService.find(filter.getId(), filter.getFilterGroup().getId());
        System.out.println(filter);

        savedFilter.setName("Nokia 2");
        Filter updatedFilter = filterService.update(filter);
        System.out.println(updatedFilter);
        assertEquals(filterGroup.getId(), updatedFilter.getFilterGroup().getId());

        // delete
        filterService.delete(updatedFilter.getId());
        assertEquals(0, filterService.find(updatedFilter.getId()).size());

    }
}