package com.java.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.java.config.SpringConfig;

/*standaloneSetup demo*/
public class LoginControllerTest {

	MockMvc mockmvc;

	@Before
	public void init() {
		mockmvc = MockMvcBuilders.standaloneSetup(new LoginController()).setViewResolvers(new SpringConfig().resolver())
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
