package edu.psu.sweng894.chewsy.concierge.infrastructure.repository.GooglePlacesAPI;

import edu.psu.sweng894.chewsy.concierge.domain.repository.RestaurantRepository;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;

import org.junit.jupiter.api.Test;
import org.json.JSONArray;

import static org.junit.Assert.*;

public class GooglePlacesAPIRepositoryTest {
    public final String location = "23666";
    public final int radius = 5;
    public final EnvironmentVariables env = new EnvironmentVariables();

    @Test
    public void testFindRestaurantsNotNull() {
        RestaurantRepository restaurants = new GooglePlacesAPIRepository();
        JSONArray restaurantList = restaurants.findRestaurants(location, radius);
        assertNotNull(restaurantList);
    }  
}
