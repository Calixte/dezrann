package bzh.dezrann.recording.databean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Record implements Serializable {
	
	@Id
	private RecordKey recordKey;

	@Column(columnDefinition="LONGTEXT")
	private String json;

	public Record(String json){
		recordKey = new RecordKey();
		this.json = json;
	}

	public Record() {
		this("");
	}

	public RecordKey getRecordKey(){
		return recordKey;
	}
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
