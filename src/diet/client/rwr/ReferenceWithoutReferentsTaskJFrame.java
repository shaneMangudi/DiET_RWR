package diet.client.rwr;

import diet.client.ConnectionToServer;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsCardMoveMessage;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsReadyStateMessage;
import diet.server.ConversationController.ReferenceWithoutReferentsTask.PlayerType;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ReferenceWithoutReferentsTaskJFrame extends JFrame {
    private final PlayerType playerType;
    private final ConnectionToServer connectionToServer;
    private final CardsPanel cardsPanel;

    public ReferenceWithoutReferentsTaskJFrame(PlayerType playerType, int numberOfCards, ConnectionToServer connectionToServer) {
        super(playerType.name());

        this.connectionToServer = connectionToServer;
        this.playerType = playerType;

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        cardsPanel = new CardsPanel(playerType, numberOfCards, this::onDrag);

        this.add(cardsPanel, BorderLayout.NORTH);
        this.add(new ControlsPanel(this::onReadyStateChange), BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }

    private void onDrag() {
        connectionToServer.sendMessage(new ReferenceWithoutReferentsCardMoveMessage(playerType, cardsPanel.getOrderedListOfCardIds()));
    }

    private void onReadyStateChange(ItemEvent itemEvent) {
        boolean readyState = itemEvent.getStateChange() == ItemEvent.SELECTED;
        connectionToServer.sendMessage(new ReferenceWithoutReferentsReadyStateMessage(playerType, readyState));
    }
}
