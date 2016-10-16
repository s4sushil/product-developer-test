package com.whitbread.foursquare.model;

/**
 * Pojo to hold the location
 *
 */
public class Location
{

    private String name;

    private String longitude;

    private String latitude;

    public String getName()
    {
	return name;
    }

    public String getLongitude()
    {
	return longitude;
    }

    public String getLatitude()
    {
	return latitude;
    }

    public void setName(final String name)
    {
	this.name = name;
    }

    public void setLongitude(final String longitude)
    {
	this.longitude = longitude;
    }

    public void setLatitude(final String latitude)
    {
	this.latitude = latitude;
    }

}
