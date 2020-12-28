
import java.util.Arrays;

public class Stack {
    private char[] items = new char[10];
    private int count;

    public void push(char item) {
        if (count == items.length)
            for (int i = 0; i < 8; i++) {
                items[i] = items[i + 1];
                items[count - 1] = item;
                return;
            }
        items[count++] = item;
    }

    public char pop() {
        if (count == 0)
            return ' ';

        return items[--count];
    }

    public int peek() {
        if (count == 0)
            throw new IllegalStateException();

        return items[count - 1];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        char[] content = Arrays.copyOfRange(items, 0, count);
        return Arrays.toString(content);
    }
}
