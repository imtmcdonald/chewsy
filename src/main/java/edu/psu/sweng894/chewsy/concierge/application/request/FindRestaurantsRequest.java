package edu.psu.sweng894.chewsy.concierge.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

public class FindRestaurantsRequest {
    @NonNull private String location;
    @NonNull private int radius;

    @JsonCreator
    public FindRestaurantsRequest(@JsonProperty("location") @NotNull final String location, @JsonProperty("radius") @NotNull final int radius) {
        this.location = location;
        this.radius = radius;
    }

    public String getLocation() {
        return location;
    }

    public int getRadius() {
        return radius;
    }
}