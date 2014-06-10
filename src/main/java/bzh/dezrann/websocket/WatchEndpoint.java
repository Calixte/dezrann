package bzh.dezrann.websocket;

import bzh.dezrann.Forwards;
import bzh.dezrann.Sessions;
import bzh.dezrann.config.Config;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.*;
import javax.websocket.Session;

public class WatchEndpoint extends Endpoint {

	private Forwards forwards;
	private Sessions sessions;

	public WatchEndpoint(){
		this.forwards = Config.injector.getInstance(Forwards.class);
		this.sessions = Config.injector.getInstance(Sessions.class);
	}

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		session.addMessageHandler(new MessageHandler.Whole<String>() {
			@Override
			public void onMessage(String message) {
				if(sessions.containsKey(message)){
					Session clientSession = sessions.get(message);
					forwards.put(clientSession, session);
				}
			}
		});
	}
}
