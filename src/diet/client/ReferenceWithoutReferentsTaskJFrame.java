package diet.client;

import diet.server.ConversationController.ReferenceWithoutReferentsTask;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
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

public class ReferenceWithoutReferentsTaskJFrame extends JFrame {
    private static final MouseAdapter CARD_DRAG_HANDLER = new MouseAdapter() {
        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            JLabel jLabel = (JLabel) mouseEvent.getSource();
            if (getTargetJLabel(mouseEvent) != null) {
                jLabel.getParent().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            } else {
                jLabel.getParent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
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
            jLabel.getParent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    };

    private final List<Card> cards = new ArrayList<>();

    public ReferenceWithoutReferentsTaskJFrame(ReferenceWithoutReferentsTask.PlayerType playerType, int numberOfCards) {
        super(playerType.name());
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        for (int i = 1; i <= numberOfCards; ++i) {
            Card card = new Card(playerType, i);
            cards.add(card);
            JLabel jLabel = new JLabel(card.getImageIcon(), JLabel.CENTER);
            jLabel.addMouseListener(CARD_DRAG_HANDLER);
            jLabel.addMouseMotionListener(CARD_DRAG_HANDLER);
            this.add(jLabel);
        }

        this.pack();
        this.setVisible(true);
    }

    private static JLabel getTargetJLabel(MouseEvent mouseEvent) {
        JLabel jLabel = (JLabel) mouseEvent.getSource();
        Component targetComponent = jLabel.getParent().getComponentAt(mouseEvent.getLocationOnScreen());
        if (targetComponent instanceof JLabel && !targetComponent.equals(jLabel)) {
            return (JLabel) targetComponent;
        }
        return null;
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