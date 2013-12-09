import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageLock {

    private volatile AtomicInteger numWritersWaiting = new AtomicInteger(0);
    private final Lock lock = new ReentrantLock();
    private final Condition writersWriting = lock.newCondition();
    private final Condition readerReading = lock.newCondition();


    public void readLock() throws InterruptedException {

        lock.lock();
        try {
            while (numWritersWaiting.get() > 0) {
                synchronized (writersWriting) {
                    writersWriting.await();
                }
            }
        } catch (Exception e) { System.err.println(e); }

    }

    public void readUnlock() {

        synchronized (readerReading) {
            readerReading.signal();
        }
        lock.unlock();
    }

    public void writeLock() throws InterruptedException {


        lock.lock();
        numWritersWaiting.incrementAndGet();

        try {
            synchronized (readerReading) {
                readerReading.await();
            }
        } catch (Exception e) { System.err.println(e); }
    }

    public void writeUnlock() {

        synchronized (writersWriting) {
            numWritersWaiting.decrementAndGet();
            writersWriting.signal();
        }
        lock.unlock();

    }
}
