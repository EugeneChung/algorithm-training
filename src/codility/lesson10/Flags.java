package codility.lesson10;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flags {
    public static void main(String[] args) {
        int [] P =
            {1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2} // 3
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private boolean check(int flags, List<Integer> peakIndices) {
            if (flags == 1) return true;

            int prev = peakIndices.get(0);
            int flagsRemained = flags - 1;

            for (int i = 1; i < peakIndices.size(); i++) {
                int peakIndex = peakIndices.get(i);
                if (peakIndex - prev >= flags) {
                    prev = peakIndex;
                    flagsRemained--;
                    if (flagsRemained == 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        public int solution(int[] A) {
            if (A.length < 3) return 0;

            List<Integer> peakIndices = new ArrayList<>();
            for (int i = 1; i < A.length - 1; i++) {
                if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                    peakIndices.add(i);
                    i++; // skip next (cannot be peak)
                }
            }
            if (peakIndices.isEmpty()) return 0;

            int low = 1;
            int high = A.length;
            int result = 1;

            while (low <= high) {
                int flags = (low + high) / 2;
                if (check(flags, peakIndices)) {
                    low = flags + 1;
                    result = flags;
                } else {
                    high = flags - 1;
                }
            }
            return result;
        }
    }
}