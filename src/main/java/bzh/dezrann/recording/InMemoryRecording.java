package bzh.dezrann.recording;

import javax.websocket.Session;

public class InMemoryRecording {

	private Session userSession;
	private Session watcherSession;


	public InMemoryRecording(Session userSession, Session watcherSession) {
		this.userSession = userSession;
		this.watcherSession = watcherSession;
	}

	public Session getUserSession() {
		return userSession;
	}

	public Session getWatcherSession() {
		return watcherSession;
	}

	@Override
	public int hashCode() {
		int result = userSession != null ? userSession.hashCode() : 0;
		result = 31 * result + (watcherSession != null ? watcherSession.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		InMemoryRecording that = (InMemoryRecording) o;

		if (userSession != null ? !userSession.equals(that.userSession) : that.userSession != null) return false;
		if (watcherSession != null ? !watcherSession.equals(that.watcherSession) : that.watcherSession != null)
			return false;

		return true;
	}
}
