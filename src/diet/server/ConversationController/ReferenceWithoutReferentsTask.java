package diet.server.ConversationController;

import diet.message.MessageChatTextFromClient;
import diet.message.ReferenceWithoutReferentsTaskMessage;
import diet.server.Conversation;
import diet.server.Participant;
import diet.textmanipulationmodules.CyclicRandomTextGenerators.IRandomParticipantIDGenerator;

import java.util.Arrays;
import java.util.Vector;

public class ReferenceWithoutReferentsTask extends DefaultConversationController {
    private static final String DIRECTOR_ID = "Director";
    private static final String MATCHER_ID = "Matcher";

    private Participant director = null;
    private Participant matcher = null;

    public ReferenceWithoutReferentsTask(Conversation conversation) {
        super(setupGlobalConfig(conversation));

        DefaultConversationController.autologinParticipantIDGenerator = new IRandomParticipantIDGenerator() {
            boolean position = true;

            @Override
            public String getNext() {
                position = !position;
                return position ? DIRECTOR_ID : MATCHER_ID;
            }
        };
    }

    // Hacky hackety way of setting global config before object construction.
    public static Conversation setupGlobalConfig(Conversation conversation) {
        config.param_experimentID = "ReferenceWithoutReferentsTask";
        config.client_turnDisplayLimit = 2;

        return conversation;
    }

    @Override
    public boolean requestParticipantJoinConversation(String participantID) {
        return (participantID.equals(DIRECTOR_ID) && director == null) || (participantID.equals(MATCHER_ID) && matcher == null);
    }

    @Override
    public void participantJoinedConversation(Participant participant) {
        if (participant.getParticipantID().equals(DIRECTOR_ID)) {
            director = participant;
        } else if (participant.getParticipantID().equals(MATCHER_ID)) {
            matcher = participant;
        }

        if (!this.experimentHasStarted && director != null && matcher != null) {
            Vector<Participant> participants = new Vector<>(Arrays.asList(director, matcher));

            this.participantPartnering.createNewSubdialogue("rwr", participants);
            this.isTypingOrNotTyping.addPairWhoAreMutuallyInformedOfTyping(director, matcher);
            this.startExperiment();

            director.sendMessage(ReferenceWithoutReferentsTaskMessage.START);
            matcher.sendMessage(ReferenceWithoutReferentsTaskMessage.START);

            conversation.newsendInstructionToMultipleParticipants(participants, "Experiment has started.");
        }
    }

    @Override
    public synchronized void processChatText(Participant sender, MessageChatTextFromClient messageChatTextFromClient) {
        super.processChatText(sender, messageChatTextFromClient);
        conversation.newrelayTurnToPermittedParticipants(sender, messageChatTextFromClient);
    }
}
