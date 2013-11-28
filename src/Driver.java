
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MessageQueueManager mqm = new MessageQueueManager();

		String msq1 = mqm.createQueue();
		String msq2 = mqm.createQueue();
		String msq3 = mqm.createQueue();

		ProcessControl pc = new ProcessControl();
		pc.createProcess(mqm, msq1, msq2);
		pc.createProcess(mqm, msq2, msq3);
		pc.createProcess(mqm, msq3, msq1);

		pc.startProcesses();
	}

}
