package edu.psu.sweng894.chewsy.concierge.infrastructure.repository.GooglePlacesAPI;

import edu.psu.sweng894.chewsy.concierge.domain.repository.RestaurantRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GooglePlacesAPIRepository implements RestaurantRepository {
    private JSONObject restaurants;
	private JSONArray results;
	private JSONArray restaurantList = new JSONArray();
	private String apiKey = System.getenv("GOOGLE_PLACES_API_KEY");

	public JSONArray findRestaurants(String location, int radius) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = 
			HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+" + location + "&radius=" + radius + "&fields=formatted_address,name,rating&key=" + apiKey)).build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode()==200) {
				JSONParser parser = new JSONParser();
                this.restaurants = (JSONObject) parser.parse(response.body().toString());
				this.results = (JSONArray) this.restaurants.get("results");
				Iterator<JSONObject> iterator = results.iterator();
				while (iterator.hasNext()) {
					JSONObject restaurant = new JSONObject();

					restaurant.put("NAME", iterator.next().get("name"));
					restaurant.put("ADDRESS", iterator.next().get("formatted_address"));
					restaurant.put("RATING", iterator.next().get("rating"));

					restaurantList.add(restaurant);
				}
			}
		} catch (IOException | InterruptedException | ParseException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("Unknown");
		}

        return restaurantList;
	}
}
