package programmers.stackqueue;

import helpers.TestHelper;

import java.util.Stack;

public class 주식가격 {
    public static void main(String[] args) {
//        int[] prices = {1, 2, 3, 2, 3}; // 4, 3, 1, 1, 0
//        int[] prices = {3,2}; // 1,0
//        int[] prices = {2, 3}; // 1,0
//        int[] prices = {5, 4, 3, 2, 1}; // 1, 1, 1, 1, 0
        int[] prices = {2, 3, 2}; // 2,1,0

        TestHelper.printSolution(new Solution().solution(prices));
    }

    static class Solution {
        private class Price {
            final int index;
            final int value;

            private Price(int index, int value) {
                this.index = index;
                this.value = value;
            }
        }

        public int[] solution(int[] prices) {
            Stack<Price> priceStack = new Stack<>(); 
            int[] answer = new int[prices.length];

            priceStack.push(new Price(0, prices[0]));
            for (int i = 1; i < prices.length; i++) {
                while (!priceStack.isEmpty()) {
                    Price oldPrice = priceStack.peek();
                    if (oldPrice.value > prices[i]) {
                        priceStack.pop();
                        answer[oldPrice.index] = i - oldPrice.index;
                    } else {
                        break;
                    }
                }
                priceStack.push(new Price(i, prices[i]));
            }
            while (!priceStack.isEmpty()) {
                Price price = priceStack.pop();
                answer[price.index] = prices.length - 1 - price.index;
            }
            return answer;
        }
    }
}