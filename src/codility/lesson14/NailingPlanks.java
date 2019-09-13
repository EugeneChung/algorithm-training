package codility.lesson14;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NailingPlanks {
    public static void main(String[] args) {
        int[] A =
            {1, 4, 5, 8}
//            {1, 4, 5, 8}
//            {1, 4, 5, 8}
            ;
        int[] B =
            {4, 5, 9, 10}
//            {1, 4, 5, 8}
//            {2, 4, 5, 8}
            ;
        int[] C =
            {4, 6, 7, 10, 2} // 4
//            {4, 6, 7, 10, 2}
//            {8, 5, 4, 2}
            ;
        Object solution = new Solution().solution(A, B, C);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private class Nail implements Comparable<Nail> {
            final int index;
            final int position;

            private Nail(int index, int position) {
                this.index = index;
                this.position = position;
            }

            @Override
            public int compareTo(Nail o) {
                return Integer.compare(this.position, o.position); // sort by the leftmost end
            }
        }

        public int solution(int[] A, int[] B, int[] C) {
            List<Nail> nails = new ArrayList<>(C.length);
            for (int i = 0; i < C.length; i++) {
                nails.add(new Nail(i, C[i]));
            }
            Collections.sort(nails);

            boolean[] nailed = new boolean[C.length];
            for (int i = 0; i < A.length; i++) {
                int locStart = Collections.binarySearch(nails, new Nail(-1, A[i]));
                if (locStart < 0) {
                    locStart = -(locStart + 1);
                }
                if (locStart == C.length) return -1;

                int locEnd = Collections.binarySearch(nails, new Nail(-1, B[i]));
                if (locEnd == -1) return -1;
                if (locEnd < 0) {
                    locEnd = -(locEnd + 1);
                    locEnd--;
                }

                for (int j = locStart; j <= locEnd; j++) {
                    Nail nail = nails.get(j);
                    nailed[nail.index] = true;
                }

                TestHelper.log("locStart=" + locStart + ", locEnd=" + locEnd);
            }
            TestHelper.printObject(nailed);

            int nailedCount = 0;
            for (int i = 0; i < nailed.length; i++) {
                if (i != nailedCount) return -1;
                if (nailed[i]) {
                    nailedCount++;
                }
            }
            if (nailedCount == 0) return -1;
            return nailedCount;
        }
    }
}