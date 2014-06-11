package bzh.dezrann;

public enum Message {
	DEMAT("demat"),
	KENAVO("kenavo"),
	DIHANAN("dihanan"),
	ENROLLAN("enrollan");

	private String message;

	Message(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
