package es.datastructur.synthesizer;

 // Note: This file will not compile until you complete task 1 (BoundedQueue).

public class GuitarString {

    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */

    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */

    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */

    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);

        buffer = new ArrayRingBuffer<>(capacity);

        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */

    public void pluck() {
        int replace = buffer.capacity() - 1;

        for (int i = 0; i <= replace; i++) {
            buffer.dequeue();
            Double noise = Math.random() - 0.5;
            buffer.enqueue(noise);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. */

    public void tic() {
        Double newSample = buffer.dequeue();
        newSample = ((newSample + buffer.peek()) / 2) * DECAY;
        buffer.enqueue(newSample);
    }

    /* Return the double at the front of the buffer. */

    public double sample() {
        Double frontSample = buffer.peek();
        return frontSample;
    }
}
