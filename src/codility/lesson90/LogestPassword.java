package codility.lesson90;

import helpers.TestHelper;

import java.util.Stack;
import java.util.StringTokenizer;

public class LogestPassword {
    public static void main(String[] args) {
        String S =
            //"test 5 a0A pass007 ?xy1"
//            "5"
//            "55"
//            "5e"
//            "e5e"
//            "x? e5e"
            "x? ^^"
            ;
        Object solution = new Solution().solution(S);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(String S) {
            int maxLength = Integer.MIN_VALUE;
            StringTokenizer tokenizer = new StringTokenizer(S, " ");

            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                int passwordLength = getPasswordLength(word);

                maxLength = Math.max(passwordLength, maxLength);
            }
            return maxLength;
        }

        private int getPasswordLength(String password) {
            int letterCount = 0;
            int digitCount = 0;
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (Character.isLetter(c)) {
                    letterCount++;
                } else if (Character.isDigit(c)) {
                    digitCount++;
                } else {
                    return -1;
                }
            }
            if (letterCount % 2 == 0 && digitCount % 2 == 1) {
                return password.length();
            }
            return -1;
        }
    }
}