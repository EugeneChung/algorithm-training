package programmers.dfsbfs;

import java.util.*;
import java.util.Map.Entry;

public class StringConversion {
    public static void main(String[] args) {
        //String[][] words = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] words = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        System.out.println("Solution : " + new Solution().solution(words));
    }

    static class Solution {
        public String[] solution(String[][] tickets) {
            Map<String, List<String>> travelMap = new HashMap<>();
            for (String[] ticket : tickets) {
                List<String> list = travelMap.computeIfAbsent(ticket[0], k -> new ArrayList<>());
                list.add(ticket[1]);
            }
            for (List<String> list : travelMap.values()) {
                if (list.size() > 1) {
                    Collections.sort(list);
                }
            }

            List<List<String>> routeList = new ArrayList<>();
            List<String> route = new ArrayList<>(Collections.singletonList("ICN"));
            Map<String, Set<String>> checkedRouteMap = new HashMap<>();
            findRoute(travelMap, tickets.length + 1, "ICN", checkedRouteMap, route, routeList);

            //for (int i = 0; i < tickets.length + 1; i++) {
            //    for (int j = 0; j < routeList.size(); j++) {
            //    }
            //}
            return routeList.get(0).toArray(new String[route.size()]);
        }
        
        public void findRoute(Map<String, List<String>> travelMap, int maxRouteLength, String current,
                              Map<String, Set<String>> checkedRouteMap, List<String> route, List<List<String>> routeList) {
            if (route.size() == maxRouteLength) {
                routeList.add(new ArrayList<>(route));
                return;
            }
            List<String> nexts = travelMap.get(current);
            Set<String> checked = checkedRouteMap.computeIfAbsent(current, k -> new HashSet<>());
            for (String next : nexts) {
                if (checked.contains(next)) continue;
                if (!routeList.isEmpty()) return;

                List<String> updatedRoute = new ArrayList<>(route);
                updatedRoute.add(next);

                Map<String, Set<String>> updatedCheckedMap = copyMap(checkedRouteMap);
                Set<String> updatedChecked = updatedCheckedMap.computeIfAbsent(current, k -> new HashSet<>());
                updatedChecked.add(next);

                findRoute(travelMap, maxRouteLength, next, updatedCheckedMap, updatedRoute, routeList);
            }
        }

        private Map<String, Set<String>> copyMap(Map<String, Set<String>> original) {
            Map<String, Set<String>> copied = new HashMap<>();
            for (Entry<String, Set<String>> entry : original.entrySet()) {
                copied.put(entry.getKey(), new HashSet<>(entry.getValue()));
            }
            return copied;
        }
    }
}