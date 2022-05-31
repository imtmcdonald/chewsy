package edu.psu.sweng894.chewsy.concierge.infrastructure.repository.GooglePlacesAPI;

import edu.psu.sweng894.chewsy.concierge.domain.repository.RestaurantRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.NoSuchElementException;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.stereotype.Repository;

@Repository
public class GooglePlacesAPIRepository implements RestaurantRepository {
	private String apiKey = System.getenv("GOOGLE_PLACES_API_KEY");

	public JSONArray findRestaurants(String location, int radius) {
		final JSONArray restaurantList = new JSONArray();

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = 
			HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+" + location + "&radius=" + radius + "&fields=formatted_address,name,rating&key=" + apiKey)).build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode()==200) {
                JSONObject restaurants = new JSONObject(response.body().toString());
				JSONArray results = restaurants.getJSONArray("results");
				for (int i = 0; i < results.length(); i++) {  
              
					// store each object in JSONObject  
					JSONObject restaurant = results.getJSONObject(i);
					JSONObject restaurantParsed = new JSONObject();
					  
					// get field value from JSONObject using get() method 
					restaurantParsed.put("NAME", restaurant.get("name"));
					restaurantParsed.put("ADDRESS", restaurant.get("formatted_address"));

					if (restaurant.get("rating")==null) {
						restaurantParsed.put("RATING", "Unknown");
					} else {
						restaurantParsed.put("RATING", restaurant.get("rating"));
					}

					restaurantList.put(restaurantParsed);
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("Unknown");
		}

        return restaurantList;
	}
}
