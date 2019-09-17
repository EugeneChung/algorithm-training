package programmers.heap;

import helpers.TestHelper;

import java.util.PriorityQueue;

public class 라면공장 {
    public static void main(String[] args) {
//        int stock = 4; int[] dates = {4, 10, 15}, supplies = {20, 5, 10}; int k = 30; // 2
//        int stock = 2; int[] dates = {1, 2}, supplies = {20, 5}; int k = 2; // 0
        int stock = 2; int[] dates = {1, 2, 5}, supplies = {1, 4, 3}; int k = 7; // 2

        TestHelper.printSolution(new Solution().solution(stock, dates, supplies, k));
    }

    static class Solution {
        private class Supply implements Comparable<Supply> {
            final int date;
            final int supply;

            private Supply(int date, int supply) {
                this.date = date;
                this.supply = supply;
            }
            @Override
            public String toString() {
                return "Supply[" + date + "]=" + supply;
            }

            @Override
            public int compareTo(Supply o) {
                return Integer.compare(o.supply, this.supply); // to make PriorityQueue return the biggest first
            }
        }

        public int solution(int stock, int[] dates, int[] supplies, int k) {
            int answer = 0;
            PriorityQueue<Supply> priorityQueue = new PriorityQueue<>();
            int currentDatesIndex = 0;
            int days = 0;
            int remainingStock = stock;
            while (days < k) {
                if (currentDatesIndex < dates.length && dates[currentDatesIndex] == days) {
                    priorityQueue.add(new Supply(dates[currentDatesIndex], supplies[currentDatesIndex]));
                    currentDatesIndex++;
                }

                if (remainingStock == 0) {
                    Supply supply = priorityQueue.poll();
                    assert supply != null; 
                    //TestHelper.log(supply);
                    remainingStock += supply.supply;
                    answer++;
                }

                remainingStock--;
                days++;
            }
            return answer;
        }
    }
}