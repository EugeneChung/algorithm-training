package programmers;

import java.util.*;

public class CopyMap {
    private static Map<String, Set<String>> copyMap(Map<String, Set<String>> original) {
        Map<String, Set<String>> copied = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : original.entrySet()) {
            System.out.println(entry.getValue());
            copied.put(entry.getKey(), new HashSet<>(entry.getValue()));
        }
        return copied;
    }

    public static void main(String[] args) {
        Map<String, Set<String>> checkedRouteMap = new HashMap<>();
        Set<String> checked = checkedRouteMap.computeIfAbsent("ICN", k -> new HashSet<>());
        checked.add("ATL");
        Map<String, Set<String>> updatedCheckedMap = copyMap(checkedRouteMap);
        System.out.println("prev : " + checkedRouteMap + ", copied=" + updatedCheckedMap);
    }
}