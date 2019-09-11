package codility.lesson14;

import helpers.TestHelper;

import java.util.*;

public class NailingPlanks {
    public static void main(String[] args) {
        int[] A =
//            {1, 4, 5, 8}
//            {1, 4, 5, 8}
            {1, 4, 5, 8}
            ;
        int[] B =
//            {4, 5, 9, 10}
//            {1, 4, 5, 8}
            {2, 4, 5, 8}
            ;
        int[] C =
//            {4, 6, 7, 10, 2}
//            {4, 6, 7, 10, 2}
            {8, 5, 4, 2}
            ;
        Object solution = new Solution().solution(A, B, C);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private class Plank implements Comparable<Plank> {
            final int id;
            final int left;
            final int right;
            boolean nailed;

            private Plank(int id, int left, int right) {
                this.id = id;
                this.left = left;
                this.right = right;
            }

            @Override
            public int compareTo(Plank o) {
                return Integer.compare(this.left, o.left); // sort by the leftmost end
            }
        }

        public int solution(int[] A, int[] B, int[] C) {
            List<Plank> planks = new LinkedList<>();
            Set<Integer> nailedPlanks = new HashSet<>();

            for (int i = 0; i < A.length; i++) {
                planks.add(new Plank(i, A[i], B[i]));
            }
            Collections.sort(planks);
            Arrays.sort(B);

            int nailUsedCount = 0;
            for (int nail : C) {
                boolean nailUsed = false;
                int loc = Collections.binarySearch(planks, new Plank(-1, nail, -1));

                if (loc < 0) {
                    if (loc == -1) continue;

                    loc = -(loc + 1);
                    if (loc == planks.size()) {
                        int locCheck = Arrays.binarySearch(B, nail);
                        if (locCheck < 0 && -(locCheck + 1) == B.length) {
                            continue;
                        }
                    }
                    loc--;
                } else {
                    nailUsed = true;
                }
                for (int j = loc; j >= 0; j--) {
                    Plank plank = planks.get(j);
                    if (plank.right >= nail) {
                        nailUsed = true;
                        if (!plank.nailed) {
                            plank.nailed = true;
                            nailedPlanks.add(plank.id);
                        }
                    }
                }

                if (nailUsed) {
                    nailUsedCount++;
                }
                if (nailedPlanks.size() == planks.size()) break;
            }
            if (nailedPlanks.size() == planks.size() && nailUsedCount > 0) {
                return nailUsedCount;
            }
            TestHelper.log(nailedPlanks);
            return -1;
        }
    }
}