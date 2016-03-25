package diet.message.referenceWithoutReferentsTask;

import diet.message.MessageTask;

public class ReferenceWithoutReferentsTaskMessage extends MessageTask {
    private static final String EMAIL = "server";
    private static final String USERNAME = "";

    private final MessageType messageType;

    ReferenceWithoutReferentsTaskMessage(MessageType messageType) {
        super(EMAIL, USERNAME);
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public enum MessageType {
        START,
        CARD_MOVE,
        READY_STATE
    }
}
