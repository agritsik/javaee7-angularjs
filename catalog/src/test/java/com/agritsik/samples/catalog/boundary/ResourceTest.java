package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Country;
import com.agritsik.samples.catalog.entity.Career;
import com.agritsik.samples.catalog.entity.Player;
import com.agritsik.samples.catalog.entity.Club;
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
import org.junit.Ignore;
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
    private WebTarget playerResource;
    private WebTarget countryResource;
    private WebTarget clubResource;
    private WebTarget careerResource;

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

        this.playerResource = this.client.target(new URL(url, "resources/players").toExternalForm());
        this.countryResource = this.client.target(new URL(url, "resources/countries").toExternalForm());
        this.clubResource = this.client.target(new URL(url, "resources/clubs").toExternalForm());
        this.careerResource = this.client.target(new URL(url, "resources/careers").toExternalForm());
    }

    @InSequence(1)
    @Test
    public void testItemREST() throws Exception {

        // Create
        Player player = new Player("Samsung Galaxy S6");
        player.setPrice(BigDecimal.valueOf(199.99));
        Response postResponse = this.playerResource.request(MediaType.APPLICATION_JSON).post(Entity.json(player));
        assertEquals(Response.Status.CREATED.getStatusCode(), postResponse.getStatus());
        assertNotNull(postResponse.getLocation());

        // Read
        Player createdPlayer = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).get(Player.class);
        assertEquals(player.getName(), createdPlayer.getName());

        // Read all
        List<Player> players = this.playerResource.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Player>>() {
        });
        assertEquals(1, players.size());

        // Update
        createdPlayer.setPrice(BigDecimal.valueOf(299.99));
        Response putResponse = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).put(Entity.json(createdPlayer));
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
        Country country = new Country("RAM");
        Response postResponse = this.countryResource.request(MediaType.APPLICATION_JSON).post(Entity.json(country));
        assertEquals(Response.Status.CREATED.getStatusCode(), postResponse.getStatus());
        assertNotNull(postResponse.getLocation());

        // Read
        Country createdCountry = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).get(Country.class);
        assertEquals(country.getName(), createdCountry.getName());

        // Read all
        List<Country> countries = this.countryResource.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Country>>() {
        });
        assertEquals(1, countries.size());

        // Update
        createdCountry.setName("RAM Edited");
        Response putResponse = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).put(Entity.json(createdCountry));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), putResponse.getStatus());

        // Delete
        Response deleteResponse = this.client.target(postResponse.getLocation())
                .request(MediaType.APPLICATION_JSON).delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), deleteResponse.getStatus());
    }


    @InSequence(3)
    @Test
    public void testPropertyREST() throws Exception {

        // Create Parent 1 todo: skipped for now
        Country country = new Country("HDD");
        Response parentPostResponse = this.countryResource.request(MediaType.APPLICATION_JSON).post(Entity.json(country));
        URI parentLocation = parentPostResponse.getLocation();

        // Create Parent 2 todo: skipped for now
        Country country2 = new Country("HDD2");
        Response parentPostResponse2 = this.countryResource.request(MediaType.APPLICATION_JSON).post(Entity.json(country2));
        URI parentLocation2 = parentPostResponse2.getLocation();
        Country createdCountry2 = this.client.target(parentLocation2).request(MediaType.APPLICATION_JSON).get(Country.class);


        // Create
        Club club = new Club("100Gb");
        Response postResponse = this.clubResource.request(MediaType.APPLICATION_JSON).post(Entity.json(club));
        assertEquals(Response.Status.CREATED.getStatusCode(), postResponse.getStatus());
        assertNotNull(postResponse.getLocation());
        WebTarget createdPropertyResource = this.client.target(postResponse.getLocation());

        // Read
        Club createdClub = createdPropertyResource.request(MediaType.APPLICATION_JSON).get(Club.class);
        assertEquals(club.getName(), createdClub.getName());
        System.out.println(club);

        // Read all
        List<Club> clubs0 = this.clubResource.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Club>>() {
        });
        assertEquals(1, clubs0.size());

        // Update
        createdClub.setName("100Gb Edited");
        Response putResponse = createdPropertyResource.request(MediaType.APPLICATION_JSON).put(Entity.json(createdClub));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), putResponse.getStatus());

        // Delete
        Response deleteResponse = createdPropertyResource.request(MediaType.APPLICATION_JSON).delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), deleteResponse.getStatus());
    }


    @Ignore
    @Test
    @InSequence(4)
    public void testConfigurationREST() throws Exception {

        // Create Category
        Country country = new Country("HDD");
        Response parentPostResponse = this.countryResource.request(MediaType.APPLICATION_JSON).post(Entity.json(country));
        URI parentLocation = parentPostResponse.getLocation();

        // Create Properties
        Club club1 = new Club("100Gb");
        Club club2 = new Club("300Gb");
        Club club3 = new Club("500Gb");
        Response postResponse1 = this.client.target(parentLocation).path("properties").request(MediaType.APPLICATION_JSON).post(Entity.json(club1));
        Response postResponse2 = this.client.target(parentLocation).path("properties").request(MediaType.APPLICATION_JSON).post(Entity.json(club2));
        Response postResponse3 = this.client.target(parentLocation).path("properties").request(MediaType.APPLICATION_JSON).post(Entity.json(club3));
        Club p1 = this.client.target(postResponse1.getLocation()).request(MediaType.APPLICATION_JSON).get(Club.class);
        Club p2 = this.client.target(postResponse2.getLocation()).request(MediaType.APPLICATION_JSON).get(Club.class);
        Club p3 = this.client.target(postResponse3.getLocation()).request(MediaType.APPLICATION_JSON).get(Club.class);

        // Create Item
        Player player = new Player("Samsung Galaxy S6");
        Response postResponse = this.playerResource.request(MediaType.APPLICATION_JSON).post(Entity.json(player));
        Player createdPlayer = this.client.target(postResponse.getLocation()).request(MediaType.APPLICATION_JSON).get(Player.class);

        // Create relation
        Career career = new Career();
        career.setPlayer(createdPlayer);
        career.setClub(p1);

        Response response = careerResource.request(MediaType.APPLICATION_JSON).post(Entity.json(career));
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertNotNull(response.getLocation());

        // Read relationship
        List<Career> list = careerResource.queryParam("player_id", createdPlayer.getId())
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Career>>() {
                });

        System.out.println(list);
        assertEquals(1, list.size());


        Response deleteResponse = client.target(response.getLocation()).request(MediaType.APPLICATION_JSON).delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), deleteResponse.getStatus());


    }
}