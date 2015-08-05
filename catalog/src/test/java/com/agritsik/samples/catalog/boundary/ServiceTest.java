package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Category;
import com.agritsik.samples.catalog.entity.Configuration;
import com.agritsik.samples.catalog.entity.Item;
import com.agritsik.samples.catalog.entity.Property;
import junit.framework.TestCase;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by andrey on 6/27/15.
 */
//@Ignore
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
    CategoryService categoryService;

    @EJB
    PropertyService propertyService;

    @EJB
    ConfigurationService configurationService;





    @Test
    @InSequence(1)
    public void testItemCRUD() throws Exception {

        // Create
        Item item = new Item("Samsung Galaxy S6");
        item.setPrice(BigDecimal.valueOf(199.99));
        itemService.create(item);
        assertNotNull(item.getId());

        // Read
        Item createdItem = itemService.find(item.getId());
        System.out.println(createdItem);
        assertNotNull(createdItem);

        // Read all
        List<Item> items = itemService.find();
        System.out.println(items);
        assertEquals(1, items.size());

        // Update
        BigDecimal newPrice = BigDecimal.valueOf(299.99);
        createdItem.setPrice(newPrice);
        Item updatedItem = itemService.update(createdItem);
        System.out.println(updatedItem);
        assertEquals(newPrice, updatedItem.getPrice());

        // Delete
        itemService.delete(updatedItem.getId());
        assertNull(itemService.find(updatedItem.getId()));

    }

    @Test
    @InSequence(2)
    public void testCategoryCRUD() throws Exception {

        // Create
        Category category = new Category("Type");
        categoryService.create(category);
        assertNotNull(category.getId());

        // Read
        Category createdCategory = categoryService.find(category.getId());
        System.out.println(createdCategory);
        assertNotNull(createdCategory);

        // Read all
        List<Category> categories = categoryService.find();
        System.out.println(categories);
        assertEquals(1, categories.size());

        // Update
        String updatedName = "Edited Type";
        createdCategory.setName(updatedName);
        Category updatedCategory = categoryService.update(createdCategory);
        System.out.println(updatedCategory);
        assertEquals(updatedName, updatedCategory.getName());

        // Delete
        categoryService.delete(updatedCategory.getId());
        assertNull(categoryService.find(updatedCategory.getId()));


    }

    @Test
    @InSequence(3)
    public void testPropertyCRUD() throws Exception {

        // Create Parent todo: skipped for now
        Category category = new Category();
        category.setName("brand");
        categoryService.create(category);

        // Create
        Property property = new Property("Nokia");
        propertyService.create(property);
        assertNotNull(property.getId());

        // Read
        Property createdProperty = propertyService.find(property.getId());
        System.out.println(createdProperty);
        assertNotNull(createdProperty);

        // Read all
        List<Property> properties0 = propertyService.find();
        assertEquals(1, properties0.size());

        // Update
        String updatedName = "Nokia 2";
        createdProperty.setName(updatedName);
        Property updatedProperty = propertyService.update(createdProperty);
        assertEquals(updatedName, updatedProperty.getName());

        // Delete
        propertyService.delete(updatedProperty.getId());
        assertEquals(0, propertyService.find().size());

    }


    @Ignore
    @Test
    @InSequence(4)
    public void testConfigurationCRUD() throws Exception {
        // create category
        Category category = new Category("RAM");
        categoryService.create(category);

        // create properties
        Property property1 = new Property("2Gb");
        Property property2 = new Property("4Gb");
        Property property3 = new Property("8Gb");
        Property property4 = new Property("16Gb");
//        propertyService.create(property1, category.getId());
//        propertyService.create(property2, category.getId());
//        propertyService.create(property3, category.getId());
//        propertyService.create(property4, category.getId());

        // create item
        Item item = new Item("Apple iPhone 5");
        itemService.create(item);

        // create relationship
        Configuration configuration1 = new Configuration(item, property1);
        configurationService.create(configuration1);
        assertNotNull(configuration1.getId());

        Configuration configuration2 = new Configuration(item, property2);
        configurationService.create(configuration2);
        assertNotNull(configuration2.getId());

        List<Configuration> items1 = configurationService.findByItemId(item.getId());
        System.out.println(items1);
        assertEquals(2, items1.size());

        configurationService.delete(configuration2.getId());

        List<Configuration> items2 = configurationService.findByItemId(item.getId());
        assertEquals(1, items2.size());

    }

}