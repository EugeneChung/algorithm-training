package codility;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxProfit {
    public static void main(String[] args) {
        int [] P =
//            {23171, 21011, 21123, 21366, 21013, 21367}
//            {-3, 1, 2, -2, 5, 6}
//            {2, 1, 1, 2, 3, 1}
//            {1, 1, }
            {1, 1, 1}
//            {3, 2, 1}
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private class Element implements Comparable<Element> {
            final int originalIndex;
            final int value;

            private Element(int originalIndex, int value) {
                this.originalIndex = originalIndex;
                this.value = value;
            }

            @Override
            public String toString() {
                return "Element{" +
                    "originalIndex=" + originalIndex +
                    ", value=" + value +
                    '}';
            }

            @Override
            public int compareTo(Element o) {
                return Integer.compare(value, o.value);
            }
        }
        public int solution(int[] A) {
            if (A.length < 2) return 0;

            List<Element> elements = new ArrayList<>();
            for (int i = 0; i < A.length; i++) {
                int val = A[i];
                elements.add(new Element(i, val));
            }
            Collections.sort(elements);
            //TestHelper.printArray(elements);

            int miminumOriginalIndex = 0;
            for (int i = elements.size() - 1; i >= 1; i--) {
                Element element = elements.get(i);

                if (element.originalIndex == miminumOriginalIndex) {
                    miminumOriginalIndex++;
                    continue;
                }

                for (int j = 0; j < i; j++) {
                    Element lowValueElement = elements.get(j);
                    if (element.originalIndex > lowValueElement.originalIndex) {
                        return element.value - lowValueElement.value;
                    }
                }
            }
            return 0;
        }
    }
}