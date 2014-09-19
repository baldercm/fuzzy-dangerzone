package org.baldercm.poc;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Slf4jRequestLog;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.server.handler.ShutdownHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		WebAppContext context = webAppContext();
		RequestLogHandler requestLogHandler = requestLogHandler();
		ShutdownHandler shutdownHandler = new ShutdownHandler("stop_jetty");

		HandlerCollection handlers = new HandlerCollection();
		handlers.addHandler(context);
		handlers.addHandler(requestLogHandler);
		handlers.addHandler(shutdownHandler);

		server.setHandler(handlers);

		server.start();
		server.join();
	}

	private static WebAppContext webAppContext() {
		WebAppContext context = new WebAppContext();
		context.setContextPath("/poc");

		Resource webApplicationInitializer = Resource.newClassPathResource("org/baldercm/poc/PocWebApplicationInitializer.class");
		context.getMetaData().addContainerResource(webApplicationInitializer);

		Configuration[] configurations = { new AnnotationConfiguration() };
		context.setConfigurations(configurations);
		return context;
	}

	private static RequestLogHandler requestLogHandler() {
		Slf4jRequestLog requestLog = new Slf4jRequestLog();

		RequestLogHandler requestLogHandler = new RequestLogHandler();
		requestLogHandler.setRequestLog(requestLog);

		return requestLogHandler;
	}
}