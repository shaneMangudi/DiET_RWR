package diet.client;

import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsCardMoveMessage;
import diet.server.ConversationController.ReferenceWithoutReferentsTask;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import static java.lang.ClassLoader.getSystemResource;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static javax.swing.SwingUtilities.convertPoint;

class ReferenceWithoutReferentsTaskJFrame extends JFrame {
    private final Map<ImageIcon, Integer> cards;
    private final ConnectionToServer connectionToServer;
    private final ReferenceWithoutReferentsTask.PlayerType playerType;

    ReferenceWithoutReferentsTaskJFrame(ReferenceWithoutReferentsTask.PlayerType playerType, int numberOfCards, ConnectionToServer connectionToServer) {
        super(playerType.name());

        this.cards = new HashMap<>();
        this.connectionToServer = connectionToServer;
        this.playerType = playerType;

        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        String cardClass = playerType.name().toLowerCase();
        CardDragHandler cardDragHandler = new CardDragHandler(this.getContentPane(), this::onDrag);

        for (int id = 1; id <= numberOfCards; ++id) {
            ImageIcon imageIcon = new ImageIcon(getSystemResource("rwr/" + cardClass + "/" + id + ".png"));
            cards.put(imageIcon, id);
            JLabel jLabel = new JLabel(imageIcon, JLabel.CENTER);
            jLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jLabel.addMouseListener(cardDragHandler);
            jLabel.addMouseMotionListener(cardDragHandler);
            this.add(jLabel);
        }

        this.pack();
        this.setVisible(true);
    }

    private List<Integer> getOrderedListOfCardIds() {
        return asList(this.getContentPane().getComponents())
                .stream()
                .map(component -> (JLabel) component)
                .map(JLabel::getIcon)
                .map(cards::get)
                .collect(toList());
    }

    private void onDrag() {
        connectionToServer.sendMessage(new ReferenceWithoutReferentsCardMoveMessage(playerType, getOrderedListOfCardIds()));
    }
}

class CardDragHandler extends MouseAdapter {
    private final Container container;
    private final Runnable dragAction;

    CardDragHandler(Container container, Runnable dragAction) {
        this.container = container;
        this.dragAction = dragAction;
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

            dragAction.run();
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