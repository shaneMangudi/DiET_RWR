package diet.server.ConversationController;

import diet.message.MessageChatTextFromClient;
import diet.server.Conversation;
import diet.server.Participant;
import diet.textmanipulationmodules.CyclicRandomTextGenerators.IRandomParticipantIDGenerator;

public class ReferenceWithoutReferentsTask extends DefaultConversationController {
	private static final String DIRECTOR_ID = "Director";
	private static final String MATCHER_ID = "Matcher";

	private Participant director = null;
	private Participant matcher = null;

	public ReferenceWithoutReferentsTask(Conversation conversation) {
		super(conversation);
		config.param_experimentID = "ReferenceWithoutReferentsTask";
		config.client_turnDisplayLimit = 2;

		DefaultConversationController.autologinParticipantIDGenerator = new IRandomParticipantIDGenerator() {
			boolean position = true;

			@Override
			public String getNext() {
				position = !position;
				return position ? DIRECTOR_ID : MATCHER_ID;
			}
		};
	}

	@Override
	public boolean requestParticipantJoinConversation(String participantID) {
		if (participantID.equals(DIRECTOR_ID)) return director == null;
		if (participantID.equals(MATCHER_ID)) return matcher == null;
		return false;
	}

	@Override
	public void participantJoinedConversation(Participant participant) {
		if (participant.getParticipantID().equals(DIRECTOR_ID)) {
			director = participant;
		} else if (participant.getParticipantID().equals(MATCHER_ID)) {
			matcher = participant;
		}
		if (director != null && matcher != null) {
			this.participantPartnering.createNewSubdialogue("rwr", director, matcher);
			this.isTypingOrNotTyping.addPairWhoAreMutuallyInformedOfTyping(director, matcher);
			this.startExperiment();
		}
	}

	@Override
	public synchronized void processChatText(Participant sender, MessageChatTextFromClient messageChatTextFromClient) {
		super.processChatText(sender, messageChatTextFromClient);
		conversation.newrelayTurnToPermittedParticipants(sender, messageChatTextFromClient);
	}
}
