package codility.lesson7;

import helpers.TestHelper;

import java.util.Stack;

public class Nesting {
    public static void main(String[] args) {
        //"(", "{", "[", "]", "}" and/or ")"
        String S =
//            ""
//            "("
//            "()"
            "((()"
//            "(()(())())"
//            "())"
            ;
        Object solution = new Solution().solution(S);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(String S) {
            if (S.isEmpty()) return 1;
            if (S.length() % 2 == 1) return 0;

            Stack<Character> brackets = new Stack<>();
            brackets.push(S.charAt(0));

            for (int i = 1; i < S.length(); i++) {
                char bracket = S.charAt(i);
                if (bracket == '(') {
                    brackets.push(bracket);
                } else {
                    if (brackets.isEmpty()) return 0;
                    brackets.pop();
                }
            }
            if (!brackets.isEmpty()) return 0;
            return 1;
        }
    }
}