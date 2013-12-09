import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageLock {

    private Thread owner = null;
    private volatile AtomicInteger numWritersWaiting = new AtomicInteger(0);
    private volatile AtomicInteger numWritersWriting = new AtomicInteger(0);
    private volatile AtomicInteger numReadersReading = new AtomicInteger(0);
    private final Lock lock = new ReentrantLock();
    private final Condition writersWriting = lock.newCondition();
    private final Condition readerReading = lock.newCondition();


    public void readLock() throws InterruptedException {

        lock.lock();
        try {
            while (numWritersWaiting.get() > 0 && numWritersWriting.get() > 0) {
                synchronized (writersWriting) {
                    writersWriting.await();
                }
            }

            while (numReadersReading.get() > 0) {
                readerReading.await();
            }

            numReadersReading.incrementAndGet();
        } catch (Exception e) { System.err.println(e); }

    }

    public void readUnlock() {

        synchronized (readerReading) {
            readerReading.signalAll();
            numReadersReading.decrementAndGet();
        }
        lock.unlock();
    }

    public void writeLock() throws InterruptedException {

        lock.lock();
        numWritersWaiting.incrementAndGet();

        try {
            while (numReadersReading.get() > 0) {
                synchronized (readerReading) {
                    readerReading.await();
                }
            }

            while (numWritersWriting.get() > 0) {
                writersWriting.await();
            }

            numWritersWaiting.decrementAndGet();
            numWritersWriting.incrementAndGet();

        } catch (Exception e) { System.err.println(e); }
    }

    public void writeUnlock() {

        synchronized (writersWriting) {
            numWritersWriting.decrementAndGet();
            writersWriting.signalAll();
        }
        lock.unlock();

    }
}
