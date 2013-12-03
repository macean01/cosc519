import java.util.Map;
import java.util.TreeMap;

public class PriorityQueue<E> {

    private QueueItem head;
    private QueueItem tail;
    private int length;
    private TreeMap<Integer, Index> priorityIndices;

    public PriorityQueue() {
        head = null;
        tail = null;
        length = 0;
        priorityIndices = new TreeMap<Integer, Index>();
    }

    public PriorityQueue(E data) {
        QueueItem newItem = new QueueItem(data, null);
        head = newItem;
        tail = newItem;
        priorityIndices = new TreeMap<Integer, Index>();
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

    private void append(E data) {

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

        Index index = new Index();

        boolean foundKey = priorityIndices.containsKey(priority);

        if (priorityIndices.isEmpty()) {
            Index newIndex = new Index(1, 1);
            priorityIndices.put(priority, newIndex);
            append(data);
        } else {

            if (!foundKey) {

                if (priority > priorityIndices.lastKey()) {

                    index = new Index(1,1);
                    priorityIndices.put(priority, index);
                    incrementIndices(priority);
                    insertBefore(data, 1);

                } else if (priority < priorityIndices.firstKey()) {

                    index = priorityIndices.firstEntry().getValue();
                    int newFirstLast = index.getLastIndex() + 1;
                    index = new Index(newFirstLast, newFirstLast);
                    priorityIndices.put(priority, index);
                    append(data);

                } else {

                    int newFirstLast = getInsertionIndex(priority);
                    index = new Index(newFirstLast, newFirstLast);
                    priorityIndices.put(priority, index);
                    insertBefore(data, newFirstLast);
                    incrementIndices(priority);
                }

            } else {

                index = priorityIndices.get(priority);
                index.incrementLastIndex();
                priorityIndices.put(priority, index);

                if (priority == priorityIndices.firstKey()) {
                    append(data);
                }

                if (priority > priorityIndices.firstKey()) {

                    int insertHere = getInsertionIndex(priority);
                    incrementIndices(priority);
                    insertBefore(data, insertHere);
                }
            }
        }

        //printIndices();
    }

    public QueueItem dequeue(){

        QueueItem oldHead = head;

        if (length == 1) {
            head = null;
            tail = null;
            priorityIndices.clear();
        } else {

            head = head.getNext();

            int priority = priorityIndices.lastKey();
            Index index = priorityIndices.get(priority);

            index.decrementLastIndex();

            int first = index.getFirstIndex();
            int last = index.getLastIndex();

            if (last < first)
                priorityIndices.remove(priority);
            else
                priorityIndices.put(priority, index);

            decrementIndices(priority);
        }

        length--;
        return oldHead;
    }


    private void insertBefore(E data, int index) {

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

        for (Map.Entry<Integer, Index> entry : priorityIndices.entrySet()) {

            int tempKey = entry.getKey();
            Index tempIndex = entry.getValue();

            System.out.println("priority = " + tempKey);
            System.out.println("firstIndex = " + tempIndex.getFirstIndex());
            System.out.println("lastIndex = " + tempIndex.getLastIndex());
            System.out.println();

        }
    }

    public void incrementIndices(int priority) {

        for (Map.Entry<Integer, Index> entry : priorityIndices.entrySet()) {

            int currentPriority = entry.getKey();
            Index index = entry.getValue();

            if (currentPriority < priority) {
                index.incrementBothIndices();
                priorityIndices.put(currentPriority, index);
            }
        }
    }

    public void decrementIndices(int priority) {

        for (Map.Entry<Integer, Index> entry : priorityIndices.entrySet()) {

            int currentPriority = entry.getKey();
            Index index = entry.getValue();

            if (currentPriority < priority) {
                index.decrementBothIndices();
                priorityIndices.put(currentPriority, index);
            }
        }
    }

    public int getInsertionIndex(int priority) {

        Index index = new Index();


        for (Map.Entry<Integer, Index> entry : priorityIndices.entrySet()) {

            if (entry.getKey() == priority)
                break;

            if (entry.getKey() > priority)
                break;

            index = entry.getValue();
        }

        return index.getFirstIndex();

    }
}
