
public class Message {

        
        char[] message_data;
        int to_pid;
        int from_pid;
        
        public Message(){
                message_data = null;
                to_pid = -1;
                from_pid = -1;
        }
        public Message(char[] data, int to, int from){
                message_data = data;
                to_pid = to;
                from_pid = from;
        }
                public char[] getMessage_data() {
                        return message_data;
                }
                public void setMessage_data(char[] message_data) {
                        this.message_data = message_data;
                }
                public int getTo_pid() {
                        return to_pid;
                }
                public void setTo_pid(int to_pid) {
                        this.to_pid = to_pid;
                }
                public int getFrom_pid() {
                        return from_pid;
                }
                public void setFrom_pid(int from_pid) {
                        this.from_pid = from_pid;
                }
        
}