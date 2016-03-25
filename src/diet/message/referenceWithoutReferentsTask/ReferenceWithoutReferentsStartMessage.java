package diet.message.referenceWithoutReferentsTask;

import diet.server.ConversationController.ReferenceWithoutReferentsTask.PlayerType;

public class ReferenceWithoutReferentsStartMessage extends ReferenceWithoutReferentsTaskMessage {
    private final PlayerType playerType;
    private final int numberOfCards;

    public ReferenceWithoutReferentsStartMessage(PlayerType playerType, int numberOfCards) {
        super(MessageType.START);
        this.playerType = playerType;
        this.numberOfCards = numberOfCards;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }
}
