import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Set;

public class MessageQueueManager {

	public Hashtable<String, MessageQueue> queues;
	private LinkedList<Message> readMessages = new LinkedList<Message>();
	public int numQueues = 0;
	public int maxQueues = 1024;
	private int messagesSent = 0;
	private int messagesRecv = 0;
	private MessageLock myLock;

	public MessageQueueManager() {
		this.queues = new Hashtable<String, MessageQueue>();
	}

	public String createQueue() {
		if (this.numQueues >= this.maxQueues) {
			System.out.println("Reached max queues please reset test params");
			return null;
		}

		MessageQueue newQueue = new MessageQueue(this.numQueues);
		if (myLock != null)
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
			message.creationTime = System.nanoTime();
			messageWriter.write(message);
			messagesSent++;
		}
	}

	public Message read(String messageQueue) {
		if (this.queues.containsKey(messageQueue) == false)
			return null;
		else {
			Message returnMSG = this.queues.get(messageQueue).read();
			if (returnMSG != null) {
				returnMSG.readTime = System.nanoTime();
				readMessages.add(returnMSG);
				messagesRecv++;
			}
			return returnMSG;
		}
	}

	public void PrintMetrics() {
		System.out.println("Total messages sent: " + messagesSent);
		System.out.println("Total messages received: " + messagesRecv);
		System.out.println("Shortest message wait time(nano): " + ShortestWaitTime());
		System.out.println("Longest message wait time(nano): " + LongestWaitTime());
		System.out.println("Avg message wait time(nano): " + AvgWaitTime());

	}

	private long AvgWaitTime() {
		long currentTimespan = 0;
		for(Message mes : readMessages){
			long timespan = mes.readTime - mes.creationTime;
			currentTimespan += timespan;
		}
		return currentTimespan/readMessages.size();
	}

	private long LongestWaitTime() {
		long longestLength = -1;
		for(Message mes : readMessages){
			long currentTimespan = mes.readTime - mes.creationTime;
			if(longestLength == -1){
				longestLength = currentTimespan;
			}
			else if(longestLength < currentTimespan){
				longestLength = currentTimespan;
			}
		}
		return longestLength;
	}

	private long ShortestWaitTime() {
		long shortestLength = -1;
		for(Message mes : readMessages){
			long currentTimespan =  mes.readTime - mes.creationTime;
			if(shortestLength == -1){
				shortestLength = currentTimespan;
			}
			else if(shortestLength > currentTimespan){
				shortestLength = currentTimespan;
			}
		}
		return shortestLength;
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
