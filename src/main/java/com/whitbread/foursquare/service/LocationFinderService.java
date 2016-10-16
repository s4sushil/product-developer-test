package com.whitbread.foursquare.service;

import java.util.List;

import com.whitbread.foursquare.model.Location;

/**
 * Class that search for location
 * 
 */
public interface LocationFinderService
{
    List<Location> findPlacesByName(String name) throws Exception;
}
