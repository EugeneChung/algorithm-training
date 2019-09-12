package helpers;

public class Element implements Comparable<Element> {
    final int index;
    final int value;

    Element(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public static Element of(int index, int value) {
        return new Element(index, value);
    }

    @Override
    public String toString() {
        return "A[" + index + "]=" + value;
    }

    @Override
    public int compareTo(Element o) {
        return Integer.compare(this.value, o.value);
    }
}