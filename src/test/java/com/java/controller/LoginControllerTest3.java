package com.java.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.java.config.SpringConfig;

/*webAppContextSetup*/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration( classes=SpringConfig.class)
@WebAppConfiguration
public class LoginControllerTest3 {

	MockMvc mockmvc;
	
	@Autowired WebApplicationContext context;
	@Autowired DataSource ds;

	@Before
	public void init() {
		mockmvc = MockMvcBuilders.webAppContextSetup(context)
				.build();
	}

	/*
	 * , it is preferable to leave the context path and the Servlet path out of the
	 * request URI. If you must test with the full request URI, be sure to set the
	 * contextPath and servletPath accordingly so that request mappings work,
	 */
	@Test
	public void testGetWelcomePage() throws Exception {
		mockmvc.perform(get("/welcome")).andExpect(status().isOk()).andExpect(view().name("welcome"));
	}

	
	
}
