import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageLock {

    private AtomicInteger numWritersWaiting = new AtomicInteger(0);

    private final Lock lock = new ReentrantLock();
    private final Condition writersWaiting = lock.newCondition();
    private final Condition readerReading = lock.newCondition();


    public void readLock() throws InterruptedException {

        try {
            while (numWritersWaiting.get() > 0) {
                writersWaiting.await();
            }
        } finally {
            lock.lock();
        }

    }

    public void readUnlock() {

        readerReading.signal();
        lock.unlock();
    }

    public void writeLock() throws InterruptedException {

        numWritersWaiting.incrementAndGet();
        
        try {
            readerReading.await();
        } finally {
            lock.lock();
        }
    }

    public void writeUnlock() {

        numWritersWaiting.decrementAndGet();
        writersWaiting.signal();
        lock.unlock();

    }
}
