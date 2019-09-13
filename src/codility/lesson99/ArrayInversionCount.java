package codility.lesson99;

import helpers.TestHelper;

public class ArrayInversionCount {
    public static void main(String[] args) {
        int[] A =
            {-1, 6, 3, 4, 7, 4}
//            {3, 3}
//            {1, 2}
//            {2, 1}
//            {5, 4, 3, 2, 1, 0}
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private int count;

        private class OverflowException extends RuntimeException {
        }

        private void mergeSort(int[] a) {
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

        private void merge(int[] arr, int[] l, int[] r) {
            final int left = l.length;
            final int right = r.length;
            int i = 0, j = 0, k = 0;

            while (i < left && j < right) {
                if (l[i] <= r[j]) {
                    arr[k++] = l[i++];
                } else {
                    count += (left + j) - k; // 오른쪽(r의 j 위치)에서 왼쪽(arr(k) 위치)으로 옮기므로, 그 사이에 존재하는 값들은 모두 inversion 대상이다.
                                             // left+j에 k를 빼주면 그 값들의 수가 나오게 된다. 
                    if (count > 1_000_000_000) throw new OverflowException();
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

        public int solution(int[] A) {
            if (A.length < 2) return 0;

            try {
                mergeSort(A);
            } catch (OverflowException e) {
                return -1;
            }

            return count;
        }
    }
}