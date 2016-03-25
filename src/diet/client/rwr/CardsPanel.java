package diet.client.rwr;

import diet.server.ConversationController.ReferenceWithoutReferentsTask.PlayerType;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

class CardsPanel extends JPanel {
    private final Map<Icon, Integer> cards = new HashMap<>();

    CardsPanel(PlayerType playerType, int numberOfCards, Runnable dragAction) {
        super(new FlowLayout());

        String cardClass = playerType.name().toLowerCase();
        CardDragHandler cardDragHandler = new CardDragHandler(this, dragAction);
        for (int id = 1; id <= numberOfCards; ++id) {
            Card card = new Card(cardDragHandler, cardClass, id);
            cards.put(card.getIcon(), id);
            this.add(card);
        }
    }

    List<Integer> getOrderedListOfCardIds() {
        return asList(this.getComponents())
                .stream()
                .map(component -> (Card) component)
                .map(JLabel::getIcon)
                .map(cards::get)
                .collect(toList());
    }
}
