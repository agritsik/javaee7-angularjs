package com.agritsik.samples.catalog.boundary;

import com.agritsik.samples.catalog.entity.Country;
import com.agritsik.samples.catalog.entity.Career;
import com.agritsik.samples.catalog.entity.Player;
import com.agritsik.samples.catalog.entity.Club;
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
    PlayerService playerService;

    @EJB
    CountryService countryService;

    @EJB
    ClubService clubService;

    @EJB
    CareerService careerService;





    @Test
    @InSequence(1)
    public void testItemCRUD() throws Exception {

        // Create
        Player player = new Player("Samsung Galaxy S6");
        player.setPrice(BigDecimal.valueOf(199.99));
        playerService.create(player);
        assertNotNull(player.getId());

        // Read
        Player createdPlayer = playerService.find(player.getId());
        System.out.println(createdPlayer);
        assertNotNull(createdPlayer);

        // Read all
        List<Player> players = playerService.find();
        System.out.println(players);
        assertEquals(1, players.size());

        // Update
        BigDecimal newPrice = BigDecimal.valueOf(299.99);
        createdPlayer.setPrice(newPrice);
        Player updatedPlayer = playerService.update(createdPlayer);
        System.out.println(updatedPlayer);
        assertEquals(newPrice, updatedPlayer.getPrice());

        // Delete
        playerService.delete(updatedPlayer.getId());
        assertNull(playerService.find(updatedPlayer.getId()));

    }

    @Test
    @InSequence(2)
    public void testCategoryCRUD() throws Exception {

        // Create
        Country country = new Country("Type");
        countryService.create(country);
        assertNotNull(country.getId());

        // Read
        Country createdCountry = countryService.find(country.getId());
        System.out.println(createdCountry);
        assertNotNull(createdCountry);

        // Read all
        List<Country> countries = countryService.find();
        System.out.println(countries);
        assertEquals(1, countries.size());

        // Update
        String updatedName = "Edited Type";
        createdCountry.setName(updatedName);
        Country updatedCountry = countryService.update(createdCountry);
        System.out.println(updatedCountry);
        assertEquals(updatedName, updatedCountry.getName());

        // Delete
        countryService.delete(updatedCountry.getId());
        assertNull(countryService.find(updatedCountry.getId()));


    }

    @Test
    @InSequence(3)
    public void testPropertyCRUD() throws Exception {

        // Create Parent todo: skipped for now
        Country country = new Country();
        country.setName("brand");
        countryService.create(country);

        // Create
        Club club = new Club("Nokia");
        clubService.create(club);
        assertNotNull(club.getId());

        // Read
        Club createdClub = clubService.find(club.getId());
        System.out.println(createdClub);
        assertNotNull(createdClub);

        // Read all
        List<Club> properties0 = clubService.find();
        assertEquals(1, properties0.size());

        // Update
        String updatedName = "Nokia 2";
        createdClub.setName(updatedName);
        Club updatedClub = clubService.update(createdClub);
        assertEquals(updatedName, updatedClub.getName());

        // Delete
        clubService.delete(updatedClub.getId());
        assertEquals(0, clubService.find().size());

    }


    @Ignore
    @Test
    @InSequence(4)
    public void testConfigurationCRUD() throws Exception {
        // create category
        Country country = new Country("RAM");
        countryService.create(country);

        // create properties
        Club club1 = new Club("2Gb");
        Club club2 = new Club("4Gb");
        Club club3 = new Club("8Gb");
        Club club4 = new Club("16Gb");
//        propertyService.create(property1, category.getId());
//        propertyService.create(property2, category.getId());
//        propertyService.create(property3, category.getId());
//        propertyService.create(property4, category.getId());

        // create
        Player player = new Player("Apple iPhone 5");
        playerService.create(player);

        // create relationship
        Career career1 = new Career(player, club1);
        careerService.create(career1);
        assertNotNull(career1.getId());

        Career career2 = new Career(player, club2);
        careerService.create(career2);
        assertNotNull(career2.getId());

        List<Career> player1 = careerService.findByPlayerId(player.getId());
        System.out.println(player1);
        assertEquals(2, player1.size());

        careerService.delete(career2.getId());

        List<Career> player2 = careerService.findByPlayerId(player.getId());
        assertEquals(1, player2.size());

    }

}