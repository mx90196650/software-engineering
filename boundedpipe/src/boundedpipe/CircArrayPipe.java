package boundedpipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircArrayPipe<E> extends AbstractPipe<E> {
    private final E[] contents;
    private int first;
    private int last;

    @SuppressWarnings("unchecked")
    public CircArrayPipe(int capacity) {
        super(capacity);
        contents = (E[]) new Object[capacity];
        first = -1;
        last = -1;
    }

    @Override
    public void prepend(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            throw new IllegalStateException();
        }
        if (isEmpty()) {
            first = 0;
            last = 0;
        } else {
            first = (first - 1 + capacity()) % capacity();
        }
        contents[first] = element;
    }

    @Override
    public void append(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            throw new IllegalStateException();
        }
        if (isEmpty()) {
            first = 0;
            last = 0;
        } else {
            last = (last + 1) % capacity();
        }
        contents[last] = element;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        E returnElement  = contents[first];
        if (this.length() == 1){
            first = -1;
            last = -1;
        } else {
            first = (first + 1) % capacity();
        }
        return returnElement;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        E returnElement = contents[last];
        if (this.length() == 1){
            first = -1;
            last = -1;
        } else {
            last = (last - 1 + capacity()) % capacity();
        }
        return returnElement;
    }

    @Override
    public int length() {
        if (first == -1) {
            return 0;
        }
        return (last - first + capacity()) % capacity() + 1;
    }

    @Override
    public Pipe<E> newInstance() {
        return new CircArrayPipe<>(capacity());
    }

    @Override
    public void clear() {
        first = -1;
        last = -1;
    }

    @Override
    public E first() {
        return isEmpty() ? null : contents[first];
    }

    @Override
    public E last() {
        return isEmpty() ? null : contents[last];
    }

    @Override
    public Iterator<E> iterator() {
        return new PipeIterator();
    }

    private class PipeIterator implements Iterator<E> {
        int index;

        public PipeIterator() {
            index = first;
        }

        @Override
        public boolean hasNext() {
            return index != -1;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E returnElement = contents[index];
            if (index == last) {
                index = -1;
            } else {
                index = (index + 1) % capacity();
            }
            return returnElement;
        }
    }
}
