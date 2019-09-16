package programmers.stackqueue;

import helpers.TestHelper;

import java.util.*;

public class Printer {
    public static void main(String[] args) {
//        int[] priorities = {2, 1, 3, 2}; int location = 2; // 1
//        int[] priorities = {2, 2, 3, 1}; int location = 1; // 3
        int[] priorities = {2, 2, 3, 1}; int location = 0; // 2
//        int[] priorities = {1, 1, 9, 1, 1, 1}; int location = 0; // 5

        TestHelper.printSolution(new Solution().solution(priorities, location));
    }

    static class Solution {

        class Element implements Comparable<Element> {
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
                //return Integer.compare(this.value, o.value);
                int compare = Integer.compare(o.value, this.value);
                if (compare == 0) {
                    return Integer.compare(this.index, o.index);
                } else {
                    return compare;
                }
            }
        }

        public int solution(int[] priorities, int location) {
            Queue<Element> queue = new ArrayDeque<>(priorities.length);
            for (int i = 0; i < priorities.length; i++) {
                queue.add(new Element(i, priorities[i]));
            }
            int answer = 0;
            while (!queue.isEmpty()) {
                Element element = queue.poll();

                boolean skip = false;
                for (Element other : queue) {
                    if (other.value > element.value) {
                        queue.add(element);
                        skip = true;
                        break;
                    }
                }
                if (!skip && element.index == location) {
                    answer = priorities.length - queue.size();
                    break;
                }
            }
            return answer;
        }
    }
}