package boundedpipe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListPipeTest {

    private Pipe<String> pipeABC6;
    private Pipe<String> pipeEmpty3;
    private Pipe<String> pipeFull2;

    @Before
    public void setUp() {
        pipeEmpty3 = new ListPipe<>(3);
        pipeABC6 = new ListPipe<>(6);
        pipeABC6.append("A");
        pipeABC6.append("B");
        pipeABC6.append("C");
        pipeFull2 = new ListPipe<>(2);
        pipeFull2.append("E");
        pipeFull2.append("F");
    }

    @Test
    public void testInitialPipes() {
        assertEquals(3, pipeEmpty3.capacity());
        assertEquals(0, pipeEmpty3.length());
        assertEquals(6, pipeABC6.capacity());
        assertEquals(3, pipeABC6.length());
        assertEquals(2, pipeFull2.capacity());
        assertEquals(2, pipeFull2.length());
    }

    @Test
    public void testPrependEmpty3Success() {
        pipeEmpty3.prepend("A");
        assertEquals("[A]:3",pipeEmpty3.toString());
        assertEquals(1,pipeEmpty3.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrependEmpty3NullElement(){
        pipeEmpty3.prepend(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testPrependEmpty3Full(){
        pipeEmpty3.prepend("A");
        pipeEmpty3.prepend("B");
        pipeEmpty3.prepend("C");
        pipeEmpty3.prepend("D");
    }

    @Test
    public void testPrependABC6Success() {
        pipeABC6.prepend("A");
        assertEquals("[A, A, B, C]:6",pipeABC6.toString());
        assertEquals(4,pipeABC6.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrependABC6NullElement(){
        pipeABC6.prepend(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testPrependABC6Full(){
        pipeABC6.prepend("A");
        pipeABC6.prepend("B");
        pipeABC6.prepend("C");
        pipeABC6.prepend("D");
    }

    @Test(expected = IllegalStateException.class)
    public void testPrependFull2Full(){
        pipeFull2.prepend("A");
    }

    @Test
    public void testAppendEmpty3Success() {
        pipeEmpty3.append("A");
        assertEquals("[A]:3",pipeEmpty3.toString());
        assertEquals(1,pipeEmpty3.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendEmpty3NullElement(){
        pipeEmpty3.append(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testAppendEmpty3Full(){
        pipeEmpty3.append("A");
        pipeEmpty3.append("B");
        pipeEmpty3.append("C");
        pipeEmpty3.append("D");
    }

    @Test
    public void testAppendABC6Success() {
        pipeABC6.append("A");
        assertEquals("[A, B, C, A]:6",pipeABC6.toString());
        assertEquals(4,pipeABC6.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendABC6NullElement(){
        pipeABC6.append(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testAppendABC6Full(){
        pipeABC6.append("A");
        pipeABC6.append("B");
        pipeABC6.append("C");
        pipeABC6.append("D");
    }

    @Test(expected = IllegalStateException.class)
    public void testAppendFull2Full(){
        pipeFull2.append("A");
    }

    @Test(expected = IllegalStateException.class)
    public void removeFirstEmpty3Empty() {
        pipeEmpty3.removeFirst();
    }

    @Test
    public void removeFirstABC6Success(){
        String s=pipeABC6.removeFirst();
        assertEquals("A",s);
        assertEquals(2,pipeABC6.length());
    }

    @Test(expected = IllegalStateException.class)
    public void removeFirstABC6Empty() {
        pipeABC6.removeFirst();
        pipeABC6.removeFirst();
        pipeABC6.removeFirst();
        pipeABC6.removeFirst();
    }

    @Test
    public void removeFirstFull2Success(){
        String s=pipeFull2.removeFirst();
        assertEquals("E",s);
        assertEquals(1,pipeFull2.length());
    }

    @Test(expected = IllegalStateException.class)
    public void removeFirstFull2Empty() {
        pipeFull2.removeFirst();
        pipeFull2.removeFirst();
        pipeFull2.removeFirst();
    }

    @Test(expected = IllegalStateException.class)
    public void removeLastEmpty3Empty() {
        pipeEmpty3.removeLast();
    }

    @Test
    public void removeLastABC6Success(){
        String s=pipeABC6.removeLast();
        assertEquals("C",s);
        assertEquals(2,pipeABC6.length());
    }

    @Test(expected = IllegalStateException.class)
    public void removeLastABC6Empty() {
        pipeABC6.removeLast();
        pipeABC6.removeLast();
        pipeABC6.removeLast();
        pipeABC6.removeLast();
    }

    @Test
    public void removeLastFull2Success(){
        String s=pipeFull2.removeLast();
        assertEquals("F",s);
        assertEquals(1,pipeFull2.length());
    }

    @Test(expected = IllegalStateException.class)
    public void removeLastFull2Empty() {
        pipeFull2.removeLast();
        pipeFull2.removeLast();
        pipeFull2.removeLast();
    }

    @Test
    public void testABCIterator(){
        StringBuilder result= new StringBuilder();
        for (String s:pipeABC6){
            result.append(s);
        }
        assertEquals("ABC", result.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty3ListPipeIllegalCapacity() {
        pipeEmpty3 = new ListPipe<>(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6ListPipeIllegalCapacity() {
        pipeABC6 = new ListPipe<>(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFull2ListPipeIllegalCapacity() {
        pipeFull2 = new ListPipe<>(0);
    }

    @Test
    public void testEmptyListPipeIsEmpty() {
        assertTrue(pipeEmpty3.isEmpty());
    }

    @Test
    public void testABC6ListPipeIsEmpty() {
        assertFalse(pipeABC6.isEmpty());
        pipeABC6.clear();
        assertTrue(pipeABC6.isEmpty());
    }

    @Test
    public void testFull2ListPipeIsEmpty() {
        assertFalse(pipeFull2.isEmpty());
        pipeFull2.clear();
        assertTrue(pipeFull2.isEmpty());
    }

    @Test
    public void testEmpty3ListPipeIsFull() {
        assertFalse(pipeEmpty3.isFull());
        pipeEmpty3.append("1");
        pipeEmpty3.append("2");
        pipeEmpty3.append("3");
        assertTrue(pipeEmpty3.isFull());
    }

    @Test
    public void testABC6ListPipeIsFull() {
        assertFalse(pipeABC6.isFull());
        pipeABC6.append("E");
        pipeABC6.append("F");
        pipeABC6.append("G");
        assertTrue(pipeABC6.isFull());
    }

    @Test
    public void testFull2ListPipeIsFull() {
        assertTrue(pipeFull2.isFull());
        pipeFull2.removeFirst();
        assertFalse(pipeFull2.isFull());
    }

    @Test
    public void testEmpty3ListPipeAppendAllSuccessAddIn() {
        pipeEmpty3.appendAll(pipeFull2);
        assertEquals("[E, F]:3",pipeEmpty3.toString());
        assertEquals(2, pipeEmpty3.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3ListPipeAppendAllLengthOutOfCapacity() {
        pipeEmpty3.append("X");
        pipeEmpty3.appendAll(pipeABC6);
    }

    @Test
    public void testABC6ListPipeAppendAllSuccessAddIn() {
        pipeABC6.appendAll(pipeFull2);
        assertEquals("[A, B, C, E, F]:6",pipeABC6.toString());
        assertEquals(5, pipeABC6.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6ListPipeAppendAllLengthOutOfCapacity() {
        pipeABC6.append("X");
        pipeABC6.append("Y");
        pipeABC6.appendAll(pipeFull2);
    }

    @Test
    public void testFull2ListPipeAppendAllSuccessAddIn() {
        pipeFull2.appendAll(pipeEmpty3);
        assertEquals("[E, F]:2",pipeFull2.toString());
        assertEquals(2, pipeFull2.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6ListPipeAppendAllNullPipe() {
        pipeABC6.appendAll(null);
    }

    @Test
    public void testEmpty3ListPipeCopy() {
        Pipe<String> newPipe = pipeEmpty3.copy();
        assertEquals(0, newPipe.length());
        assertEquals(3, newPipe.capacity());
        assertEquals(pipeEmpty3, newPipe);
    }

    @Test
    public void testABC6ListPipeCopy() {
        Pipe<String> newPipe = pipeABC6.copy();
        assertEquals(3, newPipe.length());
        assertEquals(6, newPipe.capacity());
        assertEquals(pipeABC6, newPipe);
        String s1 = pipeABC6.removeFirst();
        String s2 = newPipe.removeFirst();
        assertEquals(s1, s2);
    }

    @Test
    public void testFull2ListPipeCopy() {
        Pipe<String> newPipe = pipeFull2.copy();
        assertEquals(2, newPipe.length());
        assertEquals(2, newPipe.capacity());
        assertEquals(pipeFull2, newPipe);
    }

    @Test
    public void testEmpty3NewInstance() {
        Pipe<String> newPipe = pipeEmpty3.newInstance();
        assertEquals(0, newPipe.length());
        assertEquals(3, newPipe.capacity());
    }

    @Test
    public void testABC6NewInstance() {
        Pipe<String> newPipe = pipeABC6.newInstance();
        assertEquals(0, newPipe.length());
        assertEquals(6, newPipe.capacity());
    }

    @Test
    public void testFull2NewInstance() {
        Pipe<String> newPipe = pipeFull2.newInstance();
        assertEquals(0, newPipe.length());
        assertEquals(2, newPipe.capacity());
    }

    @Test
    public void testHashCodeEmpty3AndDifferentEmpty3(){
        Pipe<String> pipeEmpty3New = new ListPipe<>(3);
        assertEquals(pipeEmpty3.hashCode(), pipeEmpty3New.hashCode());
    }

    @Test
    public void testHashCodeEmpty3AndEmpty4(){
        Pipe<String> pipeEmpty4 = new ListPipe<>(4);
        assertNotEquals(pipeEmpty3.hashCode(), pipeEmpty4.hashCode());
    }

    @Test
    public void testHashCodeEmpty3AndA3(){
        Pipe<String> pipeA3 = new ListPipe<>(3);
        pipeA3.append("A");
        assertNotEquals(pipeEmpty3.hashCode(), pipeA3.hashCode());
    }

    @Test
    public void testHashCodeABC6AndDifferentABC6(){
        Pipe<String> pipeABC6New = new ListPipe<>(6);
        pipeABC6New.append("A");
        pipeABC6New.append("B");
        pipeABC6New.append("C");
        assertEquals(pipeABC6.hashCode(), pipeABC6New.hashCode());
    }

    @Test
    public void testHashCodeABC6AndABC4(){
        Pipe<String> pipeABC4 = new ListPipe<>(4);
        assertNotEquals(pipeABC6.hashCode(), pipeABC4.hashCode());
    }

    @Test
    public void testHashCodeABC6AndAB6(){
        Pipe<String> pipeAB6= new ListPipe<>(6);
        pipeAB6.append("A");
        pipeAB6.append("B");
        assertNotEquals(pipeABC6.hashCode(), pipeAB6.hashCode());
    }

    @Test
    public void testHashCodeFull2AndDifferentFull2(){
        Pipe<String> pipeFull2New = new ListPipe<>(2);
        pipeFull2New.append("E");
        pipeFull2New.append("F");
        assertEquals(pipeFull2.hashCode(), pipeFull2New.hashCode());
    }

    @Test
    public void testHashCodeFull2AndFull4(){
        Pipe<String> pipeFull4 = new ListPipe<>(4);
        assertNotEquals(pipeFull2.hashCode(), pipeFull4.hashCode());
    }

    @Test
    public void testHashCodeFull2AndE2(){
        Pipe<String> pipeFullE2 = new ListPipe<>(2);
        pipeFullE2.append("E");
        assertNotEquals(pipeFull2.hashCode(), pipeFullE2.hashCode());
    }

    @Test
    public void testDifferentContentABC6(){
        Pipe<String> pipeABD6 = new ListPipe<>(6);
        pipeABD6.append("A");
        pipeABD6.append("B");
        pipeABD6.append("D");
        assertFalse(pipeABC6.equals(pipeABD6));
    }

    @Test
    public void testEmpty3EqualsNull(){
        Pipe<String> newPipe = null;
        assertNotEquals(pipeEmpty3, newPipe);
    }

    @Test
    public void testEmpty3EqualsSelf(){
        assertTrue(pipeEmpty3.equals(pipeEmpty3));
    }

    @Test
    public void testEmpty3EqualsNonPipe(){
        assertFalse(pipeEmpty3.equals("[]:3"));
    }

    @Test
    public void testEmpty3EqualsDifferentEmpty3(){
        Pipe<String> pipeEmpty3New = new ListPipe<>(3);
        assertTrue(pipeEmpty3.equals(pipeEmpty3New));
    }

    @Test
    public void testEmpty3EqualsEmpty4(){
        Pipe<String> pipeEmpty4 = new ListPipe<>(4);
        assertFalse(pipeEmpty3.equals(pipeEmpty4));
    }

    @Test
    public void testEmpty3EqualsA3(){
        Pipe<String> pipeA3 = new ListPipe<>(3);
        pipeA3.append("A");
        assertFalse(pipeEmpty3.equals(pipeA3));
    }

    @Test
    public void testABC6EqualsNull(){
        assertFalse(pipeABC6.equals(null));
    }

    @Test
    public void testABC6EqualsSelf(){
        assertTrue(pipeABC6.equals(pipeABC6));
    }

    @Test
    public void testABC6EqualsNonPipe(){
        assertFalse(pipeABC6.equals("[A, B, C]:6"));
    }

    @Test
    public void testABC6EqualsDifferentABC6(){
        Pipe<String> pipeABC6New = new ListPipe<>(6);
        pipeABC6New.append("A");
        pipeABC6New.append("B");
        pipeABC6New.append("C");
        assertTrue(pipeABC6.equals(pipeABC6New));
    }

    @Test
    public void testABC6EqualsABC4(){
        Pipe<String> pipeABC4 = new ListPipe<>(4);
        assertFalse(pipeABC6.equals(pipeABC4));
    }

    @Test
    public void testABC6EqualsAB6(){
        Pipe<String> pipeAB6 = new ListPipe<>(6);
        pipeAB6.append("A");
        pipeAB6.append("B");
        assertFalse(pipeABC6.equals(pipeAB6));
    }

    @Test
    public void testFull2EqualsNull(){
        assertFalse(pipeFull2.equals(null));
    }

    @Test
    public void testFull2EqualsSelf(){
        assertTrue(pipeFull2.equals(pipeFull2));
    }

    @Test
    public void testFull2EqualsNonPipe(){
        assertFalse(pipeFull2.equals("[E, F]:2"));
    }

    @Test
    public void testFull2EqualsDifferentFull2(){
        Pipe<String> pipeFull2New = new ListPipe<>(2);
        pipeFull2New.append("E");
        pipeFull2New.append("F");
        assertTrue(pipeFull2.equals(pipeFull2New));
    }

    @Test
    public void testFull2EqualsFull4(){
        Pipe<String> pipeFull4 = new ListPipe<>(4);
        assertFalse(pipeFull2.equals(pipeFull4));
    }

    @Test
    public void testFull2EqualsE2(){
        Pipe<String> pipeFullE2 = new ListPipe<>(2);
        pipeFullE2.append("E");
        assertFalse(pipeFull2.equals(pipeFullE2));
    }

    @Test
    public void testEmpty3ToString(){
        assertEquals("[]:3",pipeEmpty3.toString());
    }

    @Test
    public void testABC6ToString(){
        assertEquals("[A, B, C]:6",pipeABC6.toString());
    }

    @Test
    public void testFull2ToString(){
        assertEquals("[E, F]:2",pipeFull2.toString());
    }

    @Test
    public void testFirstABC6(){
        assertEquals("A",pipeABC6.first());
    }

    @Test
    public void testEmptyFirst(){
        assertNull(pipeEmpty3.first());
    }

    @Test
    public void testLastABC6(){
        assertEquals("C",pipeABC6.last());
    }

    @Test
    public void testEmptyLast(){
        assertNull(pipeEmpty3.last());
    }
}
