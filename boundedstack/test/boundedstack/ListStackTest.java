package boundedstack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListStackTest {

    private Stack<String> empty3; // empty stack with capacity of 3
    private Stack<String> abc6; // stack with elements A, B, and C with capacity of 6

    @Before
    public void setUp() throws Exception {
        empty3 = new ListStack<>(3);
        abc6 = new ListStack<>(6);
        abc6.push("A");
        abc6.push("B");
        abc6.push("C");
    }

    @Test
    public void testInitialStacks() {
        assertEquals(3, empty3.capacity());
        assertEquals(0, empty3.depth());
        assertEquals(6, abc6.capacity());
        assertEquals(3, abc6.depth());
    }

    @Test(expected = IllegalStateException.class)
    public void pushException() {
        abc6.push("X");
        abc6.push("X");
        abc6.push("X");
        abc6.push("X"); // <== This call to 'push' should throw an exception
        fail(); // <== Clarifies that the test has failed because it reached this line
    }

    // Note: The push method is tested by the setup test.

    @Test(expected = IllegalStateException.class)
    public void pop() {
        assertEquals("C", abc6.pop());
        assertEquals(2, abc6.depth());
        assertEquals("B", abc6.pop());
        assertEquals(1, abc6.depth());
        assertEquals("A", abc6.pop());
        assertEquals(0, abc6.depth());
        abc6.pop();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStackConstructor() {
        // argument less than and equal to 0
        Stack<String> neg0 = new ListStack<>(0);
//        neg0 = new ListStack<>(-1);
    }

    // Note: depth and capacity are simple getters that are used to test other methods.
}