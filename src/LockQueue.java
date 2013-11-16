
public class LockQueue<E> {

    private QueueItem head;
    private int length;

    public LockQueue() {
        head = null;
        length = 0;
    }

    public LockQueue(E data) {
        QueueItem newItem = new QueueItem(data, null);
        head = newItem;
        length = 1;
    }

    public QueueItem getHead() {
        return head;
    }

    public int getLength() {
        return length;
    }

    public boolean isEmpty(){
        return (length == 0);
    }

    public void enqueue(E data) {

        QueueItem newItem = new QueueItem(data, null);

        if (head == null) {
            head = newItem;
        } else {

            QueueItem current = head;

            while (current.getNext() != null)
                current = current.getNext();

            current.setNext(newItem);
        }

        length++;
    }

    public QueueItem dequeue(){

        QueueItem oldHead = head;
        head = head.getNext();
        length--;
        return oldHead;
    }




}
