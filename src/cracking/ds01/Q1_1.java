package cracking.ds01;

import helpers.TestHelper;

import java.util.Arrays;

public class Q1_1 {
    public static void main(String[] args) {
//        char[] s = "abcad".toCharArray();
        char[] s = "abcde".toCharArray();

        TestHelper.printSolution(
            new Q1_1.Solution().solution(s)
        );
    }

    static class Solution {
        public boolean solution(char[] s) {
            Arrays.sort(s);
            for (int i = 0; i < s.length - 1; i++) {
                if (s[i] == s[i + 1]) return true;
            }
            return false;
        }
    }
}
