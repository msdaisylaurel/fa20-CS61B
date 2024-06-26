package es.datastructur.synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    Iterator<T> iterator();

    boolean equals(Object o);

    int capacity();      // return size of the buffer

    int fillCount();    // return number of items currently in the buffer

    void enqueue(T x);  // add item x to the end

    T dequeue();        // delete and return item from the front

    T peek();           // return (but do not delete) item from the front

    default boolean isEmpty() {     // is the buffer empty fillCount equals zero)?
        int itemsInBuffer = 0;
        itemsInBuffer = fillCount();
        if (itemsInBuffer == 0) {
            return true;
        }
        return false;
    }

    default boolean isFull() {      // is the buffer full (fillCount is the same as capacity)?
        int itemsInBuffer = 0;
        int bufferSize = 0;
        itemsInBuffer = fillCount();
        bufferSize = capacity();
        if (itemsInBuffer == bufferSize) {
            return true;
        }
        return false;
    }
}
