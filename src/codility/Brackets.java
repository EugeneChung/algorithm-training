package codility;

import helpers.TestHelper;

import java.util.Arrays;
import java.util.Stack;

public class Brackets {
    public static void main(String[] args) {
        //"(", "{", "[", "]", "}" and/or ")"
        String S =
//            ""
//            "("
//            "()"
//            "{()()()()()()()()[][][]}"
//            "{[()()]}"
//            "([)()]"
            "((()"
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
                if (isOpenBracket(bracket)) {
                    brackets.push(bracket);
                } else {
                    if (brackets.isEmpty()) return 0;

                    Character openBracketFromStack = brackets.pop();
                    if (!isMatchingBracket(openBracketFromStack, bracket)) {
                        return 0;
                    }
                }
            }
            if (!brackets.isEmpty()) return 0;
            return 1;
        }

        private boolean isOpenBracket(Character bracket) {
            return bracket == '(' || bracket == '{' || bracket == '[';
        }

        private boolean isMatchingBracket(Character open, Character close) {
            if (open == '(') return close == ')';
            else if (open == '{') return close == '}';
            else return close == ']';
        }
    }
}