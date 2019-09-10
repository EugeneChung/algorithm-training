package helpers;

public class Algorithms {
    public static void main(String[] s) {
        int[] actual = { 5, 1, 6, 2, 3, 4 };
        mergeSort(actual);
        TestHelper.printArray(actual);
    }

    public static void mergeSort(int[] a) {
        final int n = a.length;
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        System.arraycopy(a, 0, l, 0, mid);
        System.arraycopy(a, mid, r, 0, n - mid);

        mergeSort(l);
        mergeSort(r);

        merge(a, l, r);
    }

    private static void merge(int[] arr, int[] l, int[] r) {
        final int left = l.length;
        final int right = r.length;

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                arr[k++] = l[i++];
            }
            else {
                arr[k++] = r[j++];
            }
        }
        while (i < left) {
            arr[k++] = l[i++];
        }
        while (j < right) {
            arr[k++] = r[j++];
        }
    }
}
