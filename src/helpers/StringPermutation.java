package helpers;

public class StringPermutation {
    final String in;
    final int r;
    final StringBuilder out;
    final boolean[] used;
    int count;

    public StringPermutation(String in, int r) {
        this.in = in;
        this.r = r;
        out = new StringBuilder(in.length());
        used = new boolean[in.length()];
    }

    public void permute() {
        if (r == out.length()) {
            System.out.println(out.toString());
            count++;
            return;
        }
        for (int i = 0; i < in.length(); i++) {
            if (!used[i]) {
                out.append(in.charAt(i));
                used[i] = true;
                permute();
                used[i] = false;
                out.setLength(out.length() - 1);
            }
        }
    }

    public static void main(String[] arg) {
        StringPermutation permutation = new StringPermutation("abcd", 2);
        permutation.permute();
        System.out.println(permutation.count);
    }
}
