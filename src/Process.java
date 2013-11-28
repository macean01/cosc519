public class Process {


        //int priority;//might be unnecessary
        //String data;
        MessageQueueManager mqm;
        String producerId;
        String consumerId;


        //constructor
        public Process(MessageQueueManager mqm, String producerId, String consumerId) {
        		this.mqm = mqm;
        		this.producerId = producerId;
                this.consumerId = consumerId;
        }

        public void start(){
        	while (true) {	// need to fix this so everyone writes to a queue first and we don't deadlock on reading
        		Message m = this.recMessage();
        		this.processMessage(m);
        	}
        }

        public Message recMessage(){//this will call pop() on the Message Queue

                Message message = mqm.read(this.consumerId);
                return message;
        }

        //copy over data from a message to a process' buffer
        public void processMessage(Message msg){
                mqm.write(this.producerId, msg);
        }
}
