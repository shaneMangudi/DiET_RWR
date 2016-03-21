package diet.server.ConversationController;

import java.util.Vector;

import diet.debug.Debug;
import diet.message.MessageChatTextFromClient;
import diet.message.MessageKeypressed;
import diet.message.MessageTask;
import diet.message.MessageWYSIWYGDocumentSyncFromClientInsert;
import diet.message.MessageWYSIWYGDocumentSyncFromClientRemove;
import diet.server.Conversation;
import diet.server.Participant;
import diet.server.ConversationController.ui.JInterfaceMenuButtonsReceiverInterface;
import diet.server.ConversationController.ui.JInterfaceSinglePressButtonFIVE;
import diet.task.mazegame.Maze;
import diet.task.mazegame.MazeGameController2WAY;
import diet.task.mazegame.MazeGameLoadMazesFromJarFile;
import diet.task.mazegame.message.MessageCursorUpdate;

public class MazeGameConversationControllerMultipleDyads extends DefaultConversationController
		implements JInterfaceMenuButtonsReceiverInterface {

	Vector<MazeGameController2WAY> mazegameControllers = new Vector<>();
	JInterfaceSinglePressButtonFIVE jispb;
	Vector<Participant> participantsQueuedLLLL = new Vector<>();
	Vector<Participant> participantsQueuedRRRRR = new Vector<>();

	public MazeGameConversationControllerMultipleDyads(Conversation c) {
		super(c);
		jispb = new JInterfaceSinglePressButtonFIVE(this, "START", "STARTSHUFFLED", "EXTERNAL", "", "");
		config.param_experimentID = "DefaultMultiPartyMazeGame";

		config.login_numberOfParticipants = 2;
		config.client_MainWindow_width = 400;
		config.client_MainWindow_height = 300;
		config.client_TextEntryWindow_width = 400;
		config.client_TextEntryWindow_height = 100;
		config.client_TextEntryWindow_maximumtextlength = 500;
		config.client_TextEntryWindow_istypingtimeout = 1000;
		config.client_numberOfWindows = 1;
	}

	@Override
	public boolean requestParticipantJoinConversation(String participantID) {

		// This section is only for autologin (i.e. when programming / testing
		// the setup)
		if (DefaultConversationController.config.login_autologin) {
			if (conversation.getParticipants().findParticipantWithEmail(participantID) != null)
				return false;

			if (this.participantsQueuedLLLL.size() > this.participantsQueuedRRRRR.size()) {
				if (participantID.equals("RRRR1"))
					return true;
				if (participantID.equals("RRRR2"))
					return true;
				if (participantID.equals("RRRR3"))
					return true;
				if (participantID.equals("RRRR4"))
					return true;
				if (participantID.equals("RRRR5"))
					return true;
				if (participantID.equals("RRRR6"))
					return true;
				if (participantID.equals("RRRR7"))
					return true;
				if (participantID.equals("RRRR8"))
					return true;
				if (participantID.equals("RRRR9"))
					return true;
				if (participantID.equals("RRRR0"))
					return true;
				if (participantID.equals("RRRRX"))
					return true;
				if (participantID.equals("RRRRY"))
					return true;
			} else if (this.participantsQueuedLLLL.size() < this.participantsQueuedRRRRR.size()) {
				if (participantID.equals("LLLL1"))
					return true;
				if (participantID.equals("LLLL2"))
					return true;
				if (participantID.equals("LLLL3"))
					return true;
				if (participantID.equals("LLLL4"))
					return true;
				if (participantID.equals("LLLL5"))
					return true;
				if (participantID.equals("LLLL6"))
					return true;
				if (participantID.equals("LLLL7"))
					return true;
				if (participantID.equals("LLLL8"))
					return true;
				if (participantID.equals("LLLL9"))
					return true;
				if (participantID.equals("LLLL0"))
					return true;
				if (participantID.equals("LLLLX"))
					return true;
				if (participantID.equals("LLLLY"))
					return true;
			} else if (this.participantsQueuedLLLL.size() == this.participantsQueuedRRRRR.size()) {
				if (participantID.startsWith("LLLL") || participantID.startsWith("RRRR"))
					return true;

			}
			return false;
		}

		//

		if (participantID.equals("LLLL1"))
			return true;
		if (participantID.equals("LLLL2"))
			return true;
		if (participantID.equals("LLLL3"))
			return true;
		if (participantID.equals("LLLL4"))
			return true;
		if (participantID.equals("LLLL5"))
			return true;
		if (participantID.equals("LLLL6"))
			return true;
		if (participantID.equals("LLLL7"))
			return true;
		if (participantID.equals("LLLL8"))
			return true;
		if (participantID.equals("LLLL9"))
			return true;
		if (participantID.equals("LLLL0"))
			return true;
		if (participantID.equals("LLLLX"))
			return true;
		if (participantID.equals("LLLLY"))
			return true;
		if (participantID.equals("RRRR1"))
			return true;
		if (participantID.equals("RRRR2"))
			return true;
		if (participantID.equals("RRRR3"))
			return true;
		if (participantID.equals("RRRR4"))
			return true;
		if (participantID.equals("RRRR5"))
			return true;
		if (participantID.equals("RRRR6"))
			return true;
		if (participantID.equals("RRRR7"))
			return true;
		if (participantID.equals("RRRR8"))
			return true;
		if (participantID.equals("RRRR9"))
			return true;
		if (participantID.equals("RRRR0"))
			return true;
		if (participantID.equals("RRRRX"))
			return true;
		if (participantID.equals("RRRRY"))
			return true;

		return false;
	}

	@Override
	public synchronized void participantJoinedConversation(final Participant p) {
		super.participantJoinedConversation(p);
		conversation.changeClientInterface_disableScrolling(p);
		if (p.getParticipantID().startsWith("LLLL")) {
			this.participantsQueuedLLLL.addElement(p);
		} else if (p.getParticipantID().startsWith("RRRR")) {
			this.participantsQueuedRRRRR.addElement(p);
		}

		Conversation.printWSln("Main", "No. of LLLL:" + this.participantsQueuedLLLL.size() + "   No. of RRRR:"
				+ this.participantsQueuedRRRRR.size());
		if (this.participantsQueuedLLLL.size() != this.participantsQueuedRRRRR.size()) {
			Conversation.printWSln("Main", "UNEQUAL GROUP SIZES - DO NOT START THE EXPERIMENT----ALLP:"
					+ this.conversation.getParticipants().getAllParticipants().size());
		} else {
			Conversation.printWSln("Main", "EQUAL SIZES: OK TO START");
		}

		if (conversation.getParticipants().getAllParticipants().size() == 2) {

			// this.startmazegame();
		}

	}

	@SuppressWarnings("unchecked")
	public void startexperiment(boolean shuffle) {
		if (this.participantsQueuedLLLL.size() != this.participantsQueuedRRRRR.size()) {
			Conversation.printWSln("Main", "CANNOT START - THERE IS AN UNEQUAL NUMBER OF GROUPS");
			return;
		}

		MazeGameLoadMazesFromJarFile mglmfj = new MazeGameLoadMazesFromJarFile();
		Vector<Maze>[] bothmazes;

		for (int i = 0; i < this.participantsQueuedLLLL.size(); i++) {
			Participant pL = (Participant) participantsQueuedLLLL.elementAt(i);
			Participant pR = (Participant) participantsQueuedRRRRR.elementAt(i);

			if (!shuffle) {
				bothmazes = (Vector<Maze>[]) mglmfj.getSetOf12MazesFromJarASTEXT();
			} else {
				bothmazes = (Vector<Maze>[]) mglmfj.getSetOf12MazesFromJarASTEXTShuffled();
			}
			MazeGameController2WAY mgcNEW = new MazeGameController2WAY(conversation, bothmazes[0], bothmazes[1]);
			this.mazegameControllers.addElement(mgcNEW);
			mgcNEW.startNewMazeGame(pL, pR);

			participantPartnering.createNewSubdialogue(pL, pR);
			isTypingOrNotTyping.addPairWhoAreMutuallyInformedOfTyping(pL, pR);
			Vector<Participant> recipients = new Vector<>();
			recipients.addElement(pL);
			recipients.addElement(pR);
			conversation.newsendInstructionToMultipleParticipants(recipients, "Please start!");
		}
		this.experimentHasStarted = true;
	}

	@Override
	public void performActionTriggeredByUI(String s) {

		if (s.equalsIgnoreCase("start")) {
			this.startexperiment(false);
		}
		if (s.equalsIgnoreCase("startshuffled")) {
			this.startexperiment(true);

		}
		if (s.equalsIgnoreCase("start shuffled")) {

			this.startexperiment(true);

		}

	}

	public MazeGameController2WAY getMazeGameController(Participant p) {
		for (int i = 0; i < this.mazegameControllers.size(); i++) {
			MazeGameController2WAY mgc = (MazeGameController2WAY) this.mazegameControllers.elementAt(i);
			if (mgc.pDirector == p)
				return mgc;
			if (mgc.pMatcher == p)
				return mgc;
		}
		Conversation.printWSln("Main", "Serious error - couldn't find mazegamecontroller for " + p.getParticipantID()
				+ ", " + p.getUsername());
		Conversation.printWSln("Main", "there are " + this.mazegameControllers.size() + " mazegamecontrollers");
		return null;
	}

	@Override
	public void participantRejoinedConversation(Participant p) {

		if (Debug.debugmazegamereconnect) {
			conversation.newsendInstructionToParticipant(p,
					"Your ID (1)is registered as:" + p.getParticipantID() + "....." + p.getUsername());

		}

		MazeGameController2WAY mgcNEW = this.getMazeGameController(p);
		mgcNEW.reconnectParticipant(p);

	}

	public synchronized void processTaskMove(MessageTask mtm, Participant origin) {
		MazeGameController2WAY mgc = this.getMazeGameController(origin);

		boolean mazeIsCompleted = mgc.isCompleted();
		if (mazeIsCompleted) {
			Conversation.printWSln("Main", "Possible network lag and/or error. Experiment is receiving task moves from "
					+ origin.getUsername() + "..." + "the current maze is: " + mgc.getMazeNo());
			return;
		}
		if (mtm instanceof MessageCursorUpdate) {
			MessageCursorUpdate mcu = (MessageCursorUpdate) mtm;
			if (mcu.getMazeNo() != mgc.getMazeNo()) {
				Conversation.printWSln("Main",
						"Possible network lag and/or error. Experiment is receiving maze cursor updates from "
								+ origin.getUsername() + "...from maze number " + mcu.getMazeNo() + "..."
								+ "but the current maze is: " + mgc.getMazeNo());
				return;
			}
		}

		mazeIsCompleted = mgc.processMazeMove(mtm, origin, false);

	}

	@Override
	public synchronized void processChatText(Participant sender, MessageChatTextFromClient mct) {
		if (!this.experimentHasStarted) {
			conversation.newsendInstructionToParticipant(sender, "Please wait for the experiment to start");
			return;
		}

		this.isTypingOrNotTyping.processTurnSentByClient(sender);
		MazeGameController2WAY mgcNEW = this.getMazeGameController(sender);
		Vector additionalData = mgcNEW.getAdditionalData(sender);
		conversation.newrelayTurnToPermittedParticipants(sender, mct, additionalData);
		mgcNEW.appendToUI(sender.getUsername() + ": " + mct.getText());
	}

	// @Override
	public void processChatTextOLD(Participant sender, MessageChatTextFromClient mct) {

	}

	@Override
	public void processKeyPress(Participant sender, MessageKeypressed mkp) {
		super.processKeyPress(sender, mkp);

	}

	@Override
	public void processWYSIWYGTextInserted(Participant sender, MessageWYSIWYGDocumentSyncFromClientInsert mWYSIWYGkp) {

		// this.itnt.processDoingsByClient(sender);
	}

	@Override
	public void processWYSIWYGTextRemoved(Participant sender, MessageWYSIWYGDocumentSyncFromClientRemove mWYSIWYGkp) {
		// this.itnt.processDoingsByClient(sender);
	}

	@Override
	public Vector getAdditionalInformationForParticipant(Participant p) {
		if (!this.experimentHasStarted) {
			return new Vector();
		}
		MazeGameController2WAY mgcNEW = this.getMazeGameController(p);
		Vector additionalData = mgcNEW.getAdditionalData(p);
		return additionalData;
	}

	public static boolean showcCONGUI() {
		return true;
	}

}
