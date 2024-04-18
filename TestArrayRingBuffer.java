package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> o = new ArrayRingBuffer<>(5);
        o.enqueue(3);
        o.enqueue(4);
        o.enqueue(5);
        int a = o.dequeue(); // test dequeue()
        assertEquals(a,3);
        o.enqueue(6);
        o.enqueue(7);
        o.enqueue(8);
        int m = o.peek(); // test peek()
        assertEquals(4,m);
        assertTrue(o.isFull());
        assertEquals(o.capacity(),5);
        assertEquals(o.fillCount(),5); // asserts that the array is full
        int b = o.peek();
        assertEquals(b,4); //another test peek()
        o.dequeue();
        o.dequeue();
        o.dequeue();
        o.dequeue();
        o.dequeue();
        o.dequeue();
        o.enqueue(100);
        o.dequeue();
        int c = o.peek();
        assertEquals(100,c); // ensures that the pointers for first and last are moving circularly

    }
}
