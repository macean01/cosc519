
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int numQueues = 512;

		MessageQueueManager mqm = new MessageQueueManager();
		for (int i = 0; i < numQueues; i++)
			mqm.createQueue();

		for (int i = 0; i < numQueues; i++)
			mqm.deleteQueue(i);


	}

}
