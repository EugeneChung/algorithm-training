package programmers.heap;

import helpers.TestHelper;

import java.util.*;

public class 디스크컨트롤러 {
    public static void main(String[] args) {
//        int[][] jobs = {{0,3}}; // 3
//        int[][] jobs = {{5,3}}; // 3
//        int[][] jobs = {{0,3},{0,3}}; // 9/2=4
//        int[][] jobs = {{0,3},{4,4}}; // 7/2=3
//        int[][] jobs = {{0,3},{1,9},{2,6}}; // 9
//        int[][] jobs = {{0,3},{1,4},{2,5}}; // 19/3=6
//        int[][] jobs = {{0,3},{1,1},{3,5}}; // 12/3=4
        int[][] jobs = {{0,9},{0,4},{0,5},{0,7},{0,3}}; // 13

        TestHelper.printSolution(
            new Solution().solution(jobs)
        );
    }

    static class Solution {
        class Job implements Comparable<Job> {
            final int requestTime;
            final int usingTime;
            int endTime;

            Job(int requestTime, int value) {
                this.requestTime = requestTime;
                this.usingTime = value;
            }

            void setEndTime(int currentTime) {
                endTime = currentTime + usingTime;
            }

            int getTotalProcessingTime(int currentTime) {
                return currentTime - requestTime;
            }

            @Override
            public String toString() {
                return "Job[" + requestTime + "]=" + usingTime;
            }

            @Override
            public int compareTo(Job o) {
                int compare = Integer.compare(this.requestTime, o.requestTime);
                if (compare == 0) {
                    return Integer.compare(this.usingTime, o.usingTime);
                }
                return compare;
            }
        }

        class JobComparator implements Comparator<Job> {
            final int executingJobEndTime;

            JobComparator(int executingJobEndTime) {
                this.executingJobEndTime = executingJobEndTime;
            }

            @Override
            public int compare(Job o1, Job o2) {
                int o1First = executingJobEndTime - o1.requestTime + o1.usingTime;
                o1First += executingJobEndTime - o2.requestTime + o1.usingTime + o2.usingTime;
                int o2First = executingJobEndTime - o2.requestTime + o2.usingTime;
                o2First += executingJobEndTime - o1.requestTime + o2.usingTime + o1.usingTime;
                return Integer.compare(o1First, o2First);
            }
        }

        class FirstJobComparator implements Comparator<Job> {
            @Override
            public int compare(Job o1, Job o2) {
                return Integer.compare(o1.usingTime, o2.usingTime);
            }
        }

        public int solution(int[][] jobs) {
            // Sort by job request time
            // (문제에서 정렬되어서 들어온다는 가정이 없다.)
            List<Job> jobList = new ArrayList<>(jobs.length);
            for (int[] job : jobs) {
                jobList.add(new Job(job[0], job[1]));
            }
            Collections.sort(jobList);

            int answer = 0;
            Job executingJob = null;
            int time = jobList.get(0).requestTime;
            int jobIndex = 0;

            // 최초에 실행할 job을 결정하기 위해서 최초 request time에 들어온 job들을 FirstJobComparator에 의해서 정렬한다.
            // (request time이 동일할 경우 소요 시간이 짧은 걸 먼저 실행해야 평균이 최소값이 된다.)
            PriorityQueue<Job> jobPriorityQueue = new PriorityQueue<>(new FirstJobComparator());
            while (jobIndex < jobList.size()) {
                Job job = jobList.get(jobIndex);
                if (job.requestTime <= time) {
                    jobPriorityQueue.add(job);
                    jobIndex++;
                } else {
                    break;
                }
            }
            executingJob = jobPriorityQueue.poll(); // the first job
            if (executingJob != null) {
                executingJob.setEndTime(time);
            }

            // 시간(time)을 늘려가면서 각 시간에 따라 다음의 두 가지를 수행한다.
            // 1. job list에서 현재 시간 기준으로 처리해야 할 job들을 꺼내서 PriorityQueue에 넣는다.
            // Priority 계산은 JobComparator의 compare() method 참고
            // 2. PriorityQueue에서 우선 순위가 가장 높은 job을 꺼내서 실행한다.
            //
            // [주의] 새로운 job을 실행하게 되는 경우에는 priority가 바뀐다.
            // 그러므로 새로운 기준으로 PriorityQueue를 만들고 기존의 PriorityQueue에 있던 job들을 새로운 queue에 넣어줘야 한다. 
            while (executingJob != null || jobIndex < jobList.size()) {
                while (jobIndex < jobList.size()) {
                    Job job = jobList.get(jobIndex);
                    if (job.requestTime <= time) {
                        if (executingJob == null) {
                            executingJob = job;
                            executingJob.setEndTime(time);
                            if (!jobPriorityQueue.isEmpty()) {
                                jobPriorityQueue = makeNewJobQueue(executingJob, time, jobPriorityQueue);
                            }
                        } else {
                            jobPriorityQueue.add(job);
                        }
                        jobIndex++;
                    } else {
                        break;
                    }
                }

                // determine the next executingJob if necessary
                if (executingJob != null && executingJob.endTime == time) {
                    answer += executingJob.getTotalProcessingTime(time);
                    if (jobPriorityQueue.isEmpty()) {
                        executingJob = null;
                    } else {
                        executingJob = jobPriorityQueue.poll();
                        executingJob.setEndTime(time);
                        if (!jobPriorityQueue.isEmpty()) {
                            jobPriorityQueue = makeNewJobQueue(executingJob, time, jobPriorityQueue);
                        }
                    }
                }

                time++;
            }
//            TestHelper.log(answer);
            return answer / jobList.size();
        }

        private PriorityQueue<Job> makeNewJobQueue(Job execuingJob, int time, PriorityQueue<Job> jobPriorityQueue) {
            PriorityQueue<Job> newPriorityQueue = new PriorityQueue<>(new JobComparator(time + execuingJob.usingTime));
            while (!jobPriorityQueue.isEmpty()) {
                newPriorityQueue.add(jobPriorityQueue.poll());
            }
            return newPriorityQueue;
        }
    }
}