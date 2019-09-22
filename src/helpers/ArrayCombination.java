package helpers;

public class ArrayCombination {
    final int[] in;
    private final int r;
    final StringBuilder out;

    public ArrayCombination(int[] in, int r) {
        this.in = in;
        this.r = r;
        out = new StringBuilder();
    }

    public void combine() {
        out.setLength(0); // clear
        doCombine(0, 0);
    }

    private void doCombine(int start, int selectedSoFar) {
        for (int i = start; i < in.length; i++) {
            int prevLen = out.length();
            out.append(in[i]);
            if (selectedSoFar + 1 == r) {
                System.out.println(out.toString());
                out.setLength(prevLen);
                continue;
            }
            doCombine(i + 1, selectedSoFar + 1);
            out.setLength(prevLen);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 34};
        ArrayCombination combination = new ArrayCombination(a, 2);
        combination.combine();
    }
}