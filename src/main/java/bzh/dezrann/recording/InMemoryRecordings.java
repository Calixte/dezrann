package bzh.dezrann.recording;

import bzh.dezrann.recording.databean.Record;
import bzh.dezrann.recording.databean.Recording;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.websocket.Session;
import java.util.Collection;
import java.util.HashMap;

@Singleton
public class InMemoryRecordings extends HashMap<InMemoryRecording, Collection<Record>> {

	@Inject
	private EntityManager entityManager;

	public void stopRecording(Session userSession, Session watcherSession){
		Collection<Record> records = this.get(new InMemoryRecording(userSession, watcherSession));
		if(records == null){
			return;
		}
		Recording recording = new Recording();
		entityManager.getTransaction().begin();
		entityManager.persist(recording);
		entityManager.getTransaction().commit();
		System.out.println("New recording: " + recording.getId() + " with " + records.size() + " records.");
		this.remove(new InMemoryRecording(userSession, watcherSession));
		try{
			entityManager.getTransaction().begin();
			for(Record record : records){
				record.setRecordingId(recording.getId());
				entityManager.persist(record);
			}
			entityManager.getTransaction().commit();
		}catch(Throwable t){
			t.printStackTrace();
		}
	}

}
