package boundedpipe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedPipeTest {
    private Pipe<String> pipeABC4;
    private Pipe<String> pipeEmpty2;

    @Before
    public void setUp(){
        pipeABC4 = new LinkedPipe<>(4);
        pipeABC4.append("A");
        pipeABC4.append("B");
        pipeABC4.append("C");
        pipeEmpty2 = new LinkedPipe<>(2);
    }

    @Test
    public void testInitialPipes() {
        assertEquals(4, pipeABC4.capacity());
        assertEquals(3, pipeABC4.length());
        assertEquals(2, pipeEmpty2.capacity());
        assertEquals(0, pipeEmpty2.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrependABC4Full() {
        pipeABC4.prepend("D");
        pipeABC4.prepend("E");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrependABC4NullElement(){
        pipeABC4.prepend(null);
    }

    @Test
    public void testPrependABC4Success(){
        pipeABC4.prepend("D");
        assertEquals("D",pipeABC4.first());
        assertEquals(4,pipeABC4.length());
    }

    @Test
    public void testPrependEmpty2Success(){
        pipeEmpty2.prepend("A");
        assertEquals("A",pipeEmpty2.first());
        assertEquals(1,pipeEmpty2.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testAppendABC4Full() {
        pipeABC4.append("D");
        pipeABC4.append("E");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendABC4NullElement(){
        pipeABC4.append(null);
    }

    @Test
    public void testAppendABC4Success(){
        pipeABC4.append("D");
        assertEquals("D",pipeABC4.last());
        assertEquals(4,pipeABC4.length());
    }

    @Test
    public void testAppendEmpty2Success(){
        pipeEmpty2.append("A");
        assertEquals("A",pipeEmpty2.last());
        assertEquals(1,pipeEmpty2.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveFirstEmpty2() {
        pipeEmpty2.removeFirst();
    }

    @Test
    public void testRemoveFirstABC4() {
        assertEquals("A", pipeABC4.removeFirst());
        assertEquals(2, pipeABC4.length());
    }

    @Test
    public void testRemoveFirstOneElement() {
        pipeEmpty2.append("A");
        assertEquals("A", pipeEmpty2.removeFirst());
        assertEquals(0, pipeEmpty2.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveLastEmpty2() {
        pipeEmpty2.removeLast();
    }

    @Test
    public void testRemoveLastABC4Success() {
        assertEquals("C", pipeABC4.removeLast());
        assertEquals(2, pipeABC4.length());
    }

    @Test
    public void testRemoveLastOneElement() {
        pipeEmpty2.append("A");
        assertEquals("A", pipeEmpty2.removeLast());
        assertEquals(0, pipeEmpty2.length());
    }

    @Test
    public void length() {
        assertEquals(3, pipeABC4.length());
        assertEquals(0, pipeEmpty2.length());
        pipeABC4.append("D");
        assertEquals(4, pipeABC4.length());
        pipeEmpty2.append("A");
        assertEquals(1, pipeEmpty2.length());
    }

    @Test
    public void testNewInstanceABC4() {
        Pipe<String> newPipe = pipeABC4.newInstance();
        assertEquals(4,newPipe.capacity());
        assertEquals(0,newPipe.length());
    }

    @Test
    public void testNewInstanceEmpty2() {
        Pipe<String> newPipe = pipeEmpty2.newInstance();
        assertEquals(2, newPipe.capacity());
        assertEquals(0, newPipe.length());
    }

    @Test
    public void testClearABC4() {
        pipeABC4.clear();
        assertEquals(4,pipeABC4.capacity());
        assertEquals(0,pipeABC4.length());
    }

    @Test
    public void testClearEmpty2(){
        pipeEmpty2.clear();
        assertEquals(2,pipeEmpty2.capacity());
        assertEquals(0,pipeEmpty2.length());
    }

    @Test
    public void testFirstABC4() {
        assertEquals("A",pipeABC4.first());
    }

    @Test
    public void testFirstEmpty2(){
        assertNull(pipeEmpty2.first());
    }

    @Test
    public void testLastABC4() {
        assertEquals("C",pipeABC4.last());
    }

    @Test
    public void testLastEmpty2(){
        assertNull(pipeEmpty2.last());
    }

    @Test
    public void testIteratorABC4() {
        StringBuilder result = new StringBuilder();
        for (String s : pipeABC4) {
            result.append(s);
        }
        assertEquals("ABC", result.toString());
    }

    @Test
    public void testIteratorEmpty2(){
        StringBuilder result = new StringBuilder();
        for (String s : pipeEmpty2) {
            result.append(s);
        }
        assertEquals("", result.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNext() {
        pipeEmpty2.iterator().next();
    }

    @Test
    public void testEmpty2ToString() {
        assertEquals("[]:2", pipeEmpty2.toString());
    }

    @Test
    public void testABC4ToString() {
        assertEquals("[A, B, C]:4", pipeABC4.toString());
    }

    @Test
    public void testHashCodeABC4AndDifferentABC4() {
        Pipe<String> pipeABC4New = new LinkedPipe<>(4);
        pipeABC4New.append("A");
        pipeABC4New.append("B");
        pipeABC4New.append("C");
        assertEquals(pipeABC4.hashCode(), pipeABC4New.hashCode());
    }

    @Test
    public void testHashCodeABC4AndABC6() {
        Pipe<String> pipeABC6 = new LinkedPipe<>(6);
        assertNotEquals(pipeABC4.hashCode(), pipeABC6.hashCode());
    }

    @Test
    public void testHashCodeABC4AndAB4() {
        Pipe<String> pipeAB4 = new LinkedPipe<>(4);
        pipeAB4.append("A");
        pipeAB4.append("B");
        assertNotEquals(pipeABC4.hashCode(), pipeAB4.hashCode());
    }

    @Test
    public void testABC4EqualsNull() {
        assertFalse(pipeABC4.equals(null));
    }

    @Test
    public void testABC4EqualsSelf() {
        assertTrue(pipeABC4.equals(pipeABC4));
    }

    @Test
    public void testABC4EqualsNonPipe() {
        List<String> list = new ArrayList<>();
        assertFalse(pipeABC4.equals(list));
    }

    @Test
    public void testABC4EqualsDifferentABC4() {
        Pipe<String> pipeABC4New = new LinkedPipe<>(4);
        pipeABC4New.append("A");
        pipeABC4New.append("B");
        pipeABC4New.append("C");
        assertTrue(pipeABC4.equals(pipeABC4New));
    }

    @Test
    public void testABC4EqualsABC6() {
        Pipe<String> pipeABC6 = new LinkedPipe<>(6);
        assertFalse(pipeABC4.equals(pipeABC6));
    }

    @Test
    public void testABC4EqualsAB4() {
        Pipe<String> pipeAB4 = new LinkedPipe<>(4);
        pipeAB4.append("A");
        pipeAB4.append("B");
        assertFalse(pipeABC4.equals(pipeAB4));
    }

    @Test
    public void testABC6LinkedPipeCopy() {
        Pipe<String> newPipe = pipeABC4.copy();
        assertEquals(3, newPipe.length());
        assertEquals(4, newPipe.capacity());
        assertEquals(pipeABC4, newPipe);
        String s1 = pipeABC4.removeFirst();
        String s2 = newPipe.removeFirst();
        assertEquals(s1, s2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC4LinkedPipeAppendAllNullPipe() {
        pipeABC4.appendAll(null);
    }

    @Test
    public void testABC4LinkedPipeAppendAllSuccessAddInNoElement() {
        pipeABC4.appendAll(pipeEmpty2);
        assertEquals(3, pipeABC4.length());
    }

    @Test
    public void testABC4LinkedPipeAppendAllSuccessAddIn() {
        pipeEmpty2.append("D");
        pipeABC4.appendAll(pipeEmpty2);
        assertEquals("D", pipeABC4.last());
        assertEquals(4, pipeABC4.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testABC4LinkedPipeAppendAllLengthOutOfCapacity() {
        Pipe<String> largerLength = new LinkedPipe<>(6);
        largerLength.append("a");
        largerLength.append("b");
        largerLength.append("c");
        largerLength.append("d");
        largerLength.append("e");
        pipeABC4.appendAll(largerLength);
    }

    @Test(expected = IllegalStateException.class)
    public void testABC4LinkedPipeAppendAllTotalLengthOutOfCapacity() {
        pipeEmpty2.append("a");
        pipeEmpty2.append("b");
        pipeABC4.appendAll(pipeEmpty2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC4LinkedPipeIllegalCapacity() {
        pipeABC4 = new LinkedPipe<>(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty2LinkedPipeIllegalCapacity() {
        pipeEmpty2 = new LinkedPipe<>(-1);
    }
}