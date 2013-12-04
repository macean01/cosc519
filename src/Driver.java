<<<<<<< HEAD
import java.io.*;

=======
>>>>>>> upstream/master
public class Driver {

	static int numProcs;
	static Process[] myProcs;
	static int BufferSize;
	static int PatternSelection;

	public static void main(String[] args) {

<<<<<<< HEAD
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(
				System.in));

		String Choice = "";

		while (!Choice.equals("done")) {
			System.out.println("COSC519 Message Queue Project Main Menu:\n"
					+ "1. Set Proccess Number\n"
					+ "2. Set Message Passing Pattern\n"
					+ "3. Set Proccess Buffer Size\n" + "4. Start\n"
					+ "5. Print MessageQueues");
			try {

				Choice = bufferRead.readLine();
				switch (Choice) {
				case "1":
					SetProcessNumber(bufferRead);
					break;
				case "2":
					SetPattern(bufferRead);
					break;
				case "3":
					SetBufferSize(bufferRead);
					break;
				case "4":
					Start();
					break;

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// print outcome of test
		// parameters of test
		// # of messages passed
		// # of buffer overflows
	}

	private static void SetBufferSize(BufferedReader reader) {

		System.out.print("Please Enter Buffer Size:");
		try {
			BufferSize = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void SetPattern(BufferedReader reader) {

		System.out.print("Please Enter Pattern Configuration:");
		try {
			int patternSelection = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void Start() {

		if (numProcs > 0 && BufferSize > 0 && PatternSelection > 0) {

			MessageQueueManager mqm = new MessageQueueManager();
			PatternCalculation myCalc = new PatternCalculation(mqm, PatternSelection);
			for (int i = 0; i < numProcs; i++) {
				myProcs[i] = new Process(i, 1, BufferSize, mqm);
			}
			myCalc.CalculatePattern(myProcs);
			for(int i = 0; i< myProcs.length;i++){
				myProcs[i].run();
			}
		}
		else{
			System.out.println("\n\nPlease Set all parameters before initiating the test!\n\n");
		}
=======
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
>>>>>>> upstream/master
	}

	private static void SetProcessNumber(BufferedReader reader) {

		System.out.print("Please Enter Process Number:");
		try {
			numProcs = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
