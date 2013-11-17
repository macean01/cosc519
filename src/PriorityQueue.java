import java.util.Map;
import java.util.TreeMap;

public class PriorityQueue<E> {

    private QueueItem head;
    private QueueItem tail;
    private int length;
    private TreeMap<Integer, Integer> priorityIndices;

    public PriorityQueue() {
        head = null;
        tail = null;
        length = 0;
        priorityIndices = new TreeMap<Integer, Integer>();
    }

    public PriorityQueue(E data) {
        QueueItem newItem = new QueueItem(data, null);
        head = newItem;
        tail = newItem;
        priorityIndices = new TreeMap<Integer, Integer>();
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
            QueueItem oldTail = tail;
            oldTail.setNext(newItem);
        }

        tail = newItem;
        length++;
    }

    public void enqueue(E data, int priority) {

        int key = 0;
        int firstIndex = 0;

        boolean foundKey = priorityIndices.containsKey(priority);

        if (priorityIndices.isEmpty()) {
            priorityIndices.put(priority, 1);
            enqueue(data);
        } else {

            for (Map.Entry<Integer,Integer> entry : priorityIndices.entrySet()) {

                key = entry.getKey();
                firstIndex = entry.getValue();

                if (priority < key) {
                    break;
                }
            }

            if (priority < priorityIndices.lastKey())
                firstIndex++;

            if (!foundKey) {
                priorityIndices.put(priority, firstIndex);

                if (firstIndex > length)
                    enqueue(data);
                else
                    insertBefore(data, firstIndex);

                if (priority > priorityIndices.firstKey()) {

                    for (Map.Entry<Integer,Integer> entry : priorityIndices.entrySet()) {

                        int iterKey = entry.getKey();
                        int oldIndex = entry.getValue();

                        if (iterKey < priority) {
                            priorityIndices.put(iterKey, ++oldIndex);
                        }
                    }
                }
            } else {

                int insertIndex = 0;

                for (Map.Entry<Integer,Integer> entry : priorityIndices.entrySet()) {

                    int iterKey = entry.getKey();
                    int oldIndex = entry.getValue();

                    if (iterKey < priority) {
                        priorityIndices.put(iterKey, ++oldIndex);
                    }

                    if (oldIndex > firstIndex) {
                        insertIndex = oldIndex;
                    }
                }

                insertBefore(data, (insertIndex - 1));
            }
        }
    }

    public QueueItem dequeue(){

        QueueItem oldHead = head;

        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
        }

        length--;
        return oldHead;
    }


    public void insertBefore(E data, int index) {

        int count = 1;
        QueueItem previous = head;
        QueueItem newItem = new QueueItem(data, null);

        if (index == 1) {
            newItem.setNext(head);
            head = newItem;
        } else if (index > length) {
            // do nothing
        } else {

            while (count < (index - 1)) {
               count++;
               previous = previous.getNext();
            }

            QueueItem next = previous.getNext();
            newItem.setNext(next);
            previous.setNext(newItem);

        }

        length++;
    }

    public void printIndices(){

        for (Map.Entry<Integer,Integer> entry : priorityIndices.entrySet()) {

            int tempKey = entry.getKey();
            int oldIndex = entry.getValue();

            System.out.println("priority = " + tempKey);
            System.out.println("firstIndex = " + oldIndex);
            System.out.println();

        }
    }


}
