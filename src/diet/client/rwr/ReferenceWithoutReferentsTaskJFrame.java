package diet.client.rwr;

import diet.client.ConnectionToServer;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsCardMoveMessage;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsReadyStateMessage;
import diet.server.ConversationController.rwr.PlayerType;
import diet.server.Participant;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class ReferenceWithoutReferentsTaskJFrame extends JFrame {
    private final PlayerType playerType;
    private final String email;
    private final String username;
    private final ConnectionToServer connectionToServer;
    private final CardsPanel cardsPanel;
    private final ControlsPanel controlsPanel;

    public ReferenceWithoutReferentsTaskJFrame(String email, String username, PlayerType playerType, int numberOfCards, ConnectionToServer connectionToServer) {
        super(playerType.name());

        this.email = email;
        this.username = username;

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
        this.setResizable(false);
        this.setVisible(true);
    }

    public void resetTurn(boolean shouldRandomizeCardOrder) {
        if (shouldRandomizeCardOrder) {
            cardsPanel.reset();
        }
        controlsPanel.reset();
        SwingUtilities.invokeLater(this::sendOrderedListOfCardIds);
        this.pack();
        this.repaint();
    }

    private void sendOrderedListOfCardIds() {
        connectionToServer.sendMessage(new ReferenceWithoutReferentsCardMoveMessage(email, username, playerType, cardsPanel.getOrderedListOfCardIds()));
    }

    private void onReadyStateChange(ItemEvent itemEvent) {
        boolean readyState = itemEvent.getStateChange() == ItemEvent.SELECTED;
        connectionToServer.sendMessage(new ReferenceWithoutReferentsReadyStateMessage(email, username, playerType, readyState));
    }
}
