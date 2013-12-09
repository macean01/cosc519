import java.util.LinkedList;


public class MessageQueue {

	String id;
	LinkedList<Message> list;
	int messages = 0;
	boolean lock = false;
	MessageLock myLock;

	public MessageQueue(int id)
	{
		this.id = ((Integer)id).toString();
		list = new LinkedList<Message>();
	}

	public void write(Message buff)
	{
//		try {
//			if(myLock != null)
//				myLock.writeLock();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		list.push(buff);
		messages++;
//		if(myLock != null)
//			myLock.writeUnlock();
	}

	public Message read()
	{
		if (messages > 0) {
			messages--;
//			try {
//				if(myLock != null)
//					myLock.readLock();
				Message mesTry = list.pop();
//				if(myLock != null)
//					myLock.readUnlock();
				return mesTry;
//			} catch (InterruptedException e) {
//				
//				e.printStackTrace();
//			}
		} else {
			return null;
		}
	}

	public boolean hasMessages()
	{
		return messages > 0;
	}

	public String toString()
	{
		String ret = "";

		if (this.list.isEmpty())
			return "empty";

		for (Message message : this.list) {
			ret += message + "\t";
		}

		return ret;
	}
}
