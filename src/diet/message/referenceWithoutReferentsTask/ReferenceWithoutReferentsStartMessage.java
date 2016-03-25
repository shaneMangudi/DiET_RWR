package diet.message.referenceWithoutReferentsTask;

import diet.server.ConversationController.ReferenceWithoutReferentsTask;

public class ReferenceWithoutReferentsStartMessage extends ReferenceWithoutReferentsTaskMessage {
    private final ReferenceWithoutReferentsTask.PlayerType playerType;
    private final int numberOfCards;

    public ReferenceWithoutReferentsStartMessage(ReferenceWithoutReferentsTask.PlayerType playerType, int numberOfCards) {
        super(MessageType.START);
        this.playerType = playerType;
        this.numberOfCards = numberOfCards;
    }

    public ReferenceWithoutReferentsTask.PlayerType getPlayerType() {
        return playerType;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }
}
