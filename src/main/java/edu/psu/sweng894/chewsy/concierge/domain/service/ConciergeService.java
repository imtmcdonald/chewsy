package edu.psu.sweng894.chewsy.concierge.domain.service;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

@Service
public interface ConciergeService {
    public JSONArray findRestaurants(String location, int radius);
}