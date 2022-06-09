package edu.psu.sweng894.chewsy.concierge.application.controller;

import org.springframework.boot.test.context.SpringBootTest;

import edu.psu.sweng894.chewsy.concierge.application.request.FindRestaurantsRequest;
import edu.psu.sweng894.chewsy.concierge.domain.repository.RestaurantRepository;
import edu.psu.sweng894.chewsy.concierge.domain.service.ConciergeService;
import edu.psu.sweng894.chewsy.concierge.domain.service.DomainConciergeService;
import edu.psu.sweng894.chewsy.concierge.infrastructure.repository.GooglePlacesAPI.MockGooglePlacesAPIRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class ConciergeControllerTests {
    public final String location = "23666";
    public final int radius = 5;
    public final RestaurantRepository restaurantRepository = new MockGooglePlacesAPIRepository();
    public final ConciergeService conciergeService = new DomainConciergeService(restaurantRepository);
    public final ConciergeController conciergeController = new ConciergeController(conciergeService);
    public final FindRestaurantsRequest findRestaurantsRequest = new FindRestaurantsRequest(location, radius);

    
    @Test
    public void testFindRestaurants() {
        String restaurantList = conciergeController.findRestaurants(findRestaurantsRequest);
        String actual = "[{\"LOCATION\":\"112 Jefferson Ave, Newport News VA 23601\",\"RATING\":\"3.4\",\"NAME\":\"Burger King\"}]";
        assertEquals(actual, restaurantList);
    }

}
