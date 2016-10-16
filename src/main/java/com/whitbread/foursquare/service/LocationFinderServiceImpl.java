package com.whitbread.foursquare.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.whitbread.foursquare.model.Location;

@Service
public class LocationFinderServiceImpl implements LocationFinderService
{

    /**
     * Client Id for foursquare
     */
    private String CLIENT_ID = "2YOLMASACQYB1NQK2H22KS2VEYVF50WT2FMXPNA3B31UBVXF";

    /**
     * Client Secret for foursquare
     */
    private String CLIENT_SECRET = "CQPPNDNS4XHEGTXPUM0IPTRNDQHGK3UGBFJFEBTP4AN2SNLN";

    public List<Location> findPlacesByName(final String name) throws Exception
    {
	if (StringUtils.isEmpty(name))
	{
	    throw new IllegalArgumentException("Illegal Argument param");
	}

	final Client client = Client.create();
	final WebResource webResource = client.resource(
		"https://api.foursquare.com/v2/venues/search?client_id="
			+ CLIENT_ID + "&client_secret=" + CLIENT_SECRET
			+ "&v=20161016&near=" + name);

	// tested with OAuth first.
	final WebResource webResource22 = client
		.resource("https://api.foursquare.com/v2/venues/search?"
			+ "oauth_token=H33IHO0C0PWN5QIP0W1HTRBZZYOD3D24DEH4ERWHBNXLKIJC&v=20161016&near="
			+ name);

	ClientResponse response = webResource.accept("application/json")
		.get(ClientResponse.class);

	if (response.getStatus() != 200)
	{
	    throw new RuntimeException(
		    "Failed : HTTP error code : " + response.getStatus());
	}

	String output = response.getEntity(String.class);
	return convertToJava(output);
    }

    /**
     * Converts the json object into location pojo.
     * 
     * @param json as String
     * @return list of location
     */
    private List<Location> convertToJava(final String jsonString)
	    throws Exception
    {
	final List<Location> places = new ArrayList<Location>();

	// Create json related objects
	ObjectMapper objectMapper = new ObjectMapper();
	JsonNode rootNode = objectMapper.readTree(jsonString);
	JsonNode venuesNode = rootNode.findPath("venues");
	Iterator<JsonNode> venues = venuesNode.getElements();

	while (venues.hasNext())
	{
	    JsonNode venue = venues.next();
	    JsonNode name = venue.findPath("name");
	    JsonNode location = venue.findPath("location");

	    Location place = new Location();
	    place.setLatitude(location.findPath("lat").asText());
	    place.setLongitude(location.findPath("lng").asText());
	    place.setName(name.asText());
	    places.add(place);
	}

	return places;
    }

}
