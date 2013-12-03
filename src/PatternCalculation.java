
public class PatternCalculation {
	
	MessageQueueManager Mqm;
	int Pattern;
	
	public PatternCalculation(MessageQueueManager myMqm,int myPattern){
		Mqm = myMqm;
		Pattern = myPattern;
	}
	
	public Process[] CalculatePattern(Process[] myProcs){
		if(Pattern == PatternType.Chain){
			//set send/receiving queue handles
			for(int i = 0; i < myProcs.length;i++){
				if(i == 0){
					//beginning
					String sendQueueHandle = Mqm.createQueue();
					String recQueueHandle = Mqm.createQueue();
					myProcs[i].sendQueueHandle = sendQueueHandle;
					myProcs[i].myQueueHandle = recQueueHandle;
				}
				else if(i+1 == myProcs.length){
					//end
					myProcs[i].sendQueueHandle = myProcs[0].myQueueHandle;
				}
				else{
					//middle
					myProcs[i].myQueueHandle = myProcs[i-1].sendQueueHandle;
					String sendQueueHandle = Mqm.createQueue();
					myProcs[i].sendQueueHandle = sendQueueHandle;
				}
			}
		}
		else if(Pattern == PatternType.Pair){
			int pairedProcs = myProcs.length/2;
			for(int i = 1; i < pairedProcs;i++){
				int firstMember = (i*2)-2;
				int secondMember = (i*2)-1;
				
				String sendQueueHandle = Mqm.createQueue();
				String recQueueHandle = Mqm.createQueue();
				myProcs[firstMember].sendQueueHandle = sendQueueHandle;
				myProcs[firstMember].myQueueHandle = recQueueHandle;
				myProcs[secondMember].sendQueueHandle = recQueueHandle;
				myProcs[secondMember].myQueueHandle = sendQueueHandle;
			}
		}
		else{
			for(int i = 0; i < myProcs.length;i++){
				myProcs[i].isRandomSenario = true;
			}
		}
		return myProcs;
	}
}
