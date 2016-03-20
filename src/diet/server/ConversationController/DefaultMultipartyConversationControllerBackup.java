package diet.server.ConversationController;

import diet.message.MessageChatTextFromClient;
import diet.message.MessageKeypressed;
import diet.message.MessageWYSIWYGDocumentSyncFromClientInsert;
import diet.message.MessageWYSIWYGDocumentSyncFromClientRemove;
import diet.server.Conversation;
import diet.server.Participant;
import java.util.Vector;

public class DefaultMultipartyConversationControllerBackup extends DefaultConversationController {

	public DefaultMultipartyConversationControllerBackup(Conversation c) {
		super(c);
	}

	@Override
	public void participantJoinedConversation(Participant p) {
		super.participantJoinedConversation(p);

		if (conversation.getParticipants().getAllParticipants().size() > 1) {

			Vector v = conversation.getParticipants().getAllParticipants();

			this.participantPartnering.createNewSubdialogue(conversation.getParticipants().getAllParticipants());
			conversation.newsendInstructionToMultipleParticipants(v, "Thankyou. Please start");

		}
	}

	@Override
	public void processChatText(Participant sender, MessageChatTextFromClient mct) {
		Vector recipients = participantPartnering.getRecipients(sender);
		conversation.newrelayTurnToPermittedParticipants(sender, mct);

	}

	@Override
	public void processKeyPress(Participant sender, MessageKeypressed mkp) {

	}

	@Override
	public void processWYSIWYGTextInserted(Participant sender, MessageWYSIWYGDocumentSyncFromClientInsert mWYSIWYGkp) {
		this.isTypingOrNotTyping.processDoingsByClient(sender);
	}

	@Override
	public void processWYSIWYGTextRemoved(Participant sender, MessageWYSIWYGDocumentSyncFromClientRemove mWYSIWYGkp) {
		this.isTypingOrNotTyping.processDoingsByClient(sender);
	}

}
