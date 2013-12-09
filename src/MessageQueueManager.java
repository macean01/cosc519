import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class MessageQueueManager {

	public Hashtable<String, MessageQueue> queues;
	public int numQueues = 0;
	public int maxQueues = 1024;
	private int messagesSent = 0;
	private int messagesRecv = 0;
	private MessageLock myLock;

	public MessageQueueManager() {
		this.queues = new Hashtable<String, MessageQueue>();
	}

	public String createQueue() {
		if (this.numQueues >= this.maxQueues){
			System.out.println("Reached max queues please reset test params");
			return null;
		}

		MessageQueue newQueue = new MessageQueue(this.numQueues);
		if(myLock!=null)
			newQueue.myLock = myLock;
		this.queues.put(((Integer) numQueues).toString(), newQueue);
		this.numQueues++;
		return newQueue.id;
	}

	public void deleteQueue(String id) {
		if (this.queues.containsKey(id))
			this.queues.remove(id);
	}

	public void write(String messageQueue, Message message) {
		if (this.queues.containsKey(messageQueue) == false)
			return;
		else {
			MessageQueue messageWriter = this.queues.get(messageQueue);
			messageWriter.write(message);
			messagesSent++;
		}
	}

	public Message read(String messageQueue) {
		if (this.queues.containsKey(messageQueue) == false)
			return null;
		else{
			Message returnMSG = this.queues.get(messageQueue).read();
			if(returnMSG != null)
				messagesRecv++;
			return returnMSG;
		}
	}
	
	public void PrintMetrics(){
		System.out.println("Total messages sent: "+messagesSent);
		System.out.println("Total messages received: "+messagesRecv);
		
	}

	public String toString() {
		String ret = "";

		Set<String> keys = this.queues.keySet();
		for (String key : keys) {
			ret += "Message queue: " + key + " " + this.queues.get(key) + "\n";
		}

		ret += "\n";

		return ret;
	}

	public void setMyLock(MessageLock sharedMessageLock) {
		myLock = sharedMessageLock;
	}
}
