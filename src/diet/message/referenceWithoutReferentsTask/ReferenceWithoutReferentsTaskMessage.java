package diet.message.referenceWithoutReferentsTask;

import diet.message.Message;

public class ReferenceWithoutReferentsTaskMessage extends Message {
    private static final String EMAIL = "server";
    private static final String USERNAME = "";

    private final MessageType messageType;

    public ReferenceWithoutReferentsTaskMessage(MessageType messageType) {
        super(EMAIL, USERNAME);
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public enum MessageType {
        START
    }
}
