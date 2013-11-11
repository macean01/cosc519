public class process {
        
        
       //Notes:
		//Process will need a variable, message_queue, that it can make calls to consume from and send messages to
		//When constructing a message to be sent, how do we decide WHAT data goes into the message? will we just use random data?

	
	
	
        int pid; //pid set where? not here
        int priority;//might be unnecessary
        
        char[] data; //received message data goes here? -- considering String type
        
        
        
        //constructor
        public process() {
                pid = -1;
                priority = 0;
                data  = new char[1024]; //arbitrary buffer..for now

        }

        
        
        public boolean recMessage(){//this will call pop() on the Message Queue
        
                
        	//retrieve a message from the message queue
        	
        	if(message_queue.notEmpty()){
	        	message rec_message = message_queue.consumeItem();//placeholder
	        	
	        	processMessage(rec_message); //store the recieved message in the buffer -- not sure if this is necessary
        	}
        	else{
        		System.out.println("  [*] Message Queue Empty");
        	}
        	
        	//there will need be some kind of controller for processes that waits to receive messages that runs this func
                
        }
        
        //copy over data from a message to a process' buffer
        public void processMessage(message msg){
        	char[] data_to_get = msg.getMessage_data();
        	for(int i=0;i<data_to_get.length;i++){
        		data[i] = data_to_get[i];//copy over data to the process' buffer
        	}
        }
        
        public void sendMessageToQueue(char[] data, process target){ //this will call write() in the MessageQueue class
        		//send a message out to the message queue
        		message send_message = new message(data, target.pid, this.pid);
       
        		
        		message_queue.addItem(send_message);//placeholder
        		
                //send message out to the queue
                //queue should contain Message objects that contain a data portion and target (PID?)
        }
 
        
        
        
        
        
        
        
        
        
}