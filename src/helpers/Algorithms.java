package helpers;

public class Algorithms {
    public static void main(String[] s) {
        int[] actual = { 5, 1, 6, 2, 3, 4 };
        mergeSort(actual);
        TestHelper.printObject(actual);
        TestHelper.log(binaryGCD(200, 120));
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

    public static int binaryGCD(int p, int q) {
        //https://introcs.cs.princeton.edu/java/23recursion/BinaryGCD.java.html
        if (q == 0) return p;
        if (p == 0) return q;

        int pmod2 = p & 1;
        int qmod2 = q & 1;

        // p and q even
        if (pmod2 == 0 && qmod2 == 0) return binaryGCD(p >> 1, q >> 1) << 1;

            // p is even, q is odd
        else if (pmod2 == 0) return binaryGCD(p >> 1, q);

            // p is odd, q is even
        else if (qmod2 == 0) return binaryGCD(p, q >> 1);

            // p and q odd, p >= q
        else if (p >= q) return binaryGCD((p - q) >> 1, q);

            // p and q odd, p < q
        else return binaryGCD(p, (q - p) >> 1);
    }

    public static boolean[] buildPrimeCheckSet(int n) {
        // https://introcs.cs.princeton.edu/java/14array/PrimeSieve.java.html
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor * factor <= n; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ...,  n/factor
            if (isPrime[factor]) {
                for (int j = factor; factor * j <= n; j++) {
                    isPrime[factor * j] = false;
                }
            }
        }

        return isPrime;
    }

    public static int maxInArray(int... array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static class Element implements Comparable<Element> {
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

}
