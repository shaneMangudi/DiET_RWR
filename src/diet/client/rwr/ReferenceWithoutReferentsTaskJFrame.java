package diet.client.rwr;

import diet.client.ConnectionToServer;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsCardMoveMessage;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsReadyStateMessage;
import diet.server.ConversationController.ReferenceWithoutReferentsTask.PlayerType;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class ReferenceWithoutReferentsTaskJFrame extends JFrame {
    private final PlayerType playerType;
    private final ConnectionToServer connectionToServer;
    private final CardsPanel cardsPanel;
    private final ControlsPanel controlsPanel;

    public ReferenceWithoutReferentsTaskJFrame(PlayerType playerType, int numberOfCards, ConnectionToServer connectionToServer) {
        super(playerType.name());

        this.connectionToServer = connectionToServer;
        this.playerType = playerType;

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        this.cardsPanel = new CardsPanel(playerType, numberOfCards, this::sendOrderedListOfCardIds);
        this.controlsPanel = new ControlsPanel(this::onReadyStateChange);

        this.add(cardsPanel, BorderLayout.NORTH);
        this.add(controlsPanel, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(this::sendOrderedListOfCardIds);

        this.pack();
        this.setVisible(true);
    }

    public void resetTurn() {
        cardsPanel.reset();
        controlsPanel.reset();
        SwingUtilities.invokeLater(this::sendOrderedListOfCardIds);
        this.pack();
    }

    private void sendOrderedListOfCardIds() {
        connectionToServer.sendMessage(new ReferenceWithoutReferentsCardMoveMessage(playerType, cardsPanel.getOrderedListOfCardIds()));
    }

    private void onReadyStateChange(ItemEvent itemEvent) {
        boolean readyState = itemEvent.getStateChange() == ItemEvent.SELECTED;
        connectionToServer.sendMessage(new ReferenceWithoutReferentsReadyStateMessage(playerType, readyState));
    }
}
