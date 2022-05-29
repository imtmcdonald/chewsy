package edu.psu.sweng894.chewsy.concierge.infrastructure.repository.GooglePlacesAPI;

import edu.psu.sweng894.chewsy.concierge.domain.repository.RestaurantRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GooglePlacesAPIRepository implements RestaurantRepository {
    private JSONObject restaurants;
	private String apiKey = System.getenv("GOOGLE_PLACES_API_KEY");

	public JSONObject findRestaurants(String location, int radius) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = 
			HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+" + location + "&radius=" + radius + "&fields=formatted_address%2Cname%2Crating&key=" + apiKey)).build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode()==200) {
				JSONParser parser = new JSONParser();
                this.restaurants = (JSONObject) parser.parse(response.body().toString());
			}
		} catch (IOException | InterruptedException | ParseException e) {
			e.printStackTrace();
		}

        return restaurants;
	}
}
