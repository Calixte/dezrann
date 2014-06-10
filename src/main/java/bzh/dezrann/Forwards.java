package bzh.dezrann;

import javax.inject.Singleton;
import javax.websocket.Session;
import java.util.*;

@Singleton
public class Forwards {

	private Map<String, Set<Session>> userToWatchers;
	private Map<Session, String> watcherToUser;

	public Forwards(){
		userToWatchers = new HashMap<>();
		watcherToUser = new HashMap<>();
	}

	public void put(Session user, Session watcher){
		if(userToWatchers.containsKey(user.getId())){
			userToWatchers.get(user.getId()).add(watcher);
		}else{
			Set<Session> watchers = new HashSet<>();
			watchers.add(watcher);
			userToWatchers.put(user.getId(), watchers);
		}
	}

	public boolean containsUserId(String id) {
		return userToWatchers.containsKey(id);
	}

	public Set<Session> getWatchers(String id) {
		return userToWatchers.get(id);
	}
}
