package com.whitbread.foursquare.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.whitbread.foursquare.model.Location;

@RunWith(MockitoJUnitRunner.class)
public class LocationFinderServiceTest
{

    @Mock
    private LocationFinderService mockedLocationFinderService;

    private LocationFinderService notMockedFinderService;

    @Before
    public void setup()
    {
	notMockedFinderService = new LocationFinderServiceImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNameNotProvided() throws Exception
    {
	notMockedFinderService.findPlacesByName(null);
    }

    @Test
    public void testIfNameProvidedCallingMockedService() throws Exception
    {
	List<Location> expected = new ArrayList<>();
	Location location = new Location();
	location.setName("test");
	expected.add(location);

	Mockito.when(mockedLocationFinderService.findPlacesByName("london"))
		.thenReturn(expected);
	List<Location> actual = mockedLocationFinderService
		.findPlacesByName("london");
	Assert.assertNotNull(actual.get(0));
	Assert.assertEquals(actual.size(), expected.size());
    }

    @Test
    public void testIfNameProvidedCallingActualService() throws Exception
    {
	List<Location> actual = notMockedFinderService
		.findPlacesByName("london");
	Assert.assertNotNull(actual.get(0));
	Assert.assertEquals(actual.size(), 30);
    }
}
