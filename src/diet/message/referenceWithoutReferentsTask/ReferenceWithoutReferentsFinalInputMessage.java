package diet.message.referenceWithoutReferentsTask;

public class ReferenceWithoutReferentsFinalInputMessage extends ReferenceWithoutReferentsTaskMessage {
    private final String input;

    public ReferenceWithoutReferentsFinalInputMessage(String email, String username, String input) {
        super(email, username, MessageType.FINAL_INPUT);
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
