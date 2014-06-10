package bzh.dezrann;

import javax.inject.Singleton;
import javax.websocket.Session;
import java.util.HashMap;
import java.util.HashSet;

@Singleton
public class Sessions extends HashMap<String, Session> {
}
