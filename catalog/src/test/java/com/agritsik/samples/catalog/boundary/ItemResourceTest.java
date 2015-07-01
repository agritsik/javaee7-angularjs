package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Item;
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
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by andrey on 7/1/15.
 */
@RunWith(Arquillian.class)
public class ItemResourceTest extends TestCase {
    public static final Logger LOGGER = Logger.getLogger(ItemResourceTest.class.getName());

    private Client client;
    private WebTarget target;

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
        this.target = this.client.target(new URL(url, "resources/items").toExternalForm());
    }

    @InSequence(1)
    @Test
    public void testREST() throws Exception {

        // Create
        Item item = new Item();
        item.setName("Samsung Galaxy S6");
        item.setPrice(BigDecimal.valueOf(199.99));

        Response postResponse = this.target.request(MediaType.APPLICATION_JSON).post(Entity.json(item));

        assertEquals(Response.Status.CREATED.getStatusCode(), postResponse.getStatus());
        assertNotNull(postResponse.getLocation());

        // Read
        Item createdItem = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).get(Item.class);
        assertEquals(item.getName(), createdItem.getName());

        // Update
        createdItem.setPrice(BigDecimal.valueOf(299.99));
        Response putResponse = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).put(Entity.json(createdItem));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), putResponse.getStatus());

        // Delete
        Response deleteResponse = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), deleteResponse.getStatus());

        // Read all
        List<Item> items = this.target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Item>>() {});
        assertEquals(0, items.size());

    }


}