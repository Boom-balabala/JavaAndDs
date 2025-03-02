package Learn.DS.interfacePackage;

/**
 * @author WYL
 */
public interface Queue<T> {
    public void addQueue(T val);

    public T removeQueue();

    public boolean isEmpty();

    public int size();

    public void clear();

    public void printQueue();
}
