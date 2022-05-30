package edu.psu.sweng894.chewsy.concierge.domain.service;

import org.json.simple.JSONArray;

import edu.psu.sweng894.chewsy.concierge.domain.Request;

public interface ConciergeService {
    public JSONArray findRestaurants(Request restaurantRequest);
}