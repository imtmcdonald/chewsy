package edu.psu.sweng894.chewsy.concierge.domain.repository;

import org.json.simple.JSONObject;

public interface RestaurantRepository {
    public JSONObject findRestaurants(String location, int radius);
}
