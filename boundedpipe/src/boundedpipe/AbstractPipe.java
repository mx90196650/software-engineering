package boundedpipe;

import java.util.Iterator;

public abstract class AbstractPipe<E> implements Pipe<E> {

    private int capacity;

    public AbstractPipe(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The initial size for the list cannot be 0 or negative!");
        }
        this.capacity = capacity;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

    @Override
    public boolean isFull() {
        return length() == capacity();
    }

    @Override
    public void appendAll(Pipe<E> that) {
        if (that == null) {
            throw new IllegalArgumentException("the pipe to append cannot be null!");
        }
        if (that.length() + this.length() > this.capacity()) {
            throw new IllegalStateException("There is not enough capacity!");
        }
        if (that.length() == 0) return;
        E element = that.removeFirst();
        this.append(element);
        this.appendAll(that);
    }

    @Override
    public Pipe<E> copy() {
        Pipe<E> pipe = this.newInstance();
        for (E element: this) {
            pipe.append(element);
        }
        return pipe;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Pipe)) {
            return false;
        }
        Pipe<?> that = (Pipe) o;
        if (this.capacity() != that.capacity()) {
            return false;
        }
        if (this.length() != that.length()) {
            return false;
        }
        Iterator<E> thisIterator = this.iterator();
        Iterator<?> thatIterator = that.iterator();
        while (thisIterator.hasNext()) {
            if (!(thisIterator.next().equals(thatIterator.next()))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int result = 7;
        for (E e : this) {
            result = 31 * result + e.hashCode();
        }
        return 31 * result + capacity;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            if (iterator.hasNext()) stringBuilder.append(", ");
        }
        stringBuilder.append("]:");
        stringBuilder.append(capacity());
        return stringBuilder.toString();
    }
}
