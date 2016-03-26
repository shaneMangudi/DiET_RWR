package diet.client.rwr;

import diet.server.ConversationController.rwr.PlayerType;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.JPanel;
import static java.util.Arrays.asList;
import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;

class CardsPanel extends JPanel {
    private final List<Card> cards = new ArrayList<>();
    private final Map<Icon, Integer> idMapping = new HashMap<>();

    CardsPanel(PlayerType playerType, int numberOfCards, Runnable dragAction) {
        super(new FlowLayout());

        String cardClass = playerType.name().toLowerCase();
        CardDragHandler cardDragHandler = new CardDragHandler(this, dragAction);

        for (int id = 1; id <= numberOfCards; ++id) {
            Card card = new Card(cardDragHandler, cardClass, id);
            cards.add(card);
            idMapping.put(card.getIcon(), id);
        }

        addCardsInRandomOrder();
    }

    private void addCardsInRandomOrder() {
        shuffle(cards, new Random());
        cards.forEach(this::add);
    }

    List<Integer> getOrderedListOfCardIds() {
        return asList(this.getComponents())
                .stream()
                .map(component -> (Card) component)
                .map(Card::getIcon)
                .map(idMapping::get)
                .collect(toList());
    }

    public void reset() {
        this.removeAll();
        addCardsInRandomOrder();
    }
}
