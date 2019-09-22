package helpers;

public class StringCombination {
    final String in;
    private final int r;
    final StringBuilder out;

    public StringCombination(String in, int r) {
        this.in = in;
        this.r = r;
        out = new StringBuilder(in.length());
    }

    public void combine() {
        out.setLength(0); // clear
        doCombine(0);
    }

    private void doCombine(int start) {
        for (int i = start; i < in.length(); i++) {
            out.append(in.charAt(i));
            if (out.length() == r) {
                System.out.println(out.toString());
                out.setLength(out.length() - 1);
                continue;
            }
            doCombine(i + 1);
            out.setLength(out.length() - 1);
        }
    }

    public static void main(String[] args) {
        StringCombination combination = new StringCombination("abcd", 3);
        combination.combine();
    }
}