package codility.lesson90;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RectangularArea {
    public static void main(String[] args) {
        int[] A = {1, 2, 5, 1, 1, 7, 2, 3, 5, 1, 7}; int X = 1; // 7
//        int[] A = {1_000_000_000, 1_000_000_000, 1_000_000_000, 1_000_000_000}; int X = Integer.MAX_VALUE; // 1
//        int[] A = {1, 1, 1, 2, 2, 1}; int X = 1; // 2
//        int[] A = {1, 1, 1, 2, 1}; int X = 1; // 1
//        int[] A = {1, 2, 5, 1, 1, 2, 3, 5, 1}; int X = 5; // 2
//        int[] A = {1, 2, 5, 1, 1, 2, 3, 5, 1}; int X = 4; // 2
        Object solution = new Solution().solution(A, X);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A, int X) {
            if (A.length < 4) return 0;

            Arrays.sort(A);
            List<Integer> lengths = new ArrayList<>();
            List<Integer> lengthOver4s = new ArrayList<>();
            int occur = 0;
            int prev = Integer.MAX_VALUE;
            for (int value : A) {
                if (prev != value) {
                    prev = value;
                    occur = 1;
                } else {
                    occur++;
                    if (occur == 2) {
                        lengths.add(prev);
                    } else if (occur == 4) {
                        lengthOver4s.add(prev);
                    }
                }
            }
//            TestHelper.log(lengthOver4s);
//            TestHelper.log(lengths);

            int count = 0;
            for (int i = 0; i < lengths.size(); i++) {
                int height = lengths.get(i);
                int targetWidth = X % height == 0 ? X / height : X / height + 1;

                int loc = Collections.binarySearch(lengths, targetWidth);
                if (loc < 0) {
                    loc = -(loc + 1);
                } else {
                    if (i == loc) loc++;
                }
//                TestHelper.log(i + ": height=" + height + ", targetWidth=" + targetWidth + ", loc=" + loc);

                count += lengths.size() - loc;
                if (i > loc) count--; // remove self counting
            }
            count = count / 2;
            if (count > 1_000_000_000) {
                return -1;
            }

            for (long len : lengthOver4s) {
                long area = len * len;
                if (area >= X) {
                    count++;
                    if (count > 1_000_000_000) {
                        return -1;
                    }
                }
            }

            return count;
        }
    }
}