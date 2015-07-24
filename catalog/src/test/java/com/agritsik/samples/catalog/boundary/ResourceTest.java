package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Category;
import com.agritsik.samples.catalog.entity.Item;
import com.agritsik.samples.catalog.entity.Property;
import junit.framework.TestCase;
import org.glassfish.jersey.filter.LoggingFilter;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by andrey on 7/1/15.
 */

//@Ignore
@RunWith(Arquillian.class)
public class ResourceTest extends TestCase {
    public static final Logger LOGGER = Logger.getLogger(ResourceTest.class.getName());

    private Client client;
    private WebTarget targetItems;
    private WebTarget targetCategories;

    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "com.agritsik")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @ArquillianResource
    URL url;

    @Before
    public void setUp() throws Exception {
        this.client = ClientBuilder.newClient();
        this.client.register(new LoggingFilter(LOGGER, true));

        this.targetItems = this.client.target(new URL(url, "resources/items").toExternalForm());
        this.targetCategories = this.client.target(new URL(url, "resources/categories").toExternalForm());
    }

    @InSequence(1)
    @Test
    public void testItemREST() throws Exception {

        // Create
        Item item = new Item("Samsung Galaxy S6");
        item.setPrice(BigDecimal.valueOf(199.99));
        Response postResponse = this.targetItems.request(MediaType.APPLICATION_JSON).post(Entity.json(item));
        assertEquals(Response.Status.CREATED.getStatusCode(), postResponse.getStatus());
        assertNotNull(postResponse.getLocation());

        // Read
        Item createdItem = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).get(Item.class);
        assertEquals(item.getName(), createdItem.getName());

        // Read all
        List<Item> items = this.targetItems.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Item>>() {
        });
        assertEquals(1, items.size());

        // Update
        createdItem.setPrice(BigDecimal.valueOf(299.99));
        Response putResponse = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).put(Entity.json(createdItem));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), putResponse.getStatus());

        // Delete
        Response deleteResponse = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), deleteResponse.getStatus());
    }

    @InSequence(2)
    @Test
    public void testCategoryREST() throws Exception {

        // Create
        Category category = new Category("RAM");
        Response postResponse = this.targetCategories.request(MediaType.APPLICATION_JSON).post(Entity.json(category));
        assertEquals(Response.Status.CREATED.getStatusCode(), postResponse.getStatus());
        assertNotNull(postResponse.getLocation());

        // Read
        Category createdCategory = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).get(Category.class);
        assertEquals(category.getName(), createdCategory.getName());

        // Read all
        List<Category> categories = this.targetCategories.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Category>>() {
        });
        assertEquals(1, categories.size());

        // Update
        createdCategory.setName("RAM Edited");
        Response putResponse = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).put(Entity.json(createdCategory));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), putResponse.getStatus());

        // Delete
        Response deleteResponse = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), deleteResponse.getStatus());
    }


    @InSequence(3)
    @Test
    public void testPropertyREST() throws Exception {

        // Create Parent
        Category category = new Category("HDD");
        Response parentPostResponse = this.targetCategories.request(MediaType.APPLICATION_JSON).post(Entity.json(category));
        URI parentLocation = parentPostResponse.getLocation();


        // Create
        Property property = new Property("100Gb");
        Response postResponse = this.client.target(parentLocation).path("properties").request(MediaType.APPLICATION_JSON).post(Entity.json(property));
        assertEquals(Response.Status.CREATED.getStatusCode(), postResponse.getStatus());
        assertNotNull(postResponse.getLocation());

        // Read
        Property createdProperty = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).get(Property.class);
        assertEquals(property.getName(), createdProperty.getName());

        // Read all
        List<Property> properties = this.client.target(parentLocation).path("properties").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Property>>() {
        });
        assertEquals(1, properties.size());

        // Update
        createdProperty.setName("100Gb Edited");
        Response putResponse = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).put(Entity.json(createdProperty));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), putResponse.getStatus());

        // Delete
        Response deleteResponse = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), deleteResponse.getStatus());
    }


}