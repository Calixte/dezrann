package bzh.dezrann.recording.databean;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

@Embeddable
public class RecordKey implements Serializable{
	private Integer recordingId;
	private Long timestamp;
	private Long nano;
	private String uuid;
	
	public RecordKey(){
		this.timestamp = System.currentTimeMillis();
		this.nano = System.nanoTime();
		this.uuid = UUID.randomUUID().toString();
	}
	
	public void setRecordingId(Integer recordingId){
		this.recordingId = recordingId;
	}
}
