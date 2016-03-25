package diet.message.referenceWithoutReferentsTask;

import diet.server.ConversationController.ReferenceWithoutReferentsTask.PlayerType;
import java.util.List;

public class ReferenceWithoutReferentsCardMoveMessage extends ReferenceWithoutReferentsTaskMessage {
    private final PlayerType playerType;
    private final List<Integer> orderedListOfCardIds;

    public ReferenceWithoutReferentsCardMoveMessage(PlayerType playerType, List<Integer> orderedListOfCardIds) {
        super(MessageType.CARD_MOVE);

        this.playerType = playerType;
        this.orderedListOfCardIds = orderedListOfCardIds;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public List<Integer> getOrderedListOfCardIds() {
        return orderedListOfCardIds;
    }
}
