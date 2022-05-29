package edu.psu.sweng894.chewsy.concierge.infrastructure.repository.GooglePlacesAPI;

import edu.psu.sweng894.chewsy.concierge.domain.repository.RestaurantRepository;

import org.junit.Test;

import org.json.simple.JSONArray;

import static org.junit.Assert.*; 

public class GooglePlacesAPIRepositoryTest {
    @Test
    public void testFindRestaurants() {
        RestaurantRepository restaurants = new GooglePlacesAPIRepository();
        JSONArray restaurantList = restaurants.findRestaurants("23666", 5);
        assertNull(restaurantList);
    }
}
