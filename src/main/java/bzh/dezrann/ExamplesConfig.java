package bzh.dezrann;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class ExamplesConfig implements ServerApplicationConfig {

	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {

		Set<ServerEndpointConfig> result = new HashSet<ServerEndpointConfig>();

		System.out.println("here");

		if (scanned.contains(EchoEndpoint.class)) {
			result.add(ServerEndpointConfig.Builder.create(
					EchoEndpoint.class,
					"/echo").build());
		}

		return result;
	}

	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		return null;
	}

}