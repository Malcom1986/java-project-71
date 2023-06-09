package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Difference {
    public static Map<String, KeyStatus> makeDifference(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Map<String, KeyStatus> result = new LinkedHashMap<>();
        Set<String> keySet = new TreeSet<>(firstMap.keySet());
        keySet.addAll(secondMap.keySet());

        for (String key : keySet) {
            if (!firstMap.containsKey(key)) {
                result.put(key, new KeyStatus("added", null, secondMap.get(key)));
            } else if (!secondMap.containsKey(key)) {
                result.put(key, new KeyStatus("deleted", firstMap.get(key)));
            } else if (Objects.equals(firstMap.get(key), secondMap.get(key))) {
                result.put(key, new KeyStatus("unchanged", firstMap.get(key)));
            } else if (!Objects.equals(firstMap.get(key), secondMap.get(key))) {
                result.put(key, new KeyStatus("changed", firstMap.get(key), secondMap.get(key)));
            }
        }
        return result;
    }
}
