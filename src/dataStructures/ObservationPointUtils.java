package dataStructures;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObservationPointUtils {
    public static String findType(List<ObservationPoint> distanceList, int kNearest) {
        distanceList.sort(new Comparator<ObservationPoint>() {
            @Override
            public int compare(ObservationPoint o1, ObservationPoint o2) {
                return Double.compare(o1.getDistance(), o2.getDistance());
            }
        });

        Map<String, Integer> countResultTypeMap = new HashMap<>();
        for (int i = 0; i < kNearest; i++) {
            ObservationPoint point = distanceList.get(i);
            String type = point.getType();
            countResultTypeMap.put(type, countResultTypeMap.getOrDefault(type, 0) + 1);
        }

        String resultType = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : countResultTypeMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                resultType = entry.getKey();
            }
        }
        System.out.println("Type: " + resultType + " | count: " + maxCount);
        for (int i = 0; i < kNearest; i++) {
            System.out.println("Distance: " + distanceList.get(i).getDistance() + " with: " + distanceList.get(i).getType());
        }
        // System.out.println("Result type: " + resultType);
        return resultType;
    }
}