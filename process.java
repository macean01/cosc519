import java.util.ArrayList;

public class process {
        
        
       //Notes:
		//Process will need a variable, message_queue, that it can make calls to consume from and send messages to
		//When constructing a message to be sent, how do we decide WHAT data goes into the message? will we just use random data?

	
	
	
        int pid; 
        int priority;
        
        String data; 
        
        MessageQueueManager mqm; //connect the MQM from the Driver to the process...All processes share the same MQM
        
        
        //constructor
        public process(int new_pid) { //have to pass mqm  after we create a process, because processes are created in the ProcessControl class, which has no knowledge of a mqm
        	
                pid = new_pid; //each time we create a process, we need to increment pid....we will use the pid variable to determine what queue to read/write from (Pn writes to Qn), (Pn reads Qn+1)
               // priority = 0;
                data  = ""; 

        }

        
        //Connect MQM 
        public void setMessageQueueManager(MessageQueueManager newMQM){ 
        	this.mqm = newMQM;
        }
        
        
        
        
        public String receiveMessage(){//this will call pop() on the Message Queue
        
        	Message result = mqm.read(pid+1+"");//Pn , reads from Qn+1, where n is the PID (PIDs start at 0 and increment by 1, so this should work correctly with the MQM indices 
        	
              
        	return result.getMessage_data();
        	//retrieve a message from the message queue

                        
        }
        

        
        public void sendMessageToQueue(String data, process target){ //this will call write() in the MessageQueue class
        		//send a message out to the message queue
        		Message send_message = new Message();
       
        		
        		mqm.write(pid+"", send_message); // Pn writes to Qn, we can use PID as n
                //send message out to the queue
        }
 
        
        
        
        
        
        
        
        
        
}