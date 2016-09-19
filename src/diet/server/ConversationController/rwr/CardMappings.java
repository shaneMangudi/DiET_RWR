package diet.server.ConversationController.rwr;

import java.util.*;

public class CardMappings {
    private static final List<Integer> STATIC_RANDOM_MAPPING = Arrays.asList(5, 1, 4, 3, 2, 7, 8, 6);

    private final Map<Integer, Integer> idMappings;

    private CardMappings(Map<Integer, Integer> idMappings) {
        this.idMappings = idMappings;
    }

    public static CardMappings from(List<Integer> cardIds, CardMappingType cardMappingType) {
        if (cardMappingType == CardMappingType.DIRECT) {
            return new CardMappings(zip(cardIds, cardIds));
        } else if (cardMappingType == CardMappingType.RANDOM || cardMappingType == CardMappingType.PSEUDO_RANDOM) {
            List<Integer> copyOfCardIds = new ArrayList<>(cardIds);
            Random random = cardMappingType == CardMappingType.RANDOM ? new Random() : new Random(0L);
            Collections.shuffle(copyOfCardIds, random);
            return new CardMappings(zip(cardIds, copyOfCardIds));
        } else if (cardMappingType == CardMappingType.STATIC_RANDOM) {
            if (cardIds.size() == 8 || cardIds.size() == 6) {
                return new CardMappings(zip(cardIds, STATIC_RANDOM_MAPPING.subList(0, cardIds.size())));
            }
            throw new RuntimeException("Unsupported card count for PSEUDO_RANDOM card mapping type: " + cardIds.size());
        } else {
            throw new RuntimeException("Unknown expected CardMappingType: " + cardMappingType);
        }
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
