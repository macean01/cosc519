import java.util.Hashtable;

public class MessageQueueManager {

	public Hashtable<Integer,MessageQueue> queues;
	public int numQueues = 0;
	public int maxQueues = 1024;

	public MessageQueueManager()
	{
		this.queues = new Hashtable<Integer,MessageQueue>();
	}

	public String createQueue()
	{
		if (this.numQueues >= this.maxQueues)
			return null;

		MessageQueue newQueue = new MessageQueue(this.numQueues);
		this.queues.put(numQueues, newQueue);
		this.numQueues++;

		return newQueue.id;
	}

	public MessageQueue getQueue(int id)
	{
		if (this.queues.containsKey((Integer)id))
			return this.queues.get((Integer)id);
		else
			return null;
	}

	public void deleteQueue(int id)
	{
		if (this.queues.containsKey((Integer)id))
			this.queues.remove((Integer)id);
	}

}
