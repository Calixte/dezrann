package bzh.dezrann.recording.databean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Record implements Serializable {
	@Id
	private Integer recordingId;
	@Id
	private Long timestamp;
	@Id
	private Long nano;
	@Column(columnDefinition="LONGTEXT")
	private String json;

	public Record(String json){
		this.timestamp = System.currentTimeMillis();
		this.nano = System.nanoTime();
		this.json = json;
	}

	public Record() {
		this("");
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getNano() {
		return nano;
	}

	public void setNano(Long nano) {
		this.nano = nano;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Integer getRecordingId() {
		return recordingId;
	}

	public void setRecordingId(Integer recordingId) {
		this.recordingId = recordingId;
	}
}
