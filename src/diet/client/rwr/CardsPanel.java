package diet.client.rwr;

import diet.server.ConversationController.rwr.PlayerType;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;
import javax.swing.Icon;
import javax.swing.JPanel;

import static diet.server.ConversationController.rwr.PlayerType.DIRECTOR;
import static java.util.Arrays.asList;
import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;

class CardsPanel extends JPanel {
    private final List<Card> cards = new ArrayList<>();
    private final Map<Icon, Integer> idMapping = new HashMap<>();

    private final JPanel topPanel;
    private final JPanel bottomPanel;

    CardsPanel(PlayerType playerType, int numberOfCards, Runnable dragAction) {
        super(new BorderLayout());
        this.topPanel = new JPanel(new FlowLayout());
        this.bottomPanel = new JPanel(new FlowLayout());

        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);

        String cardClass = playerType.name().toLowerCase();
        CardDragHandler cardDragHandler = playerType == DIRECTOR ? null : new CardDragHandler(this, dragAction);

        for (int id = 1; id <= numberOfCards; ++id) {
            Card card = new Card(cardDragHandler, cardClass, id);
            cards.add(card);
            idMapping.put(card.getIcon(), id);
        }

        addCardsInRandomOrder();
    }

    private void addCardsInRandomOrder() {
        shuffle(cards, new Random());

        int middleIndex = (int) Math.ceil(cards.size() / 2.0f);
        cards.subList(0, middleIndex).forEach(this.topPanel::add);
        cards.subList(middleIndex, cards.size()).forEach(this.bottomPanel::add);
    }

    List<Integer> getOrderedListOfCardIds() {
        return Stream.concat(asList(this.topPanel.getComponents()).stream(), asList(this.bottomPanel.getComponents()).stream())
                .map(component -> (Card) component)
                .map(Card::getIcon)
                .map(idMapping::get)
                .collect(toList());
    }

    public void reset() {
        this.topPanel.removeAll();
        this.bottomPanel.removeAll();
        addCardsInRandomOrder();
        this.invalidate();
    }
}
