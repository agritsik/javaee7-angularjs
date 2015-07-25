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
    public void testCategoryCRUD() throws Exception {

        // Create
        Category category = new Category("Type");
        categoryService.create(category);
        assertNotNull(category.getId());

        // Read
        Category savedCategory = categoryService.find(category.getId());
        System.out.println(savedCategory);
        assertNotNull(savedCategory);

        // Read all
        List<Category> categories = categoryService.find();
        System.out.println(categories);
        assertEquals(1, categories.size());

        // Update
        String editedName = "Edited Type";
        category.setName(editedName);
        Category updatedfilterGroup = categoryService.update(category);
        System.out.println(updatedfilterGroup);
        assertEquals(editedName, updatedfilterGroup.getName());

        // Delete
        categoryService.delete(updatedfilterGroup.getId());
        assertNull(categoryService.find(updatedfilterGroup.getId()));


    }

    @Test
    @InSequence(3)
    public void testPropertyCRUD() throws Exception {

        // Create Parent
        Category category = new Category();
        category.setName("brand");
        categoryService.create(category);

        // Create
        Property property = new Property("Nokia");
        propertyService.create(property, category.getId());
        assertNotNull(property.getId());

        // Read
        Property createdProperty = propertyService.find(property.getId(), category.getId());
        System.out.println(createdProperty);
        assertNotNull(createdProperty);

        // Read all
        List<Property> properties = propertyService.find(category.getId());
        assertEquals(1, properties.size());

        // Update
        Property savedProperty = propertyService.find(property.getId(), property.getCategory().getId());
        savedProperty.setName("Nokia 2");
        Property updatedProperty = propertyService.update(property);
        assertEquals(category.getId(), updatedProperty.getCategory().getId());

        // Delete
        propertyService.delete(updatedProperty.getId());
        assertEquals(0, propertyService.find(updatedProperty.getId()).size());

    }


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
        propertyService.create(property1, category.getId());
        propertyService.create(property2, category.getId());
        propertyService.create(property3, category.getId());
        propertyService.create(property4, category.getId());

        // create item
        Item item = new Item("Apple iPhone 5");
        itemService.create(item);

        property1.setCategory(null);
        propertyService.update(property1);


        Configuration configuration1 = new Configuration(item, property1);
        configurationService.create(configuration1);
        Configuration configuration2 = new Configuration(item, property2);
        configurationService.create(configuration2);

        List<Configuration> items1 = configurationService.findByItemId(item.getId());
        System.out.println(items1);
        assertEquals(2, items1.size());

        configurationService.delete(configuration2.getId());

        List<Configuration> items2 = configurationService.findByItemId(item.getId());
        assertEquals(1, items2.size());


    }

}