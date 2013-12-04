import java.util.LinkedList;


public class ProcessControl {

	public int numProcesses;
	LinkedList<Process> processes;


	public ProcessControl(){
		numProcesses=0; //num of processes, we will always insert at the end of the list
		processes= new LinkedList<Process>();
	}

	public void createProcess(MessageQueueManager mqm, String produce, String consume, int numMessages){
		Process newproc = new Process(mqm, produce, consume, numProcesses, numMessages);
		processes.add(newproc); // insert into list
		numProcesses=processes.size(); //or just ++
	}

	public void startProcesses() {
		for (Process process : processes) {
			Thread t = new Thread(process);
			t.start();
		}

	}

}
