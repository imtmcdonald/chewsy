package edu.psu.sweng894.chewsy.concierge.domain.repository;

import org.json.simple.JSONArray;

public interface RestaurantRepository {
    public JSONArray findRestaurants(String location, int radius, String apiKey);
}
