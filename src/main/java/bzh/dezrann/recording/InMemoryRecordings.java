package bzh.dezrann.recording;

import bzh.dezrann.recording.databean.Record;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;

@Singleton
public class InMemoryRecordings extends HashMap<InMemoryRecording, Collection<Record>> {
}
