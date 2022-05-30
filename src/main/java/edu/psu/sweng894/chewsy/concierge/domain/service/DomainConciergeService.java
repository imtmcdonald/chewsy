package edu.psu.sweng894.chewsy.concierge.domain.service;

import org.json.simple.JSONArray;

import edu.psu.sweng894.chewsy.concierge.domain.Request;
import edu.psu.sweng894.chewsy.concierge.domain.repository.RestaurantRepository;

public class DomainConciergeService implements ConciergeService {
    private final RestaurantRepository restaurantRepository;

    public DomainConciergeService(final Request restaurantRequest, final RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    
    public JSONArray findRestaurants(final Request restaurantRequest) {
        String location = restaurantRequest.getAddress();
        int radius = restaurantRequest.getRadius();

        return restaurantRepository.findRestaurants(location, radius);
    }
}
