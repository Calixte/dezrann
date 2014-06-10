package bzh.dezrann.websocket;

import bzh.dezrann.Forwards;
import bzh.dezrann.Message;
import bzh.dezrann.Sessions;
import bzh.dezrann.config.Config;
import com.google.inject.internal.util.$SourceProvider;

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
		System.out.println("Watcher connection opened (session № " + session.getId() + ")");
		session.addMessageHandler(new MessageHandler.Whole<String>() {
			@Override
			public void onMessage(String message) {
				if(sessions.containsKey(message)){
					Session clientSession = sessions.get(message);
					forwards.put(clientSession.getId(), session);
				}
			}
		});
	}

	@Override
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("Watcher connection closed (session № " + session.getId() + ")");
	}

}
