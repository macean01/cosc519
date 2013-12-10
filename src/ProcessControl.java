import java.util.LinkedList;

public class ProcessControl {

	public int numProcesses;
	LinkedList<Process> processes;
	int Pattern;

	public ProcessControl() {
		numProcesses = 0; // num of processes, we will always insert at the end
							// of the list
		processes = new LinkedList<Process>();
	}

	private void createProcess(MessageQueueManager mqm, String produce,
			String consume, int numMessages) {
		Process newproc = new Process(mqm, produce, consume, numProcesses,
				numMessages);
		processes.add(newproc); // insert into list
		numProcesses = processes.size(); // or just ++
	}

	public void startProcesses() {
		long startTime = System.currentTimeMillis();
		Thread threads[] = new Thread[processes.size()];
		int i = 0;
		for (Process process : processes) {
			Thread t = new Thread(process);
			threads[i++] = t;
			t.start();
		}
		for (i = 0; i < processes.size(); i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("Time spent (ms): " + (endTime - startTime));
	}
		
	public void CreateProcesses(int pattern,int numProcs,int numMessages, MessageQueueManager mqm){
		if(pattern == PatternType.Chain){
			//set send/receiving queue handles
			for(int i = 0; i < numProcs;i++){
				if(i == 0){
					//beginning
					String queue1 = mqm.createQueue();
					String queue2 = mqm.createQueue();
					createProcess(mqm, queue1, queue2, numMessages);
				}
				else if(i == numProcs - 1 ){
					//end
					createProcess(mqm,processes.get(0).consumerId,processes.get(i-1).producerId, numMessages);
				}
				else{
					//middle
					createProcess(mqm, mqm.createQueue(), processes.get(i-1).producerId, numMessages);
				}
			}
		}
		else if(Pattern == PatternType.Pair){
			int pairedProcs = numProcs/2;
			for(int i = 0; i < pairedProcs;i++){
				String sendQueueHandle = mqm.createQueue();
				String recQueueHandle = mqm.createQueue();
				createProcess(mqm, sendQueueHandle, recQueueHandle, numMessages);
				createProcess(mqm, recQueueHandle, sendQueueHandle, numMessages);
			}
		}
	}

	public void PrintMetrics() {
		for(Process proc:processes){
			System.out.println("Process#"+proc.pid);
			System.out.println("Total messages sent: "+proc.sendMessages);
			System.out.println("Total messages received: "+proc.recvMessages);
		}
	}
}