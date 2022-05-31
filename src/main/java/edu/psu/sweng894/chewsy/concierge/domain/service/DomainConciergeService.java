package edu.psu.sweng894.chewsy.concierge.domain.service;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import edu.psu.sweng894.chewsy.concierge.domain.repository.RestaurantRepository;

@Service
public class DomainConciergeService implements ConciergeService {
    private final RestaurantRepository restaurantRepository;

    public DomainConciergeService(final RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    
    public JSONArray findRestaurants(String location, int radius) {
        return restaurantRepository.findRestaurants(location, radius);
    }
}
