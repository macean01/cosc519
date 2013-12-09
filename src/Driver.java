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
		
		ProcessControl pc = new ProcessControl();
		
		MessageLock sharedMessageLock = new MessageLock();

		MessageQueueManager mqm = new MessageQueueManager();

		mqm.setMyLock(sharedMessageLock);

		String Choice = "";

		while (!Choice.equals("done")) {
			System.out.println("COSC519 Message Queue Project Main Menu:\n"
							+ "1. Set Proccess Number\n"
							+ "2. Set Message Passing Pattern\n"
							+ "3. Start Queue Simulation\n"
							+ "4. Print MessageQueueManager");
			try {

				Choice = bufferRead.readLine();

				if (Choice.equals("1")) {
					System.out.println("\nPlease Enter Process Number:");
					try {
						String procs = bufferRead.readLine();
						numProcs = Integer.parseInt(procs);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (Choice.equals("2")) {
					System.out.println("\nPlease Enter Pattern Configuration:");
					try {
						String pattern = bufferRead.readLine();
						PatternSelection = Integer.parseInt(pattern);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (Choice.equals("3")) {
					if (numProcs > 0 && PatternSelection > -1) {
						pc.CreateProcesses(PatternSelection, numProcs, mqm);
						pc.startProcesses();
					} else {
						System.out.println("\nParameters do not meet requirement to initiate the test!\n\n");
					}
				} else if (Choice.equals("4")) {
					System.out.println(mqm);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n***************************END SIM*******************************");
		mqm.PrintMetrics();
		pc.PrintMetrics();
	}
}
