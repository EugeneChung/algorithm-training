package helpers;

class Element implements Comparable<Element> {
    final int index;
    final int value;

    Element(int index, int value) {
        this.index = index;
        this.value = value;
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