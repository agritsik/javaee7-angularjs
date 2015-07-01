package com.agritsik.samples.catalog.boundary;

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
public class ItemServiceTest extends TestCase {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "com.agritsik")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    ItemService itemService;

    @Test
    @InSequence(1)
    public void testCRUD() throws Exception {

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

        // Update
        BigDecimal newPrice = BigDecimal.valueOf(299.99);
        savedItem.setPrice(newPrice);
        Item updatedItem = itemService.update(savedItem);
        System.out.println(updatedItem);
        assertEquals(newPrice, updatedItem.getPrice());

        // Delete
        itemService.delete(updatedItem.getId());
        assertNull(itemService.find(updatedItem.getId()));

        // Read all
        List<Item> items = itemService.find();
        System.out.println(items);
        assertEquals(0, items.size());


    }

}