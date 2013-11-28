
public class Message {


        String message_data;

        public Message(){
                message_data = null;
        }
        public Message(String data){
                message_data = data;
        }
        public String getMessage_data() {
        	return message_data;
        }
        public void setMessage_data(String message_data) {
        	this.message_data = message_data;
        }
}
