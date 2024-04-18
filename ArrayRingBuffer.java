package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {

        if (this.isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        } else {
            if (rb[last] == null) {
                rb[last] = x;
            } else {
                if (last == rb.length - 1) {
                    last = -1;
                    last++;
                    rb[last] = x;
                } else {
                    last++;
                    rb[last] = x;
                }
            }
            if (fillCount < rb.length) {fillCount++;}
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {

        if (this.isEmpty()) {
           throw new RuntimeException("Ring Buffer underflow");
        } else {
            T item = rb[first];
            rb[first] = null;
            fillCount--;
            if (fillCount == 0) {first = -1;}
            first++;
            if (first == rb.length) {first = 0;}
            return item;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {

        if (this.isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        } else {
            return rb[first];
        }
    }

    @Override
    public int capacity() {
        int arraySize;
        arraySize = rb.length;
        return arraySize;
    }

    @Override
    public int fillCount() {return this.fillCount;}

    @Override
    public Iterator <T> iterator() {
        return new ArrayRingIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o.equals(this)) {
            return true;
        } else {
            return false;
        }
    }

    private class ArrayRingIterator implements Iterator<T> {
        private int numLocation;

        public ArrayRingIterator() {numLocation = 0;}
        public boolean hasNext() {return numLocation < rb.length;}
        public T next() {
            T anItem = rb[numLocation];
            numLocation += 1;
            return anItem;
        }

    }
}

