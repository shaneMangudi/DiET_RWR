package diet.server.ConversationController;

import diet.message.MessageChatTextFromClient;
import diet.message.MessageTask;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsCardMoveMessage;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsReadyStateMessage;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsResetMessage;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsSetupMessage;
import diet.message.referenceWithoutReferentsTask.ReferenceWithoutReferentsTaskMessage;
import diet.server.Conversation;
import diet.server.Participant;
import diet.textmanipulationmodules.CyclicRandomTextGenerators.IRandomParticipantIDGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.stream.IntStream;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class ReferenceWithoutReferentsTask extends DefaultConversationController {
    private static final int NUMBER_OF_CARDS = 8;
    private static final int REQUIRED_CONSECUTIVE_WINS = 3;
    private static final boolean CARDS_RANDOM_MAPPING = false;
    private static final String MATCHER_ID = PlayerType.MATCHER.name();
    private static final String DIRECTOR_ID = PlayerType.DIRECTOR.name();
    private final MismatchCounter mismatchCounter;
    private Participant matcher = null;
    private Participant director = null;
    private Vector<Participant> participants = null;
    private int consecutiveWins = 0;
    private Map<PlayerType, Boolean> readyStates = new HashMap<>();
    private Map<PlayerType, List<Integer>> orderedListOfCardIds = new HashMap<>();

    public ReferenceWithoutReferentsTask(Conversation conversation) {
        super(setupGlobalConfig(conversation));

        List<Integer> ids = IntStream.rangeClosed(1, NUMBER_OF_CARDS).boxed().collect(toList());
        if (CARDS_RANDOM_MAPPING) {
            List<Integer> copyOfIds = new ArrayList<>(ids);
            Collections.shuffle(copyOfIds, new Random());
            mismatchCounter = new MismatchCounter(ids, copyOfIds);
        } else {
            mismatchCounter = new MismatchCounter(ids, ids);
        }

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
    private static Conversation setupGlobalConfig(Conversation conversation) {
        config.param_experimentID = "ReferenceWithoutReferentsTask";
        config.client_turnDisplayLimit = 2;

        return conversation;
    }

    @Override
    public synchronized boolean requestParticipantJoinConversation(String participantID) {
        return (participantID.equals(DIRECTOR_ID) && director == null) || (participantID.equals(MATCHER_ID) && matcher == null);
    }

    @Override
    public synchronized void participantJoinedConversation(Participant participant) {
        if (participant.getParticipantID().equals(DIRECTOR_ID)) {
            director = participant;
            readyStates.put(PlayerType.DIRECTOR, false);
        } else if (participant.getParticipantID().equals(MATCHER_ID)) {
            matcher = participant;
            readyStates.put(PlayerType.MATCHER, false);
        }

        if (!this.experimentHasStarted && director != null && matcher != null) {
            participants = new Vector<>(asList(director, matcher));

            this.participantPartnering.createNewSubdialogue("rwr", participants);
            this.isTypingOrNotTyping.addPairWhoAreMutuallyInformedOfTyping(director, matcher);
            this.startExperiment();

            director.sendMessage(new ReferenceWithoutReferentsSetupMessage(PlayerType.DIRECTOR, NUMBER_OF_CARDS));
            matcher.sendMessage(new ReferenceWithoutReferentsSetupMessage(PlayerType.MATCHER, NUMBER_OF_CARDS));

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
            // TODO: Save task move information to the logs
            case CARD_MOVE:
                ReferenceWithoutReferentsCardMoveMessage cardMoveMessage = (ReferenceWithoutReferentsCardMoveMessage) rwrMessageTask;
                orderedListOfCardIds.put(cardMoveMessage.getPlayerType(), cardMoveMessage.getOrderedListOfCardIds());
                System.out.println(cardMoveMessage.getPlayerType() + ": " + cardMoveMessage.getOrderedListOfCardIds());
                break;
            case READY_STATE:
                ReferenceWithoutReferentsReadyStateMessage readyStateMessage = (ReferenceWithoutReferentsReadyStateMessage) rwrMessageTask;
                readyStates.put(readyStateMessage.getPlayerType(), readyStateMessage.getReadyState());

                if (readyStates.values().stream().allMatch(bool -> bool)) {
                    director.sendMessage(new ReferenceWithoutReferentsResetMessage());
                    matcher.sendMessage(new ReferenceWithoutReferentsResetMessage());

                    conversation.newsendInstructionToMultipleParticipants(participants, "Turn complete.");
                    int mismatches = countMismatchedCards();
                    if (mismatches == 0) {
                        ++consecutiveWins;
                        conversation.newsendInstructionToMultipleParticipants(participants, "No mismatches. " + consecutiveWins + " consecutive successful turn(s).");
                        if (consecutiveWins == REQUIRED_CONSECUTIVE_WINS) {
                            conversation.newsendInstructionToMultipleParticipants(participants, "Game complete.");

                            consecutiveWins = 0;
                            readyStates.put(PlayerType.MATCHER, false);
                            readyStates.put(PlayerType.DIRECTOR, false);
                            orderedListOfCardIds.clear();

                            conversation.newsendInstructionToMultipleParticipants(participants, "New game started.");
                        }
                    } else {
                        conversation.newsendInstructionToMultipleParticipants(participants, mismatches + " mismatched. Turn failed.");
                        consecutiveWins = 0;
                    }
                }
                break;
            default:
                throw new RuntimeException("Unexpected ReferenceWithoutReferents task message type: " + messageTask);
        }
    }

    private int countMismatchedCards() {
        List<Integer> director = orderedListOfCardIds.get(PlayerType.DIRECTOR);
        List<Integer> matcher = orderedListOfCardIds.get(PlayerType.MATCHER);
        return mismatchCounter.of(director, matcher);
    }

    public enum PlayerType {
        DIRECTOR,
        MATCHER
    }

    private static class MismatchCounter {
        private final Map<Integer, Integer> idMappings;

        MismatchCounter(List<Integer> keys, List<Integer> values) {
            this(zip(keys, values));
        }

        MismatchCounter(Map<Integer, Integer> idMappings) {
            this.idMappings = idMappings;
        }

        private static Map<Integer, Integer> zip(List<Integer> keys, List<Integer> values) {
            Map<Integer, Integer> idMappings = new HashMap<>();
            for (int i = 0; i < NUMBER_OF_CARDS; ++i) {
                idMappings.put(keys.get(i), values.get(i));
            }
            return idMappings;
        }

        int of(List<Integer> director, List<Integer> matcher) {
            int mismatches = 0;
            for (int i = 0; i < NUMBER_OF_CARDS; ++i) {
                if (!idMappings.get(director.get(i)).equals(matcher.get(i))) {
                    ++mismatches;
                }
            }
            return mismatches;
        }
    }
}