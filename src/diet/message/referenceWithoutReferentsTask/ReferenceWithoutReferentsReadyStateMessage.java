package diet.message.referenceWithoutReferentsTask;

import diet.server.ConversationController.ReferenceWithoutReferentsTask.PlayerType;

public class ReferenceWithoutReferentsReadyStateMessage extends ReferenceWithoutReferentsTaskMessage {
    private final PlayerType playerType;
    private final boolean readyState;

    public ReferenceWithoutReferentsReadyStateMessage(PlayerType playerType, boolean readyState) {
        super(MessageType.READY_STATE);
        this.playerType = playerType;
        this.readyState = readyState;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public boolean getReadyState() {
        return readyState;
    }
}
