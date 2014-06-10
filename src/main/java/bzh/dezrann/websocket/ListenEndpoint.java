package bzh.dezrann.websocket;

import bzh.dezrann.Forwards;
import bzh.dezrann.Message;
import bzh.dezrann.config.Config;
import bzh.dezrann.Sessions;
import javax.websocket.*;
import java.io.IOException;

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
					try {
						watcher.getBasicRemote().sendText(message);
					} catch (IOException e) {
						System.err.println("Error sending message to watcher");
						e.printStackTrace();
					}
				}
			}
		});
		sessions.put(session.getId(), session);
	}

	@Override
	public void onClose(Session session, CloseReason closeReason) {
		sessions.remove(session.getId());
		System.out.println("User connection closed (session № " + session.getId() + ")");
		System.out.println(closeReason);
	}

	@Override
	public void onError(Session session, Throwable thr) {
		System.err.println("User connection errored (session № " + session.getId() + ")");
		thr.printStackTrace();
	}
}