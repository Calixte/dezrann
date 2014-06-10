package bzh.dezrann.config;

import bzh.dezrann.servlet.WatchServlet;
import bzh.dezrann.websocket.EchoEndpoint;
import bzh.dezrann.servlet.MainServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

public class Config extends GuiceServletContextListener implements ServerApplicationConfig {

	public static Injector injector;

	@Override
	protected Injector getInjector() {
		injector = Guice.createInjector(new ServletModule(){
			@Override
			protected void configureServlets() {
				serve("/").with(MainServlet.class);
				serve("/watch").with(WatchServlet.class);
			}
		});
		return injector;
	}

	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {

		Set<ServerEndpointConfig> result = new HashSet<>();

		if (scanned.contains(EchoEndpoint.class)) {
			result.add(ServerEndpointConfig.Builder.create(EchoEndpoint.class, "/echo").build());
		}

		return result;
	}

	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		return null;
	}
}