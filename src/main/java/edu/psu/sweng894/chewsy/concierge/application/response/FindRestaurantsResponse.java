package edu.psu.sweng894.chewsy.concierge.application.response;

import org.json.JSONArray;

public class FindRestaurantsResponse {
    private final JSONArray restaurantList;

    public FindRestaurantsResponse(final JSONArray restaurantList) {
        this.restaurantList = restaurantList;
    }

    public JSONArray getRestaurantList() {
        return restaurantList;
    }
}
