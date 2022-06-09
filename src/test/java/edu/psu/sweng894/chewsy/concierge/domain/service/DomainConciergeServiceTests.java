package edu.psu.sweng894.chewsy.concierge.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.psu.sweng894.chewsy.concierge.domain.repository.RestaurantRepository;
import edu.psu.sweng894.chewsy.concierge.infrastructure.repository.GooglePlacesAPI.MockGooglePlacesAPIRepository;

@SpringBootTest
public class DomainConciergeServiceTests {
    public final RestaurantRepository restaurantRepository = new MockGooglePlacesAPIRepository();
    public final ConciergeService conciergeService = new DomainConciergeService(restaurantRepository);
    public final String location = "23666";
    public final int radius = 5;

    @Test
    public void testFindRestaurants_validateName() {
        JSONArray restaurantList = conciergeService.findRestaurants(location, radius);
        assertEquals("Burger King", restaurantList.getJSONObject(0).getString("NAME"));
    }

    @Test
    public void testFindRestaurants_validateLocation() {
        JSONArray restaurantList = conciergeService.findRestaurants(location, radius);
        assertEquals("112 Jefferson Ave, Newport News VA 23601", restaurantList.getJSONObject(0).getString("LOCATION"));
    }

    @Test
    public void testFindRestaurants_validateRating() {
        JSONArray restaurantList = conciergeService.findRestaurants(location, radius);
        assertEquals(3.4, Double.parseDouble(restaurantList.getJSONObject(0).getString("RATING")));
    }
}
