package com.deepak.maps.seeme;

import java.util.TimeZone;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:seeme-test-servlet.xml", "classpath:seeme-servlet.xml" })
@WebAppConfiguration
public class BaseTest {

	protected MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	
	public BaseTest() {
		super();
	}

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

	}

	@Test
	public void testContext() {
		Assert.assertNotNull(webApplicationContext);
	}

	@After
	public void validate() {
	    Mockito.validateMockitoUsage();
	}

}