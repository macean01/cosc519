import java.util.LinkedList;


public class MessageQueue {

	String id;
	LinkedList<String> list;
	int messages = 0;

	public MessageQueue(int id)
	{
		this.id = ((Integer)id).toString();
		list = new LinkedList<String>();
	}

	public void write(String buff)
	{
		list.push(buff);
		messages++;
	}

	public String read()
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
}
