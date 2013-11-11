import java.util.LinkedList;


public class MessageQueue {

	String id;
	LinkedList<Message> list;
	int messages = 0;

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
}
