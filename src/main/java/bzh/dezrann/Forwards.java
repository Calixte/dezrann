package bzh.dezrann;

import bzh.dezrann.recording.databean.Record;

import javax.inject.Singleton;
import javax.websocket.Session;
import java.util.*;

@Singleton
public class Forwards {

	private Map<Session, Set<Session>> userToWatchers;
	private Map<Session, Session> watcherToUser;

	public Forwards(){
		userToWatchers = new HashMap<>();
		watcherToUser = new HashMap<>();
	}

	public void put(Session user, Session watcher){
		if(userToWatchers.containsKey(user)){
			userToWatchers.get(user).add(watcher);
		}else{
			Set<Session> watchers = new HashSet<>();
			watchers.add(watcher);
			userToWatchers.put(user, watchers);
		}
	}

	public boolean containsUser(Session userSession) {
		return userToWatchers.containsKey(userSession);
	}

	public Set<Session> getWatchers(Session userSession) {
		return userToWatchers.get(userSession);
	}

	public Session stopWatching(Session session) {
		watcherToUser.remove(session);
		Session clientSession = null;
		boolean deleteSet = false;
		for(Map.Entry<Session, Set<Session>> watchers : userToWatchers.entrySet()){
			if (watchers.getValue().remove(session)) {
				deleteSet = watchers.getValue().isEmpty();
				clientSession = watchers.getKey();
				break;
			}
		}
		if(deleteSet){
			userToWatchers.remove(clientSession);
		}
		return clientSession;
	}
}
