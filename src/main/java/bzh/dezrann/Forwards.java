package bzh.dezrann;

import javax.inject.Singleton;
import javax.websocket.Session;
import java.util.HashMap;

@Singleton
public class Forwards extends HashMap<String, Session> {
}
