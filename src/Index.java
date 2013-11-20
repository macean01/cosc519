/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 11/19/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class Index {

    private int firstIndex;
    private int lastIndex;

    public Index() {
        firstIndex = 0;
        lastIndex = 0;
    }

    public Index(int _firstIndex, int _lastIndex) {
        firstIndex = _firstIndex;
        lastIndex = _lastIndex;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setFirstIndex(int _firstIndex) {
        firstIndex = _firstIndex;
    }

    public void setLastIndex(int _lastIndex) {
        lastIndex = _lastIndex;
    }

    public void incrementBothIndices() {
        firstIndex++;
        lastIndex++;
    }

    public void incrementFirstIndex() {
        firstIndex++;
    }

    public void incrementLastIndex() {
        lastIndex++;
    }

    public void decrementBothIndices() {
        firstIndex--;
        lastIndex--;
    }

    public void decrementFirstIndex() {
        firstIndex--;
    }

    public void decrementLastIndex() {
        lastIndex--;
    }


}
