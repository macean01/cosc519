public class Message {

        
        String message_data;
//        int to_pid;
//        int from_pid;
        
        public Message(){
        	message_data = null;
//        	to_pid = -1;
//        	from_pid = -1;
        }
        public Message(String data){
        	message_data = data;
//        	to_pid = to;
//        	from_pid = from;
        }
		public String getMessage_data() {
			return message_data;
		}
		public void setMessage_data(String message_data) {
			this.message_data = message_data;
		}

        
}