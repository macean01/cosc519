
public class process {
	
	
	//Main components:
	//Code, Data, Resources, Status

	int pid; //pid set where? not here
	int priority;
	
	char[] data; //do something with this data
	
	//Do we need a variable to hold the process' code?
	
	boolean state_new;
	boolean state_ready;
	boolean state_running;
	boolean state_waiting;
	boolean state_terminated;
	
	
	
	
	
	//constructor
	public process() {
		pid = -1;
		priority = 0;
		data  = new char[1024]; //arbitrary buffer..for now
		state_new = true;
		state_ready = false;
		state_running = false;
		state_waiting = false;
		state_terminated = false;
	}

	
	
	public boolean recMessage(){
	
		
		//the process will be continuosly listening as long as the state is _ready ?
		//while process ready()
			//remove msg from the queue (highest priority removed?)
			//do something with the msg
			//return true??
		
		
	}
	
	
	public void sendMessage(Message m, Process target){
		
		//send message out to the queue
		//queue should contain Message objects that contain a data portion and target (PID?)
	}
	
	
	
	public boolean isState_new() {
		return state_new;
	}
	
	public void setState_new() {
		state_new = true;
		state_ready = false;
		state_running = false;
		state_waiting = false;
		state_terminated = false;
	}
	
	public boolean isState_ready() {
		return state_ready;
	}
	public void setState_ready() {
		state_new = false;
		state_ready = true;
		state_running = false;
		state_waiting = false;
		state_terminated = false;
	}
	public boolean isState_running() {
		return state_running;
	}
	public void setState_running() {
		state_new = false;
		state_ready = false;
		state_running = true;
		state_waiting = false;
		state_terminated = false;
	}
	public boolean isState_waiting() {
		return state_waiting;
		
	}
	public void setState_waiting() {
		state_new = false;
		state_ready = false;
		state_running = false;
		state_waiting = true;
		state_terminated = false;
	}
	public boolean isState_terminated() {
		return state_terminated;
	}
	public void setState_terminated() {
		state_new = false;
		state_ready = false;
		state_running = false;
		state_waiting = false;
		state_terminated = true;
	}
	
	
	
	
	
	
	
	
	
	
}
