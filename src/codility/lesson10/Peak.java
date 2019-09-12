package codility.lesson10;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Peak {
    public static void main(String[] args) {
        int [] P =
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2} // 4
//            {1, 2, 1, 2, 1, 2, 1, 2} // 2
//            {8, 7, 9, 1} // 1
//            {1, 7, 7, 1} // 0
//            {1, 1, 1} // 0
//            {1, 3, 1} // 1
//            {1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2} // 3
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length < 3) return 0;

            List<Integer> peakIndices = new ArrayList<>();
            for (int i = 1; i < A.length - 1; i++) {
                if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                    peakIndices.add(i);
                    i++; // skip next (cannot be peak)
                }
            }
//            TestHelper.log(peakIndices);
            if (peakIndices.isEmpty()) return 0;

            int maxBlocks = 1;
            for (int i = peakIndices.size(); i >= 2; i--) {
                if (A.length % i != 0) {
                    continue;
                }

                final int blockLen = A.length / i;
                int left = 0;
                int right = blockLen - 1;
                int peakCheckIndex = 0;
                Set<Integer> matchingBlocks = new HashSet<>();

                while (right < A.length && peakCheckIndex < peakIndices.size()) {
                    int peakIndex = peakIndices.get(peakCheckIndex);
                    if (left <= peakIndex && peakIndex <= right) {
                        matchingBlocks.add(left);
                        peakCheckIndex++;
//                        TestHelper.log(matchingBlocks);
                        continue;
                    }

                    // move to the next
                    left = right + 1;
                    right = left + blockLen - 1;
                }
                if (matchingBlocks.size() == i) {
                    maxBlocks = i;
                    break;
                }
            }

            return maxBlocks;
        }
    }
}