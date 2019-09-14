package codility.lesson90;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FloodDepth {
    public static void main(String[] args) {
        int[] A =
//            {1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2, 3}
            {1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2} // 2
//            {4, 3, 2, 1, 2, 4} // 3
//            {4, 3, 2, 1, 2} // 1
//            {4, 3, 2, 1, 2, 5} // 3
//            {5, 8, 3} //0 
//            {5, 8} // 0
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private class Element implements Comparable<Element> {
            final int index;
            final int value;

            Element(int index, int value) {
                this.index = index;
                this.value = value;
            }

            @Override
            public String toString() {
                return "A[" + index + "]=" + value;
            }

            @Override
            public int compareTo(Element o) {
                return Integer.compare(this.value, o.value);
            }
        }

        private boolean check(List<Element> elements, int loc, int index) {
            for (int i = loc; i < elements.size(); i++) {
                Element element = elements.get(i);
                if (element.index > index) {
                    return true;
                }
            }
            return false;
        }

        public int solution(int[] A) {
            if (A.length < 3) return 0;
            List<Element> elements = new ArrayList<>(A.length);
            for (int i = 0; i < A.length; i++) {
                elements.add(new Element(i, A[i]));
            }
            Collections.sort(elements);

            int depth = 0;
            int maxDepth = 0;
            for (int i = 1; i < A.length - 1; i++) {
                int localDepth = A[i - 1] - A[i];
                if (localDepth > 0) {
                    //이 경우 depth에 더할지 말지 확정 필요
                    if (depth > 0) {
                        depth += localDepth;
                    } else {
                        int loc = Collections.binarySearch(elements, new Element(-1, A[i]));
                        if (loc >= 0) {
                            if (check(elements, loc, i)) {
                                depth += localDepth;
                            }
                        } else {
                            if (check(elements, loc, i)) {
                                depth += localDepth;
                            }
                        }
                    }
                } else if (localDepth < 0) {
                    depth += localDepth;
                    if (depth < 0) depth = 0;
                }

                TestHelper.log(depth);

                maxDepth = Math.max(depth, maxDepth);
            }
            return maxDepth;
        }
    }
}