package diet.message;

public class ReferenceWithoutReferentsTaskMessage extends Message {
    public static final ReferenceWithoutReferentsTaskMessage START = new ReferenceWithoutReferentsTaskMessage(MessageType.START);
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
