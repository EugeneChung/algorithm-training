package cracking.ds01;

import helpers.TestHelper;

public class Q1_6 {
    public static void main(String[] args) {
//        String s = "aabccccaaa";
        String s = "abcde";

        TestHelper.printSolution(
            new Q1_6.Solution().solution(s)
        );
    }

    static class Solution {
        public String solution(String s) {
            if (s.length() < 2) return s;

            StringBuilder builder = new StringBuilder(s.length() + 24);
            char current = s.charAt(0);
            int currentOccur = 1;
            for (int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == current) currentOccur++;
                else {
                    builder.append(current).append(currentOccur);
                    current = c;
                    currentOccur = 1;
                }
                if (builder.length() > s.length()) {
                    return s;
                }
            }
            builder.append(current).append(currentOccur);
            if (builder.length() > s.length()) {
                return s;
            }
            return builder.toString();
        }
    }
}
