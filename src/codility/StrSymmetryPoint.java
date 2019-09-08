package codility;

import helpers.TestHelper;

public class StrSymmetryPoint {
    public static void main(String[] args) {
        String S =
//            "racecar"
//            "raceca"
//            "e"
//            "ece"
            "eeeee"
//            "{()()()()()()()()[][][]}"
//            "{[()()]}"
//            "([)()]"
            ;
        Object solution = new Solution().solution(S);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(String S) {
            if (S.length() == 1) return 0;
            if (S.length() % 2 == 0) return -1;

            int candidate = S.length() >> 1;
            for (int i = 0, j = S.length() - 1; i < candidate; i++, j--) {
                if (S.charAt(i) != S.charAt(j)) return -1;
            }
            return candidate;
        }
    }
}