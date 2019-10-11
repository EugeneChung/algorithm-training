package cracking.ds01;

import helpers.TestHelper;

public class Q1_3 {
    public static void main(String[] args) {
        String s = "Mr John Smith";

        TestHelper.printSolution(
            new Q1_3.Solution().solution(s)
        );
    }

    static class Solution {
        public String solution(String s) {
            StringBuilder builder = new StringBuilder(s.length() * 3);
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    builder.append("%20");
                } else {
                    builder.append(c);
                }
            }
            return builder.toString();
        }
    }
}
