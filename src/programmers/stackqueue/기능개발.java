package programmers.stackqueue;

import helpers.TestHelper;

import java.util.*;

public class 기능개발 {
    public static void main(String[] args) {
        int[] progresses = {1, 80, 95}; int[] speeds = {5, 10, 10}; //3
//        int[] progresses = {90, 1, 95}; int[] speeds = {5, 10, 10}; //1, 2
//        int[] progresses = {1, 1}; int[] speeds = {2, 1}; //1,1
//        int[] progresses = {1, 1}; int[] speeds = {1, 2}; //2
//        int[] progresses = {1}; int[] speeds = {1}; //1
//        int[] progresses = {}; int[] speeds = {}; //0
//        int[] progresses = {93, 30, 55}; int[] speeds = {1, 30, 5}; //2,1

        TestHelper.printSolution(new Solution().solution(progresses, speeds));
    }

    static class Solution {
        private class Task implements Comparable<Task> {
            final int index;
            int currentProgress;

            private Task(int index, int currentProgress) {
                this.index = index;
                this.currentProgress = currentProgress;
            }

            @Override
            public String toString() {
                return "Task[" + index + "]=" + currentProgress;
            }

            @Override
            public int compareTo(Task o) {
                return Integer.compare(this.index, o.index);
            }
        }

        public int[] solution(int[] progresses, int[] speeds) {
            if (progresses.length < 2) {
                return new int[]{progresses.length};
            }
            List<Task> tasks = new ArrayList<>(progresses.length);
            for (int i = 0; i < progresses.length; i++) {
                tasks.add(new Task(i, progresses[i]));
            }
            List<Integer> answer = new ArrayList<>();
            TreeSet<Task> deploymentWaitingTasks = new TreeSet<>();
            int nextDeployableTask = 0;

            while (!tasks.isEmpty()) {
                for (Iterator<Task> it = tasks.iterator(); it.hasNext(); ) {
                    Task task = it.next();
                    task.currentProgress += speeds[task.index];
                    if (task.currentProgress >= 100) {
                        deploymentWaitingTasks.add(task);
                        it.remove();
                    }
                }
                if (!deploymentWaitingTasks.isEmpty()) {
//                    TestHelper.log(deploymentWaitingTasks);
                    int deployed = 0;
                    do {
                        Task first = deploymentWaitingTasks.first();
                        if (first.index == nextDeployableTask) {
                            deploymentWaitingTasks.pollFirst();
                            nextDeployableTask++;
                            deployed++;
                        } else {
                            break;
                        }
                    } while (!deploymentWaitingTasks.isEmpty());
                    if (deployed > 0) {
                        answer.add(deployed);
                    }
                }
            }

            return answer.stream().mapToInt(x -> x).toArray();
        }
    }
}