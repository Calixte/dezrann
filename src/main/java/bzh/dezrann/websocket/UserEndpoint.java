package bzh.dezrann.websocket;

import bzh.dezrann.Forwards;
import bzh.dezrann.Message;
import bzh.dezrann.Sessions;
import bzh.dezrann.config.Config;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler.Whole;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Set;

public class UserEndpoint extends Endpoint {

	private Sessions sessions;
	private Forwards forwards;

	public UserEndpoint(){
		this.sessions = Config.injector.getInstance(Sessions.class);
		this.forwards = Config.injector.getInstance(Forwards.class);
	}

	@Override
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		System.out.println("User connection opened (session № " + session.getId() + "\t" + session + ")");
		session.addMessageHandler(new Whole<String>() {
			@Override
			public void onMessage(String message) {
				if(forwards.containsUser(session)){
					Set<Session> watchers = forwards.getWatchers(session);
					for(Session watcher : watchers){
						try {
							watcher.getBasicRemote().sendText(message);
						} catch (IOException e) {
							System.err.println("Error sending message to watcher");
							e.printStackTrace();
						}
					}
				}
			}
		});
		sessions.put(session.getId(), session);
	}

	@Override
	public void onClose(Session session, CloseReason closeReason) {
		sessions.remove(session.getId());
		for(Session watcher : forwards.getWatchers(session)){
			try {
				forwards.stopWatching(session);
				watcher.getBasicRemote().sendText(Message.KENAVO.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("User connection closed (session № " + session.getId() + " " + session + ")");
	}

	@Override
	public void onError(Session session, Throwable thr) {
		System.err.println("User connection failed (session № " + session.getId() + ")");
		thr.printStackTrace();
	}
}