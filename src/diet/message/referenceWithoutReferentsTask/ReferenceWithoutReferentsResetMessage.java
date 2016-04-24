package diet.message.referenceWithoutReferentsTask;

public class ReferenceWithoutReferentsResetMessage extends ReferenceWithoutReferentsTaskMessage {
    private final boolean shouldRandomizeCardOrder;

    public ReferenceWithoutReferentsResetMessage(boolean shouldRandomizeCardOrder) {
        super(MessageType.RESET);
        this.shouldRandomizeCardOrder = shouldRandomizeCardOrder;
    }

    public boolean shouldRandomizeCardOrder() {
        return shouldRandomizeCardOrder;
    }
}
