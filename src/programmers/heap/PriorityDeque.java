package programmers.heap;

import helpers.TestHelper;

import java.util.*;

public class PriorityDeque {
    public static void main(String[] args) {
        String[] operations = {"I 7", "I 5", "I -5", "D -1"}; //7,5
//        String[] operations = {"I 16", "D 1"}; //0,0
//        String[] operations = {"I 16", "I 1", "D 1"};//1,0

        TestHelper.printSolution(new Solution().solution(operations));
    }

    static class Solution {
        public int[] solution(String[] operations) {
            TreeSet<Integer> prioritySet = new TreeSet<>();
            Map<Integer, Integer> counterMap = new HashMap<>();
            for (String operation : operations) {
                char command = operation.charAt(0);
                if (prioritySet.isEmpty() && command == 'D') continue;

                int value = Integer.parseInt(operation.substring(2));
                switch (command) {
                    case 'D':
                        int valueRemoved;
                        if (value > 0) {
                            valueRemoved = prioritySet.descendingIterator().next();
                        } else {
                            valueRemoved = prioritySet.iterator().next();
                        }
                        int count = counterMap.get(valueRemoved);
                        count--;
                        if (count == 0) {
                            counterMap.remove(valueRemoved);
                            if (value > 0) {
                                prioritySet.pollLast();
                            } else {
                                prioritySet.pollFirst();
                            }
                        } else {
                            prioritySet.add(valueRemoved);
                            counterMap.put(valueRemoved, count);
                        }
                        break;
                    case 'I':
                        count = counterMap.getOrDefault(value, 0);
                        if (count == 0) {
                            prioritySet.add(value);
                        }
                        counterMap.put(value, ++count);
                        break;
                    default:
                        break;
                }
            }
            if (prioritySet.isEmpty()) return new int[]{0,0};
            Integer max = prioritySet.pollLast();
            if (max == null) {
                max = 0;
            }
            Integer min = prioritySet.pollFirst();
            if (min == null) {
                min = 0;
            }
            return new int[]{max, min};
        }
    }
}