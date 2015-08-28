package org.baldercm.poc;

import com.thetransactioncompany.cors.CORSFilter;
import org.apache.commons.lang3.CharEncoding;
import org.baldercm.poc.config.PocConfig;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

@Order(1)
public class PocWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        springApplicationContext(servletContext);

        corsFilter(servletContext);
        characterEncodingFilter(servletContext);
    }

    private void springApplicationContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext appCtx = new AnnotationConfigWebApplicationContext();
        appCtx.getEnvironment().setActiveProfiles("dev");
        appCtx.register(PocConfig.class);

        servletContext.addListener(new ContextLoaderListener(appCtx));
        servletContext.addListener(new RequestContextListener());

        ServletRegistration.Dynamic restServletConfig = servletContext.addServlet("restServlet", new DispatcherServlet(appCtx));
        restServletConfig.setLoadOnStartup(1);
        restServletConfig.addMapping("/*");
        restServletConfig.setAsyncSupported(true);
    }

    private void corsFilter(ServletContext servletContext) {
        CORSFilter corsFilter = new CORSFilter();

        FilterRegistration.Dynamic corsFilterConfig = servletContext.addFilter("corsFilter", corsFilter);
        corsFilterConfig.setInitParameter("cors.supportedHeaders", "Origin, Accept, Content-Type, X-Requested-With, Authorization");
        corsFilterConfig.setInitParameter("cors.supportedMethods", "GET, POST, PUT, DELETE, HEAD, OPTIONS");
        corsFilterConfig.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        corsFilterConfig.setAsyncSupported(true);
    }

    private void characterEncodingFilter(ServletContext servletContext) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(CharEncoding.UTF_8);
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistration.Dynamic characterEncodingFilterConfig = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
        characterEncodingFilterConfig.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        characterEncodingFilterConfig.setAsyncSupported(true);
    }

//	private void addSpringSecurityFilter(ServletContext servletContext) {
//		FilterRegistration.Dynamic springSecurityFilter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
//
//		springSecurityFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
//	}
}
