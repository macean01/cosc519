
public class QueueItem<E> {

    private E data;
    private QueueItem next;
    private boolean isNotified = false;

    public QueueItem() {
        data = null;
        next = null;
    }

    public QueueItem(E _data) {
        data = _data;
        next = null;
    }

    public QueueItem(E _data, QueueItem _next) {
        data = _data;
        next = _next;
    }

    public void setNext(QueueItem _next) {
        next = _next;
    }

    public QueueItem getNext() {
        return next;
    }

    public E getData() {
        return data;
    }

    public synchronized void doWait() throws InterruptedException {
        while(!isNotified){
            this.wait();
        }
        this.isNotified = false;
    }

    public synchronized void doNotify() {
        this.isNotified = true;
        this.notify();
    }

    public boolean equals(Object o) {
        return this == o;
    }


}
