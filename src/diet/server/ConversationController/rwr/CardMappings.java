package diet.server.ConversationController.rwr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CardMappings {
    private final Map<Integer, Integer> idMappings;

    private CardMappings(Map<Integer, Integer> idMappings) {
        this.idMappings = idMappings;
    }

    public static CardMappings from(List<Integer> cardIds, CardMappingType cardMappingType) {
        if (cardMappingType == CardMappingType.DIRECT) {
            return new CardMappings(zip(cardIds, cardIds));
        }

        Random random;
        if (cardMappingType == CardMappingType.RANDOM) {
            random = new Random();
        } else if (cardMappingType == CardMappingType.FIXED_RANDOM) {
            random = new Random(0L);
        } else {
            throw new RuntimeException("Unknown expected CardMappingType: " + cardMappingType);
        }
        List<Integer> copyOfCardIds = new ArrayList<>(cardIds);
        Collections.shuffle(copyOfCardIds, random);
        return new CardMappings(zip(cardIds, copyOfCardIds));
    }

    private static Map<Integer, Integer> zip(List<Integer> keys, List<Integer> values) {
        Map<Integer, Integer> idMappings = new HashMap<>();
        for (int i = 0; i < keys.size(); ++i) {
            idMappings.put(keys.get(i), values.get(i));
        }
        return idMappings;
    }

    public int countMismatches(List<Integer> director, List<Integer> matcher) {
        int mismatches = 0;
        for (int i = 0; i < director.size(); ++i) {
            if (!idMappings.get(director.get(i)).equals(matcher.get(i))) {
                ++mismatches;
            }
        }
        return mismatches;
    }
}
