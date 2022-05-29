package edu.psu.sweng894.chewsy.concierge.infrastructure.repository.GooglePlacesAPI;

import edu.psu.sweng894.chewsy.concierge.domain.repository.RestaurantRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class GooglePlacesAPIRepository implements RestaurantRepository {
    private JSONArray restaurants;

	public JSONArray findRestaurants(String location, int radius, String apiKey) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = 
			HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+" + location + "&radius=" + radius + "&fields=formatted_address%2Cname%2Crating&key=" + apiKey)).build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode()==200) {
				System.out.println(response.body());
                Object result = JSONValue.parse(response.toString());
                this.restaurants = (JSONArray)result;
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

        return restaurants;
	}
}
