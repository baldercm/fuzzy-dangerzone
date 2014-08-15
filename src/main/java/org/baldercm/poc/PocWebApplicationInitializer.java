package org.baldercm.poc;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.thetransactioncompany.cors.CORSFilter;

@Order(1)
public class PocWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		springApplicationContext(servletContext);

		jerseyServlet(servletContext);

		corsFilter(servletContext);
		characterEncodingFilter(servletContext);
	}

	private void springApplicationContext(ServletContext servletContext) {
		servletContext.addListener("org.springframework.web.context.ContextLoaderListener");
		servletContext.addListener("org.springframework.web.context.request.RequestContextListener");

		servletContext.setInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
		servletContext.setInitParameter("contextConfigLocation", "org.baldercm.poc.config.PocConfig");
		servletContext.setInitParameter("spring.profiles.default", "dev");
	}

	private void jerseyServlet(ServletContext servletContext) {
		ServletRegistration.Dynamic jerseyServlet = servletContext.addServlet("pocJerseyServlet", ServletContainer.class);
		jerseyServlet.setInitParameter("javax.ws.rs.Application", "org.baldercm.poc.config.PocJerseyConfig");
		jerseyServlet.setLoadOnStartup(1);
		jerseyServlet.addMapping("/*");
	}

	private void corsFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", CORSFilter.class);
		corsFilter.setInitParameter("cors.supportedHeaders", "Origin, Accept, Content-Type, X-Requested-With, Authorization");
		corsFilter.setInitParameter("cors.supportedMethods", "GET, POST, PUT, DELETE, HEAD, OPTIONS");
		corsFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	}

	private void characterEncodingFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", CharacterEncodingFilter.class);
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	}

//	private void addSpringSecurityFilter(ServletContext servletContext) {
//		FilterRegistration.Dynamic springSecurityFilter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
//
//		springSecurityFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
//	}
}
