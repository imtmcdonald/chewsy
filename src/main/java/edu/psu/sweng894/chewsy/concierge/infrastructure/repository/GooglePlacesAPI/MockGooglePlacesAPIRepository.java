package edu.psu.sweng894.chewsy.concierge.infrastructure.repository.GooglePlacesAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.psu.sweng894.chewsy.concierge.domain.repository.RestaurantRepository;

public class MockGooglePlacesAPIRepository implements RestaurantRepository {
    public JSONArray findRestaurants(String location, int radius) {
        JSONObject jo = new JSONObject();
        jo.put("NAME", "Burger King");
        jo.put("RATING", "3.4");
        jo.put("LOCATION", "112 Jefferson Ave, Newport News VA 23601");

        JSONArray restaurantList = new JSONArray();
        restaurantList.put(jo);

        return restaurantList;
    }
}
