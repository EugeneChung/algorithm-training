package helpers;

public class ArrayPermutation {
    final int[] in;
    final int r;
    final StringBuilder out;
    final boolean[] used;
    int count;

    public ArrayPermutation(int[] in, int r) {
        this.in = in;
        this.r = r;
        out = new StringBuilder();
        used = new boolean[in.length];
    }

    public void permute() {
        permute(0);
    }

    private void permute(int selectedSoFar) {
        if (r == selectedSoFar) {
            System.out.println(out.toString());
            count++;
            return;
        }
        for (int i = 0; i < in.length; i++) {
            if (!used[i]) {
                int prevLen = out.length();
                out.append(in[i]);
                used[i] = true;
                permute(selectedSoFar + 1);
                used[i] = false;
                out.setLength(prevLen);
            }
        }
    }

    public static void main(String[] arg) {
        int[] a = {1, 2, 34};
        ArrayPermutation permutation = new ArrayPermutation(a, 3);
        permutation.permute();
        System.out.println(permutation.count);
    }
}
