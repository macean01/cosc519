public class Process implements Runnable {


        //int priority;//might be unnecessary
        //String data;
        MessageQueueManager mqm;
        String producerId;
        String consumerId;
        int pid;
        int recvMessages;
        int sendMessages;
        int maxMessages;

        //constructor
        public Process(MessageQueueManager mqm, String producerId, String consumerId, int pid, int numMessages) {
        		this.mqm = mqm;
        		this.producerId = producerId;
                this.consumerId = consumerId;
                this.sendMessages = 0;
                this.recvMessages = 0;
                this.pid = pid;
                this.maxMessages = numMessages;
        }

        public void run() {
        	while (this.recvMessages < this.maxMessages) {	// need to fix this so everyone writes to a queue first and we don't deadlock on reading
        		if (this.sendMessages < this.maxMessages) {
        			Message m = new Message("Message: " + (this.sendMessages + 1) + " from: " + this.pid);
        			this.processMessage(m);
        			this.sendMessages++;
        		}

        		Message recv = this.recMessage();
        		if (recv != null) {
        			this.recvMessages++;
        			System.out.println(recv);
        			//System.out.print(mqm);
        		}
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
