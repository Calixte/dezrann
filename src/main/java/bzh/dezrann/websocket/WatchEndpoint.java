package bzh.dezrann.websocket;

import bzh.dezrann.Forwards;
import bzh.dezrann.Message;
import bzh.dezrann.Sessions;
import bzh.dezrann.config.Config;
import bzh.dezrann.recording.InMemoryRecording;
import bzh.dezrann.recording.InMemoryRecordings;
import bzh.dezrann.recording.databean.Record;
import bzh.dezrann.recording.databean.Recording;

import javax.persistence.EntityManager;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler.Whole;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class WatchEndpoint extends Endpoint {

	private Forwards forwards;
	private Sessions sessions;
	private InMemoryRecordings recordings;
	private EntityManager entityManager;

	public WatchEndpoint(){
		this.forwards = Config.injector.getInstance(Forwards.class);
		this.sessions = Config.injector.getInstance(Sessions.class);
		this.recordings = Config.injector.getInstance(InMemoryRecordings.class);
		this.entityManager = Config.injector.getInstance(EntityManager.class);
	}

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		System.out.println("Watcher connection opened (session № " + session.getId() + " " + session + ")");
		session.addMessageHandler(new Whole<String>() {
			@Override
			public void onMessage(String message) {
				if(message.equals(Message.ENROLLAN.getMessage())){
					Session clientSession = forwards.getWatchedUser(session);
					recordings.put(new InMemoryRecording(clientSession, session), new ArrayList<>());
					try {
						clientSession.getBasicRemote().sendText(Message.ENROLLAN.getMessage());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(message.equals(Message.DIHANAN.getMessage())){
					Session clientSession = forwards.getWatchedUser(session);
					recordings.stopRecording(clientSession, session);
				}
				else if (sessions.containsKey(message)) {
					Session clientSession = sessions.get(message);
					try {
						clientSession.getBasicRemote().sendText(Message.DEMAT.getMessage());
					} catch (IOException e) {
						e.printStackTrace();
					}
					forwards.put(clientSession, session);
				}
			}
		});
	}

	@Override
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("Watcher connection closed (session № " + session.getId() + " " + session + ")");
		Session clientSession = forwards.stopWatching(session);
		recordings.stopRecording(clientSession, session);
		if(!forwards.containsUser(clientSession)){
			try {
				clientSession.getBasicRemote().sendText(Message.KENAVO.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
