package diet.server.ConversationController.rwr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MismatchCounter {
    private final Map<Integer, Integer> idMappings;

    public MismatchCounter(List<Integer> keys, List<Integer> values) {
        this(zip(keys, values));
    }

    private MismatchCounter(Map<Integer, Integer> idMappings) {
        this.idMappings = idMappings;
    }

    private static Map<Integer, Integer> zip(List<Integer> keys, List<Integer> values) {
        Map<Integer, Integer> idMappings = new HashMap<>();
        for (int i = 0; i < keys.size(); ++i) {
            idMappings.put(keys.get(i), values.get(i));
        }
        return idMappings;
    }

    public int count(List<Integer> director, List<Integer> matcher) {
        int mismatches = 0;
        for (int i = 0; i < director.size(); ++i) {
            if (!idMappings.get(director.get(i)).equals(matcher.get(i))) {
                ++mismatches;
            }
        }
        return mismatches;
    }
}