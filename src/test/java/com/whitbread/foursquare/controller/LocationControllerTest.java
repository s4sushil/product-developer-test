package com.whitbread.foursquare.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.whitbread.foursquare.application.WebApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class LocationControllerTest
{
    private MockMvc mockMvc;

    @Before
    public void setup()
    {
	MockitoAnnotations.initMocks(this);
	this.mockMvc = MockMvcBuilders.standaloneSetup(new LocationController())
		.build();
    }

    @Test
    public void testWelcomePage() throws Exception
    {
	this.mockMvc
		.perform(get("/welcome").accept(MediaType
			.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(status().isOk())
		.andExpect(view().name("locationFinder"));
    }

}
