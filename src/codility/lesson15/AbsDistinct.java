package codility.lesson15;

import helpers.TestHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AbsDistinct {
    public static void main(String[] args) {
        int[] A =
            {10, 2, 5, 1, 8, 12, 11, 15} // 20
//            {10, 2, 5, 1, 8, 12} // 4
//            {3, 2, 1} // 0
//            {3, 2, 7} // 0
//            {3, 2, 3} // 1
//            {} // 0
//            {1} // 0
//            {1, 2} // 0
//            {1, 2} // 0
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length == 1) return 1;

            Set<Integer> set = new HashSet<>();
            for (int value : A) {
                set.add(Math.abs(value));
            }
            return set.size();
        }
    }
}