import java.util.LinkedList;


public class MessageQueue {

	String id;
	LinkedList<Message> list;
	int messages = 0;
	boolean lock = false;

	public MessageQueue(int id)
	{
		this.id = ((Integer)id).toString();
		list = new LinkedList<Message>();
	}

	public void write(Message buff)
	{
		list.push(buff);
		messages++;
	}

	public Message read()
	{
		if (messages > 0) {
			messages--;
			return list.pop();
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
