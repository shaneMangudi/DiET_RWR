/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.server.ConversationController;

import java.util.Vector;

import diet.attribval.AttribVal;
import diet.message.MessageChatTextFromClient;
import diet.message.MessageWYSIWYGDocumentSyncFromClientInsert;
import diet.message.MessageWYSIWYGDocumentSyncFromClientRemove;
import diet.server.Conversation;
import diet.server.Participant;
import diet.task.mazegame.MazeGameController2WAY;

/**
 *
 * @author gj
 */
public class EDIMazeGameBaseline extends MazeGameConversationControllerMultipleDyads {

	// CyclicRandomDutchSelfRepairInitiation crdsr = new
	// CyclicRandomDutchSelfRepairInitiation();
	// SelfRepair sr = new SelfRepair();
	// HashtableOfLong htol = new HashtableOfLong(0);

	public EDIMazeGameBaseline(Conversation c) {
		super(c);
		config.param_experimentID = "DP2015_BASELINE";
	}

	@Override
	public synchronized void processChatText(Participant sender, MessageChatTextFromClient messageChatTextFromClient) {
		if (!this.experimentHasStarted) {
			conversation.newsendInstructionToParticipant(sender, "Please wait for the experiment to start");
			return;
		}

		Participant pRecipient = (Participant) this.participantPartnering.getRecipients(sender).elementAt(0);
		long currentTurnNo = sender.getNumberOfChatMessagesProduced();
		MazeGameController2WAY mazeGameControllerForSender = this.getMazeGameController(sender);
		Vector<AttribVal> additionalData = mazeGameControllerForSender.getAdditionalData(sender);
		conversation.newrelayTurnToPermittedParticipants(sender, messageChatTextFromClient, additionalData);
		mazeGameControllerForSender.appendToUI(sender.getUsername() + ": " + messageChatTextFromClient.getText());

		if (messageChatTextFromClient.getText().equalsIgnoreCase("rj")) {
			this.getMazeGameController(sender).reconnectParticipant(sender);
			conversation.newsendInstructionToParticipant(sender, "RESETTING");
		}

	}

	@Override
	public void processWYSIWYGTextInserted(Participant sender, MessageWYSIWYGDocumentSyncFromClientInsert mWYSIWYGkp) {
		this.updateInfoAsItIsTyped(sender, mWYSIWYGkp.getAllTextInWindow(), false);
	}

	@Override
	public void processWYSIWYGTextRemoved(Participant sender, MessageWYSIWYGDocumentSyncFromClientRemove mWYSIWYGkp) {
		this.updateInfoAsItIsTyped(sender, mWYSIWYGkp.getAllTextInWindow(), true);
	}

	public synchronized void updateInfoAsItIsTyped(Participant sender, String textInBoxOLD, boolean updateISREMOVE) {
		try {
			// textInBox = "PRESET"+textInBoxOLD.replaceAll("\n",
			// "").replace("\n", "").replaceAll("\r\n", "").replaceAll("\r",
			// "")+"ENDSET";

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
