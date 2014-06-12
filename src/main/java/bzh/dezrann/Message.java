package bzh.dezrann;

public enum Message {
	DEMAT("demat"),
	KENAVO("kenavo"),
	DIHANAN("dihanan"),
	ENROLLAN("enrollan"),
	KOUN("koun");

	private String message;

	Message(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
