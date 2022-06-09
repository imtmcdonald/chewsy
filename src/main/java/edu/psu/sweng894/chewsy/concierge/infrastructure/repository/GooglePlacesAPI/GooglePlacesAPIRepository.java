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
import org.json.JSONException;
import org.springframework.stereotype.Repository;

@Repository
public class GooglePlacesAPIRepository implements RestaurantRepository {
	private String apiKey = System.getenv("GOOGLE_PLACES_API_KEY");

	public JSONArray findRestaurants(String location, int radius) {
		final JSONArray restaurantList = new JSONArray();

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = 
			HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+" + location + "&radius=" + radius + "&key=" + apiKey)).build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode()==200) {

				JSONObject jsonResponse = new JSONObject(response.body());
				JSONArray results = new JSONObject(response.body().toString()).getJSONArray("results");
				buildList(results, restaurantList);

				int i = 0;

				System.out.println(new JSONObject(response.body().toString()).getString("next_page_token"));

				while(jsonResponse.has("next_page_token")) {
					Thread.sleep(2000);
					String pageToken = new JSONObject(response.body().toString()).getString("next_page_token");

					request = 
						HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/textsearch/json?pagetoken=" + pageToken + "&key=" + apiKey)).build();
					
					response = client.send(request, BodyHandlers.ofString());

					i = i + 1;

					if (response.statusCode()==200) {
						JSONArray nextPageResults = new JSONObject(response.body().toString()).getJSONArray("results");
						buildList(nextPageResults, restaurantList);

						jsonResponse = new JSONObject(response.body());

					}

					if(i >= 4) {
						break;
					}
				}		
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("Unknown");
		} catch (JSONException e) {
			e.printStackTrace();
		}

        return restaurantList;
	}

	private void buildList(JSONArray results, JSONArray restaurantList) {
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
}
