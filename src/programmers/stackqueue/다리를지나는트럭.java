package programmers.stackqueue;

import helpers.TestHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class 다리를지나는트럭 {
    public static void main(String[] args) {
//        int bridge_length = 2, weight = 10; int[] truck_weights = {7,4,5,6}; //8
//        int bridge_length = 100, weight = 100; int[] truck_weights = {10}; //101
//        int bridge_length = 100, weight = 100; int[] truck_weights = {10,10,10,10,10,10,10,10,10,10}; //110
        int bridge_length = 1, weight = 1; int[] truck_weights = {1}; //2
//        int bridge_length = 1, weight = 1; int[] truck_weights = {1, 1}; //3

        TestHelper.printSolution(new Solution().solution(bridge_length, weight, truck_weights));
    }

    static class Solution {
        private class Truck {
            final int weight;
            int remainingTime;

            Truck(int weight, int remainingTime) {
                this.weight = weight;
                this.remainingTime = remainingTime;
            }
        }

        public int solution(int bridge_length, int bridge_weight, int[] truck_weights) {
            int waitingTruck = 0;
            int currentTotalWeight = 0;
            int time = 0;
            Map<Integer, Truck> passingTruckMap = new HashMap<>();
            while (waitingTruck < truck_weights.length) {
                currentTotalWeight = updatePassingTrucks(currentTotalWeight, passingTruckMap);

                int nextWeight = currentTotalWeight + truck_weights[waitingTruck];
                if (nextWeight <= bridge_weight) {
                    currentTotalWeight = nextWeight;
                    passingTruckMap.put(waitingTruck, new Truck(truck_weights[waitingTruck], bridge_length));
                    waitingTruck++;
                }
                time++;
            }
            while (!passingTruckMap.isEmpty()) {
                currentTotalWeight = updatePassingTrucks(currentTotalWeight, passingTruckMap);
                time++;
            }
            return time;
        }

        private int updatePassingTrucks(int currentTotalWeight, Map<Integer, Truck> passingTruckMap) {
            for (Iterator<Map.Entry<Integer, Truck>> iterator = passingTruckMap.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<Integer, Truck> entry = iterator.next();
                entry.getValue().remainingTime--;
                if (entry.getValue().remainingTime == 0) {
                    iterator.remove();
                    currentTotalWeight = currentTotalWeight - entry.getValue().weight;
                }
            }
            return currentTotalWeight;
        }
    }
}