package boundedpipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListPipe<E> extends AbstractPipe<E> {

    private List<E> list;

    public ListPipe(int capacity) {
        super(capacity);
        list = new ArrayList<>();
    }

    @Override
    public void prepend(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element to prepend cannot be null!");
        }
        if (isFull()) {
            throw new IllegalStateException("Full Capacity!");
        }
        list.add(0, element);
    }

    @Override
    public void append(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element to append cannot be null!");
        }
        if (isFull()) {
            throw new IllegalStateException("Full Capacity!");
        }
        list.add(element);
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Full Capacity!");
        }
        return list.remove(0);
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Full Capacity!");
        }
        return list.remove(length() - 1);
    }

    @Override
    public int length() {
        return list.size();
    }

    @Override
    public Pipe<E> newInstance() {
        return new ListPipe<>(capacity());
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    public E first() {
        return isEmpty() ? null : list.get(0);
    }

    public E last() {
        return isEmpty() ? null : list.get(length() - 1);
    }
}
