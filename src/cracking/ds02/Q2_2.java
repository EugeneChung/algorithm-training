package cracking.ds02;

import helpers.TestHelper;

import java.util.*;

public class Q2_2 {
    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<>(Arrays.asList(1, 3, 1, 4, 5)); int k = 2;

        TestHelper.printSolution(
            new Q2_2.Solution().solution1(l, k)
        );
    }

    static class Solution {
        // when we know the length of list
        public int solution1(LinkedList<Integer> l, int k) {
            int count = l.size() - k + 1;
            Iterator<Integer> it = l.iterator();
            int i = 1;
            while (i < count) {
                i++;
                it.next();
            }
            return it.next();
        }
    }
}
