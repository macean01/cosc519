import java.util.Hashtable;

public class MessageQueueManagement {
	
	public Hashtable<Integer,MessageQueue> queues;
	public int numQueues = 0;
	
	public MessageQueueManagement()
	{
		this.queues = new Hashtable<Integer,MessageQueue>();
	}
	
	public MessageQueue createQueue()
	{
		MessageQueue newQueue = new MessageQueue();
		this.queues.put(numQueues, newQueue);
		this.numQueues++;
		
		return newQueue;
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
