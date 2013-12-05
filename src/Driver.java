import java.io.*;
import java.util.*;

public class Driver {

	static int numProcs;
	static Process[] myProcs;
	static int BufferSize;
	static int PatternSelection;

	public static void main(String[] args) {

		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(
				System.in));
		
		MessageQueueManager mqm = new MessageQueueManager();

		String Choice = "";
		
		while (!Choice.equals("done")) {
			System.out.println("COSC519 Message Queue Project Main Menu:\n"
					+ "1. Set Proccess Number\n"
					+ "2. Set Message Passing Pattern\n"
					+ "3. Set Proccess Buffer Size\n" + "4. Start\n"
					+ "5. Print MessageQueues");
			try {

				Choice = bufferRead.readLine();

				if (Choice.equals("1")) {
					System.out.println("Please Enter Process Number:");
					try {
						String procs = bufferRead.readLine();
						numProcs = Integer.parseInt(procs);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if (Choice.equals("2")) {
					System.out.println("Please Enter Pattern Configuration:");
					try {
						String pattern = bufferRead.readLine();
						PatternSelection = Integer.parseInt(pattern);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if (Choice.equals("4")) {
					if (numProcs > 0 && PatternSelection > 0) {

						ProcessControl pc = new ProcessControl();
						pc.CreateProcesses(PatternSelection, numProcs, mqm);
						pc.startProcesses();
					} else {
						System.out.println("\n\nPlease Set all parameters before initiating the test!\n\n");
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// print outcome of test
		// parameters of test
		// # of messages passed
		// # of buffer overflows

		// =======
		// MessageQueueManager mqm = new MessageQueueManager();
		//
		// String msq1 = mqm.createQueue();
		// String msq2 = mqm.createQueue();
		// String msq3 = mqm.createQueue();
		//
		// ProcessControl pc = new ProcessControl();
		// pc.createProcess(mqm, msq1, msq2, 8);
		// pc.createProcess(mqm, msq2, msq3, 8);
		// pc.createProcess(mqm, msq3, msq1, 8);
		//
		// pc.startProcesses();
		//
		System.out.print(mqm);
		// >>>>>>> upstream/master
	}
}
