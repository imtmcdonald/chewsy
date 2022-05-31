package edu.psu.sweng894.chewsy.concierge.application.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import edu.psu.sweng894.chewsy.concierge.application.request.FindRestaurantsRequest;
import edu.psu.sweng894.chewsy.concierge.application.response.FindRestaurantsResponse;
import edu.psu.sweng894.chewsy.concierge.domain.service.ConciergeService;

@RestController
@RequestMapping("/find")
public class ConciergeController {
    private final ConciergeService conciergeService;
    
    @Autowired
    public ConciergeController(ConciergeService conciergeService) {
        this.conciergeService = conciergeService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String findRestaurants(@RequestBody final FindRestaurantsRequest findRestaurantsRequest) {
        final JSONArray restaurantList = conciergeService.findRestaurants(findRestaurantsRequest.getLocation(), findRestaurantsRequest.getRadius());

        return new FindRestaurantsResponse(restaurantList).getRestaurantList().toString();
    }
}