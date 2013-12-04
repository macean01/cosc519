import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class MessageQueueManager {

	public Hashtable<String,MessageQueue> queues;
	public int numQueues = 0;
	public int maxQueues = 1024;

	public MessageQueueManager()
	{
		this.queues = new Hashtable<String,MessageQueue>();
	}

	public String createQueue()
	{
		if (this.numQueues >= this.maxQueues)
			return null;

		MessageQueue newQueue = new MessageQueue(this.numQueues);
		this.queues.put(((Integer)numQueues).toString(), newQueue);
		this.numQueues++;

		return newQueue.id;
	}

	public void deleteQueue(String id)
	{
		if (this.queues.containsKey(id))
			this.queues.remove(id);
	}

	public void write(String messageQueue, Message message)
	{
		if (this.queues.containsKey(messageQueue) == false)
			return;
		else
			this.queues.get(messageQueue).write(message);
	}

	public Message read(String messageQueue)
	{
		if (this.queues.containsKey(messageQueue) == false)
			return null;
		else
			return this.queues.get(messageQueue).read();
	}

	public String toString()
	{
		String ret = "";

		Set<String> keys = this.queues.keySet();
		for (String key : keys) {
			ret += "Message queue: " + key + " " + this.queues.get(key) + "\n";
		}

		ret += "\n";

		return ret;
	}
}
