package bzh.dezrann;

public enum Message {
	DEMAT("demat"),
	KENAVO("kenavo"),
	DIHANAN("dihanañ"),
	ENROLLAN("enrollañ");

	private String message;

	Message(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
