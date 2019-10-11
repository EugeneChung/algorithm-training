package cracking.ds01;

import helpers.TestHelper;

public class Q1_5 {
    public static void main(String[] args) {
//        String s1 = "pale", s2 = "ple"; // true
//        String s1 = "pales", s2 = "pale"; // true
//        String s1 = "pale", s2 = "bale"; // true
//        String s1 = "pale", s2 = "bake"; // false
        String s1 = "pal", s2 = "bake"; // false

        TestHelper.printSolution(
            new Q1_5.Solution().solution(s1, s2)
        );
    }

    static class Solution {
        public boolean solution(String s1, String s2) {
            if (s1.length() == s2.length()) {
                boolean onlyOneDifferenceAllowed = true;
                for (int i = 0; i < s1.length(); i++) {
                    if (s1.charAt(i) != s2.charAt(i)) {
                        if (onlyOneDifferenceAllowed) onlyOneDifferenceAllowed = false;
                        else return false;
                    }
                }
            } else if (Math.abs(s1.length() - s2.length()) == 1) {
                boolean onlyOneDifferenceAllowed = true;
                for (int i = 0, j = 0; i < s1.length() && j < s2.length();) {
                    if (s1.charAt(i) != s2.charAt(j)) {
                        if (onlyOneDifferenceAllowed) onlyOneDifferenceAllowed = false;
                        else return false;
                        if (s1.length() < s2.length()) {
                            j++;
                        } else {
                            i++;
                        }
                    } else {
                        i++; j++;
                    }
                }
            } else {
                return false;
            }
            return true;
        }
    }
}
