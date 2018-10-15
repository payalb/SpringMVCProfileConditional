package com.java.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*standaloneSetup demo,without using spring config file*/
public class LoginControllerTest2 {

	MockMvc mockmvc;

	@Before
	public void init() {
		mockmvc = MockMvcBuilders.standaloneSetup(new LoginController()).setViewResolvers(resolver())
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

	
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
