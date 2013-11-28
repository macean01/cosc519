import java.util.LinkedList;


public class ProcessControl {

	public int numProcesses;
	LinkedList<Process> processes;


	public ProcessControl(){
		numProcesses=0; //num of processes, we will always insert at the end of the list
		processes= new LinkedList<Process>();
	}

	public void createProcess(MessageQueueManager mqm, String produce, String consume){
		Process newproc = new Process(mqm, produce, consume);
		processes.add(newproc); // insert into list
		numProcesses=processes.size(); //or just ++
	}

	public void startProcesses() {
		for (Process process : processes) {
			process.start();
		}

	}

	/* I don't think these methods are needed
	public void deleteProcess(int pid){

		processes.remove(findProcessIndex(pid)); //remove a process given a pid, we cant access the list directly w PID, because once the structure of the list is changed after a deletion, PID wont be accurate
		numProcesses=processes.size(); //or just --

	}

	//find process given a pid in the list. deleting by the pid wont work because after a deletion, the structure of the list will shift and the pid wont match the index
	public int findProcessIndex(int pid){
		int result =  -1;
		for(int i =0;i<processes.size();i++){
			if(processes.get(i).pid == pid){ //if given pid == current pid
				return i;
			}
		}

		return result; //return -1 if not found
	}
	*/

}
