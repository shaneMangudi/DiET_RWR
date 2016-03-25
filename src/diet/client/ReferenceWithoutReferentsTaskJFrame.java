package diet.client;

import diet.server.ConversationController.ReferenceWithoutReferentsTask;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import static java.util.stream.Collectors.toList;
import static javax.swing.SwingUtilities.convertPoint;

public class ReferenceWithoutReferentsTaskJFrame extends JFrame {
    private final List<Card> cards = new ArrayList<>();

    public ReferenceWithoutReferentsTaskJFrame(ReferenceWithoutReferentsTask.PlayerType playerType, int numberOfCards) {
        super(playerType.name());
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        CardDragHandler cardDragHandler = new CardDragHandler(this.getRootPane().getContentPane());

        for (int i = 1; i <= numberOfCards; ++i) {
            Card card = new Card(playerType, i);
            cards.add(card);
            JLabel jLabel = new JLabel(card.getImageIcon(), JLabel.CENTER);
            jLabel.addMouseListener(cardDragHandler);
            jLabel.addMouseMotionListener(cardDragHandler);
            this.add(jLabel);
        }

        this.pack();
        this.setVisible(true);
    }

    public List<Integer> getCardIdsOrdered() {
        return cards.stream().map(Card::getId).collect(toList());
    }

    private class Card {
        private final ImageIcon image;
        private final int id;

        Card(ReferenceWithoutReferentsTask.PlayerType playerType, int id) {
            this.image = new ImageIcon(ClassLoader.getSystemResource("rwr/" + playerType.name().toLowerCase() + "/" + id + ".png"));
            this.id = id;
        }

        int getId() {
            return id;
        }

        ImageIcon getImageIcon() {
            return image;
        }
    }
}

class CardDragHandler extends MouseAdapter {
    private final Container container;

    CardDragHandler(Container container) {
        this.container = container;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        container.setCursor((getTargetJLabel(mouseEvent) != null) ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        JLabel jLabel = (JLabel) mouseEvent.getSource();
        JLabel targetJLabel = getTargetJLabel(mouseEvent);
        if (targetJLabel != null) {
            Icon targetIcon = targetJLabel.getIcon();
            targetJLabel.setIcon(jLabel.getIcon());
            jLabel.setIcon(targetIcon);
        }
        container.setCursor(Cursor.getDefaultCursor());
    }

    private JLabel getTargetJLabel(MouseEvent mouseEvent) {
        JLabel jLabel = (JLabel) mouseEvent.getSource();
        Component targetComponent = container.getComponentAt(convertPoint(jLabel, mouseEvent.getPoint(), container));
        if (targetComponent instanceof JLabel && !targetComponent.equals(jLabel)) {
            return (JLabel) targetComponent;
        }
        return null;
    }
}