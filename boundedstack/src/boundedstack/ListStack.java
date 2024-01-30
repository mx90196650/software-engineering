package boundedstack;

import java.util.LinkedList;
import java.util.List;

public class ListStack<E> implements Stack<E> {
    private final List<E> contents;
    private final int capacity;

    public ListStack(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("The bound for the stack cannot be 0 or negative!");
        }
        contents = new LinkedList<>();
        capacity = max;
    }

    @Override
    public void push(E element) {
        if (depth() == capacity) {
            throw new IllegalStateException("The stack is already full!");
        }
        contents.add(element);
    }

    @Override
    public E pop() {
        if (depth() == 0) {
            throw new IllegalStateException("The stack is already empty!");
        }
        return contents.remove(contents.size() - 1);
    }

    @Override
    public int depth() {
        return contents.size();
    }

    @Override
    public int capacity() {
        return capacity;
    }

}