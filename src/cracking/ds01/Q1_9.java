package cracking.ds01;

import helpers.TestHelper;

public class Q1_9 {
    public static void main(String[] args) {
//        String s1 = "waterbottle"; String s2 = "erbottlewat";
        String s1 = "waterbottle"; String s2 = "rbottlewat";

        TestHelper.printSolution(
            new Q1_9.Solution().solution(s1, s2)
        );
    }

    static class Solution {
        public boolean solution(String s1, String s2) {
            if (s1.length() != s2.length()) return false;
            String s = s1 + s2;
            return s.contains(s2); // = isSubstring
        }
    }
}
