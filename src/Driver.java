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
		pc.createProcess(mqm, msq1, msq2, 8);
		pc.createProcess(mqm, msq2, msq3, 8);
		pc.createProcess(mqm, msq3, msq1, 8);

		pc.startProcesses();

		//System.out.print(mqm);
	}

}
