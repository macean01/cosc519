
public class MessageLock {

    private int numWriters;
    private int numReaders;
    //private

    public MessageLock() {
        numWriters = 0;
        numReaders = 0;
    }

    public MessageLock(int _numWriters, int _numReaders) {
        if (_numWriters < 0)
            throw new IllegalArgumentException(numWriters + " < 0");
        if (_numReaders < 0)
            throw new IllegalArgumentException(numReaders + " < 0");
        numWriters = _numWriters;
        numReaders = _numReaders;
    }


}
