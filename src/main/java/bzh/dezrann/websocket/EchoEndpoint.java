package bzh.dezrann.websocket;

import bzh.dezrann.config.Config;
import bzh.dezrann.Sessions;

import java.io.IOException;

import javax.websocket.*;

public class EchoEndpoint extends Endpoint {

	private Sessions sessions;

	public EchoEndpoint(){
		this.sessions = Config.injector.getInstance(Sessions.class);
	}

	@Override
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		RemoteEndpoint.Basic remoteEndpointBasic = session.getBasicRemote();
		session.addMessageHandler(new EchoMessageHandler(remoteEndpointBasic));
		sessions.put(session.getId(), session);
	}

	@Override
	public void onClose(Session session, CloseReason closeReason) {
		sessions.remove(session);
	}

	private static class EchoMessageHandler implements MessageHandler.Whole<String> {

		private final RemoteEndpoint.Basic remoteEndpointBasic;

		private EchoMessageHandler(RemoteEndpoint.Basic remoteEndpointBasic) {
			this.remoteEndpointBasic = remoteEndpointBasic;
		}

		@Override
		public void onMessage(String message) {
			try {
				if (remoteEndpointBasic != null) {
					remoteEndpointBasic.sendText(message);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}