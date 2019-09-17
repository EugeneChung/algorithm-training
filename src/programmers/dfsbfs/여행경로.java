package programmers.dfsbfs;

import helpers.TestHelper;

import java.util.*;

public class 여행경로 {
    public static void main(String[] args) {
//        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}}; // [ICN, JFK, HND, IAD]
//        String[][] tickets = {{"ICN", "JFK"}, {"JFK", "ICN"}, {"ICN", "MFA"}}; // [ICN, JFK, ICN, MFA]
//        String[][] tickets = {{"ICN", "JFK"}, {"JFK", "ICN"}, {"ICN", "MFA"}, {"MFA", "ICN"}}; // [ICN, JFK, ICN, MFA, ICN]
//        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}}; // ICN, ATL, ICN, SFO, ATL, SFO]
        //String[][] tickets = { { "ICN", "COO" }, { "COO", "ICN" },{ "COO", "ICN" } };
        String[][] tickets = { { "ICN", "COO" }, { "ICN", "BOO" },{ "COO", "ICN" } };
        TestHelper.printSolution(new Solution().solution(tickets));
    }

    static class Solution {
        public String[] solution(String[][] tickets) {
            if (tickets.length == 0) return new String[]{};

            Map<String, List<String>> ticketMap = new HashMap<>();
            for (String[] ticket : tickets) {
                List<String> dests = ticketMap.computeIfAbsent(ticket[0], k -> new ArrayList<>());
                dests.add(ticket[1]);
            }
            for (List<String> dests : ticketMap.values()) {
                Collections.sort(dests);
            }

            String prevStart = "ICN";
            String nextStart = "ICN";
            List<String> routes = new ArrayList<>(Collections.singleton(nextStart));
            while (!ticketMap.isEmpty()) {
                List<String> dests = ticketMap.get(nextStart);
                if (dests == null) {
                    while (!routes.isEmpty()) {
                        dests = ticketMap.get(prevStart);
                        if (dests == null) {
                            break;
                        }
                        routes.remove(routes.size() - 1);
                        nextStart = prevStart;
                    }
                }
                String dest = dests.remove(0);
                routes.add(dest);
                if (dests.isEmpty()) {
                    ticketMap.remove(nextStart);
                }

                prevStart = nextStart;
                nextStart = dest;
            }
            return routes.toArray(new String[0]);
        }
    }
}