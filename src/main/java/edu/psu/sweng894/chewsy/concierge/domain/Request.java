package edu.psu.sweng894.chewsy.concierge.domain;

public class Request {
    private String address;
    private int radius;

    public Request(final String address, final int radius) {
        this.address = address;
        this.radius = radius;
    }

    public String getAddress() {
        return address;
    }

    public int getRadius() {
        return radius;
    }
    
}
