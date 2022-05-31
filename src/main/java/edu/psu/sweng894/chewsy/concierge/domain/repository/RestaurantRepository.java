package edu.psu.sweng894.chewsy.concierge.domain.repository;

import org.json.JSONArray;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository {
    public JSONArray findRestaurants(String location, int radius);
}
