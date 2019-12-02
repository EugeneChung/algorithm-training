package programmers.dfsbfs;

import helpers.TestHelper;

import java.util.*;

public class 여행경로 {
    public static void main(String[] args) {
//        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}}; // [ICN, JFK, HND, IAD]
//        String[][] tickets = {{"ICN", "JFK"}, {"JFK", "ICN"}, {"ICN", "MFA"}}; // [ICN, JFK, ICN, MFA]
//        String[][] tickets = {{"ICN", "JFK"}, {"JFK", "ICN"}, {"ICN", "MFA"}, {"MFA", "ICN"}}; // [ICN, JFK, ICN, MFA, ICN]
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}}; // ICN, ATL, ICN, SFO, ATL, SFO]
        //String[][] tickets = { { "ICN", "COO" }, { "COO", "ICN" },{ "COO", "ICN" } };
//        String[][] tickets = { { "ICN", "COO" }, { "ICN", "BOO" },{ "COO", "ICN" },{"BOO", "DOO"} }; // ICN -> COO -> ICN -> BOO -> DOO
        TestHelper.printSolution(new Solution().solution(tickets));
    }

    static class Solution {
        private class Ticket implements Comparable<Ticket> {
            final String from;
            final String to;

            private Ticket(String from, String to) {
                this.from = from;
                this.to = to;
            }

            @Override
            public int compareTo(Ticket o) {
                return to.compareTo(o.to);
            }
        }

        public String[] solution(String[][] tickets) {
            if (tickets.length == 0) return new String[]{};

            List<Ticket> ticketList = new ArrayList<>(tickets.length);
            for (String[] ticket : tickets) {
                ticketList.add(new Ticket(ticket[0], ticket[1]));
            }
            Collections.sort(ticketList);

            boolean[] visit = new boolean[tickets.length];
            List<String> routes = new ArrayList<>(tickets.length * 2);

            dfs("ICN", ticketList, visit, routes, 0);
            return routes.toArray(new String[0]); 
        }

        private boolean dfs(String from, List<Ticket> ticketList, boolean[] visit, List<String> routes, int ticketUsedCount) {
            routes.add(from);
            if (ticketUsedCount == ticketList.size()) {
                return true;
            }

            for (int i = 0; i < ticketList.size(); i ++) {
                Ticket ticket = ticketList.get(i);
                if (ticket.from.equals(from) && !visit[i]) {
                    visit[i] = true;
                    if (dfs(ticket.to, ticketList, visit, routes, ticketUsedCount + 1)) {
                        return true;
                    }
                    visit[i] = false;
                }
            }

            routes.remove(routes.size() - 1);
            return false;
        }
    }
}