package diet.server.ConversationController;

import diet.message.MessageChatTextFromClient;
import diet.message.MessageTask;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsCardMoveMessage;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsReadyStateMessage;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsResetMessage;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsSetupMessage;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsTaskMessage;
import diet.server.Conversation;
import diet.server.ConversationController.rwr.CardMappingType;
import diet.server.ConversationController.rwr.CardMappings;
import diet.server.ConversationController.rwr.PlayerType;
import diet.server.Participant;
import diet.textmanipulationmodules.CyclicRandomTextGenerators.IRandomParticipantIDGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.IntStream;
import javax.swing.JOptionPane;

import static diet.server.ConversationController.rwr.PlayerType.DIRECTOR;
import static diet.server.ConversationController.rwr.PlayerType.MATCHER;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static javax.swing.JOptionPane.showInputDialog;

@SuppressWarnings("unused")
public class ReferenceWithoutReferentsTask extends DefaultConversationController {
    private static final int DEFAULT_NUMBER_OF_CARDS = 8;
    private static final int DEFAULT_REQUIRED_CONSECUTIVE_WINS = 3;
    private static final CardMappingType DEFAULT_CARD_MAPPING_TYPE = CardMappingType.DIRECT;
    private static final String MATCHER_ID = MATCHER.name();
    private static final String DIRECTOR_ID = DIRECTOR.name();

    private final Map<PlayerType, Boolean> readyStates = new HashMap<>();
    private final Map<PlayerType, List<Integer>> orderedListOfCardIds = new HashMap<>();
    private final int numberOfCards;
    private final int requiredConsecutiveWins;
    private final CardMappingType cardMappingType;
    private Participant matcher = null;
    private Participant director = null;
    private Vector<Participant> participants = null;
    private int consecutiveWins;
    private CardMappings cardMappings;

    public ReferenceWithoutReferentsTask(Conversation conversation) {
        super(setupGlobalConfig(conversation));

        this.numberOfCards = parseInt(showInputDialog("Please input the number of cards: ", DEFAULT_NUMBER_OF_CARDS));
        this.requiredConsecutiveWins = parseInt(showInputDialog("Please input the required number of wins: ", DEFAULT_REQUIRED_CONSECUTIVE_WINS));
        this.cardMappingType = (CardMappingType) showInputDialog(null, "Please input the card mapping type: ", "",
                JOptionPane.QUESTION_MESSAGE, null,
                CardMappingType.values(), DEFAULT_CARD_MAPPING_TYPE
        );

        setStateForNewGame();

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
    // This method is found by ConnectionToServer reflectively and run to set up the config object.
    @SuppressWarnings("WeakerAccess")
    public static Conversation setupGlobalConfig(Conversation conversation) {
        config.param_experimentID = "ReferenceWithoutReferentsTask";
        config.client_turnDisplayLimit = 2;

        return conversation;
    }

    private void setStateForNewGame() {
        this.consecutiveWins = 0;
        this.readyStates.put(MATCHER, false);
        this.readyStates.put(DIRECTOR, false);
        this.orderedListOfCardIds.clear();
        this.cardMappings = CardMappings.from(IntStream.rangeClosed(1, this.numberOfCards).boxed().collect(toList()), this.cardMappingType);
    }

    @Override
    public synchronized boolean requestParticipantJoinConversation(String participantID) {
        return (participantID.equals(DIRECTOR_ID) && director == null) || (participantID.equals(MATCHER_ID) && matcher == null);
    }

    @Override
    public synchronized void participantJoinedConversation(Participant participant) {
        if (participant.getParticipantID().equals(DIRECTOR_ID)) {
            director = participant;
        } else if (participant.getParticipantID().equals(MATCHER_ID)) {
            matcher = participant;
        }

        if (!this.experimentHasStarted && director != null && matcher != null) {
            participants = new Vector<>(asList(director, matcher));

            this.participantPartnering.createNewSubdialogue("rwr", participants);
            this.isTypingOrNotTyping.addPairWhoAreMutuallyInformedOfTyping(director, matcher);
            this.startExperiment();

            director.sendMessage(new ReferenceWithoutReferentsSetupMessage(DIRECTOR, numberOfCards));
            matcher.sendMessage(new ReferenceWithoutReferentsSetupMessage(MATCHER, numberOfCards));

            conversation.newsendInstructionToMultipleParticipants(participants, "New game started.");
        }
    }

    @Override
    public synchronized void processChatText(Participant sender, MessageChatTextFromClient messageChatTextFromClient) {
        super.processChatText(sender, messageChatTextFromClient);
        conversation.newrelayTurnToPermittedParticipants(sender, messageChatTextFromClient);
    }

    @Override
    public synchronized void processTaskMove(MessageTask messageTask, Participant participant) {
        super.processTaskMove(messageTask, participant);
        if (!(messageTask instanceof ReferenceWithoutReferentsTaskMessage)) return;

        ReferenceWithoutReferentsTaskMessage rwrMessageTask = (ReferenceWithoutReferentsTaskMessage) messageTask;
        switch (rwrMessageTask.getMessageType()) {
            case CARD_MOVE:
                ReferenceWithoutReferentsCardMoveMessage cardMoveMessage = (ReferenceWithoutReferentsCardMoveMessage) rwrMessageTask;
                List<Integer> orderedListOfCardIds = cardMoveMessage.getOrderedListOfCardIds();
                this.orderedListOfCardIds.put(cardMoveMessage.getPlayerType(), orderedListOfCardIds);
                this.conversation.newsaveAdditionalRowOfDataToSpreadsheetOfTurns("cardOrder", participant, orderedListOfCardIds.toString());
                break;
            case READY_STATE:
                ReferenceWithoutReferentsReadyStateMessage readyStateMessage = (ReferenceWithoutReferentsReadyStateMessage) rwrMessageTask;
                readyStates.put(readyStateMessage.getPlayerType(), readyStateMessage.getReadyState());

                if (readyStates.values().stream().allMatch(bool -> bool)) {
                    conversation.newsendInstructionToMultipleParticipants(participants, "Round complete.");
                    int mismatches = cardMappings.countMismatches(this.orderedListOfCardIds.get(DIRECTOR), this.orderedListOfCardIds.get(MATCHER));

                    ReferenceWithoutReferentsResetMessage resetMessage = new ReferenceWithoutReferentsResetMessage(mismatches == 0);
                    director.sendMessage(resetMessage);
                    matcher.sendMessage(resetMessage);

                    if (mismatches == 0) {
                        String message = "No mismatches. " + (++consecutiveWins) + " consecutive successful round" + (consecutiveWins > 1 ? "s" : "") + ".";
                        conversation.newsendInstructionToMultipleParticipants(participants, message);
                        if (consecutiveWins == requiredConsecutiveWins) {
                            conversation.newsendInstructionToMultipleParticipants(participants, "Game complete.");

                            setStateForNewGame();

                            conversation.newsendInstructionToMultipleParticipants(participants, "New game started.");
                        }
                    } else {
                        conversation.newsendInstructionToMultipleParticipants(participants, mismatches + " mismatch(es).");
                        consecutiveWins = 0;
                    }
                }
                break;
            default:
                throw new RuntimeException("Unexpected ReferenceWithoutReferents task message type: " + messageTask);
        }
    }
}