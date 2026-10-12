package lab4;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomIterator implements Iterator<String> {

    private final List<String> lines;
    private int index;

    public CustomIterator(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public boolean hasNext() {
        return index < lines.size();
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return lines.get(index++);
    }
}