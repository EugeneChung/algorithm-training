package cracking.ds01;

import helpers.TestHelper;

public class Q1_2 {
    public static void main(String[] args) {
//        String s1 = "abcde", s2 = "deabc"; // true
        String s1 = "abcde", s2 = "deaba"; // false

        TestHelper.printSolution(
            new Q1_2.Solution().solution(s1, s2)
        );
    }

    static class Solution {
        public boolean solution(String s1, String s2) {
            if (s1.length() != s2.length()) return false;
            if (s1.equals(s2)) return true;

            int[] checkSet = new int[128];//Assume that s1 and s2 have only ASCII characters
            for (int i = 0; i < s1.length(); i++) {
                char c = s1.charAt(i);
                checkSet[(int)c]++;
            }
            for (int i = 0; i < s2.length(); i++) {
                char c = s2.charAt(i);
                checkSet[(int)c]--;
                if (checkSet[(int)c] < 0) return false;
            }
            return true;
        }
    }
}
