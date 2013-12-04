<<<<<<< HEAD
public class Process extends Thread{

	// Notes:
	// Process will need a variable, message_queue, that it can make calls to
	// consume from and send messages to
	// When constructing a message to be sent, how do we decide WHAT data goes
	// into the message? will we just use random data?

	int pid; // pid set where? not here
	int priority;// might be unnecessary
	String myQueueHandle;
	String sendQueueHandle;
	MessageQueueManager message_queue_manager;
	char[] data; // received message data goes here? -- considering String type
	Boolean isRandomSenario;

	// constructor
	public Process() {
		pid = -1;
		priority = 0;
		data = new char[1024]; // arbitrary buffer..for now

	}

	public Process(int myPid, int myPriority,
			int myBufferSize,MessageQueueManager manager) {
		pid = myPid;
		priority = myPriority;
		data = new char[myBufferSize];
		message_queue_manager = manager;
	}

	public boolean recMessage() {// this will call pop() on the Message Queue

		// retrieve a message from the message queue
		Message rec_message = message_queue_manager.read(myQueueHandle);
		if(rec_message != null){
			processMessage(rec_message); 
		} else {
			System.out.println(" [*] Message Queue Empty");
			return false;
		}
		return true;
		// there will need be some kind of controller for processes that waits
		// to receive messages that runs this func

	}

	// copy over data from a message to a process' buffer
	public void processMessage(Message msg) {
		char[] data_to_get = msg.getMessage_data();
		for (int i = 0; i < data_to_get.length; i++) {
			data[i] = data_to_get[i];// copy over data to the process' buffer
		}
	}

	public void sendMessageToQueue(char[] data, Process target) { // this will
																	// call
																	// write()
																	// in the
																	// MessageQueue
																	// class
		// send a message out to the message queue
		Message send_message = new Message(data, target.pid, this.pid);

		message_queue_manager.write(sendQueueHandle,send_message);// placeholder

		// send message out to the queue
		// queue should contain Message objects that contain a data portion and
		// target (PID?)
	}

	
	public void run(){
		//need to implement behaviors here
	}
	
}
=======
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
>>>>>>> upstream/master
