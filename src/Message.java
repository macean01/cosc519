import java.util.Date;

public class Message {

	long creationTime = 0;
	long readTime = 0;
	String message_data;

	public Message() {
		message_data = null;
	}

	public Message(String data) {
		message_data = data;
		creationTime = System.currentTimeMillis();
	}

	public String getMessage_data() {
		return message_data;
	}

	public void setMessage_data(String message_data) {
		this.message_data = message_data;
	}

	public String toString() {
		return this.message_data;
	}
}
