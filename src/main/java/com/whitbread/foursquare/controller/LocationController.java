package com.whitbread.foursquare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.whitbread.foursquare.model.Location;
import com.whitbread.foursquare.service.LocationFinderService;

/**
 * Controller takes the request from the form and give response as
 * searched places from the four square api.
 * 
 */
@Controller
public class LocationController
{

    /**
     * searchService to get the data from four square
     */
    @Autowired
    private LocationFinderService locationFinderService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String findLocation(@RequestParam("location") String location, Model model) throws Exception
    {
	final List<Location> searchedResults = locationFinderService
		.findPlacesByName(location);
	model.addAttribute("searchedResults", searchedResults);
	model.addAttribute("location", location);
	return "locationFinder";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome()
    {
	return "locationFinder";
    }
    
}