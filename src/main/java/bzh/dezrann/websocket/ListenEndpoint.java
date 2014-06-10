package bzh.dezrann.websocket;

import bzh.dezrann.Forwards;
import bzh.dezrann.Message;
import bzh.dezrann.config.Config;
import bzh.dezrann.Sessions;
import javax.websocket.*;

public class ListenEndpoint extends Endpoint {

	private Sessions sessions;
	private Forwards forwards;

	public ListenEndpoint(){
		this.sessions = Config.injector.getInstance(Sessions.class);
		this.forwards = Config.injector.getInstance(Forwards.class);
	}

	@Override
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		System.out.println("User connection opened (session № " + session.getId() + ")");
		session.getAsyncRemote().sendText(Message.DEMAT.getMessage());
		session.addMessageHandler(new MessageHandler.Whole<String>() {
			@Override
			public void onMessage(String message) {
				if(forwards.containsKey(session.getId())){
					Session watcher = forwards.get(session.getId());
					watcher.getAsyncRemote().sendText(message);
				}
			}
		});
		sessions.put(session.getId(), session);
	}

	@Override
	public void onClose(Session session, CloseReason closeReason) {
		sessions.remove(session.getId());
		System.out.println("User connection closed (session № " + session.getId() + ")");
	}
}