package cracking.ds01;

import helpers.TestHelper;

public class Q1_4 {
    public static void main(String[] args) {
        String s = "tact coa";

        TestHelper.printSolution(
            new Q1_4.Solution().solution(s)
        );
    }

    static class Solution {
        public boolean solution(String s) {
            if (s.length() == 0) return true;

            int actualLen = 0;
            int[] checkSet = new int[128];
            for (int i = 0; i < s.length(); i++) {
                if (Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i))) {
                    checkSet[(int) s.charAt(i)]++;
                    actualLen++;
                }
            }
            if (actualLen % 2 == 0) {
                for (int count : checkSet) {
                   if (count % 2 != 0) return false; 
                }
            } else {
                boolean onlyOneOddCharAllowed = true;
                for (int count : checkSet) {
                    if (count % 2 != 0) {
                        if (onlyOneOddCharAllowed) onlyOneOddCharAllowed = false;
                        else return false;
                    };
                }
            }
            return true;
        }
    }
}
