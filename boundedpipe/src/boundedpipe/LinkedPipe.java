package boundedpipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPipe<E> extends AbstractPipe<E>  {

    private Node first;
    private Node last;
    private int length;

    public LinkedPipe(int capacity) {
        super(capacity);
        first = null;
        last = null;
        length = 0;
    }

    @Override
    public void prepend(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            throw new IllegalStateException();
        }
        Node newNode = new Node(element);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        length++;
    }

    @Override
    public void append(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            throw new IllegalStateException();
        }
        Node newNode = new Node(element);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        length++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        E returnElement = first.contents;
        if (length == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        length--;
        return returnElement;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        E returnElement = last.contents;
        if (length == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        length--;
        return returnElement;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public Pipe<E> newInstance() {
        return new LinkedPipe<>(capacity());
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        length = 0;
    }

    @Override
    public E first() {
        return isEmpty() ? null : first.contents;
    }

    @Override
    public E last() {
        return isEmpty() ? null : last.contents;
    }

    @Override
    public Iterator<E> iterator() {
        return new PipeIterator();
    }

    private class Node {
        E contents;
        Node prev;
        Node next;

        public Node(E contents) {
            this.contents = contents;
        }
    }

    private class PipeIterator implements Iterator<E> {

        Node currentNode;

        public PipeIterator() {
            currentNode = first;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E result = currentNode.contents;
            currentNode = currentNode.next;
            return result;
        }
    }
}

