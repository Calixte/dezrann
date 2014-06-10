package bzh.dezrann.websocket;

import bzh.dezrann.Forwards;
import bzh.dezrann.Message;
import bzh.dezrann.config.Config;
import bzh.dezrann.Sessions;
import javax.websocket.*;
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
		session.getAsyncRemote().sendText(Message.DEMAT.getMessage());
		session.addMessageHandler(new MessageHandler.Whole<String>() {
			@Override
			public void onMessage(String message) {
				if(forwards.containsUserId(session.getId())){
					Set<Session> watchers = forwards.getWatchers(session.getId());
					for(Session watcher : watchers){
						try {
							watcher.getBasicRemote().sendText(message);
						} catch (IOException e) {
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

	}
}