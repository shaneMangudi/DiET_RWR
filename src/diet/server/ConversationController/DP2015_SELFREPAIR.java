/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.server.ConversationController;

import diet.message.MessageChatTextFromClient;
import diet.message.MessageWYSIWYGDocumentSyncFromClientInsert;
import diet.message.MessageWYSIWYGDocumentSyncFromClientRemove;
import diet.server.Conversation;
import diet.server.Participant;
import diet.task.mazegame.MazeGameController2WAY;
import diet.textmanipulationmodules.CyclicRandomTextGenerators.CyclicRandomDutchSelfRepairInitiation;
import diet.textmanipulationmodules.Selfrepairgeneration.SelfRepair;
import diet.utils.HashtableOfLong;
import static java.awt.SystemColor.info;
import java.util.Vector;

/**
 *
 * @author gj
 */
public class DP2015_SELFREPAIR extends MazeGameConversationControllerMultipleDyads {

	CyclicRandomDutchSelfRepairInitiation crdsr = new CyclicRandomDutchSelfRepairInitiation();
	SelfRepair sr = new SelfRepair();
	HashtableOfLong htol = new HashtableOfLong(0);

	public DP2015_SELFREPAIR(Conversation c) {
		super(c);
		config.param_experimentID = "DP2015_SELFREPAIR";
	}

	@Override
	public synchronized void processChatText(Participant sender, MessageChatTextFromClient mct) {

		if (!this.experimentHasStarted) {
			conversation.newsendInstructionToParticipant(sender, "Please wait for the experiment to start");
			return;
		}

		MazeGameController2WAY mgccc = this.getMazeGameController(sender);
		Participant pRecipient = (Participant) this.participantPartnering.getRecipients(sender).elementAt(0);

		long mostRecentIntervention = htol.get(sender);
		long currentTurnNo = sender.getNumberOfChatMessagesProduced();
		if (currentTurnNo - mostRecentIntervention < 2) {

			System.err.println("Not even trying to look for intervention - 5 turns haven't elapsed yet");
			Conversation.printWSln("Main", "Not triggering delete because delete was in previous turn");
			this.isTypingOrNotTyping.processTurnSentByClient(sender);
			MazeGameController2WAY mgcNEW = this.getMazeGameController(sender);
			Vector additionalData = mgcNEW.getAdditionalData(sender);
			conversation.newrelayTurnToPermittedParticipants(sender, mct, additionalData);
			mgcNEW.appendToUI(sender.getUsername() + ": " + mct.getText());
			sr.setNewTurn(sender);
			return;
		}
		String[] modifiedTurn = sr.getFirstPart_SecondPart_SummaryWithErrorCheck(sender);
		if (modifiedTurn[0] == null || modifiedTurn[1] == null) {
			System.err.println("Couldnt generate delete...so is relaying");
			Conversation.printWSln("Main", "Couldnt generate delete...so is relaying");
			this.isTypingOrNotTyping.processTurnSentByClient(sender);
			MazeGameController2WAY mgcNEW = this.getMazeGameController(sender);
			Vector additionalData = mgcNEW.getAdditionalData(sender);
			conversation.newrelayTurnToPermittedParticipants(sender, mct, additionalData);
			mgcNEW.appendToUI(sender.getUsername() + ": " + mct.getText());
			sr.setNewTurn(sender);
			return;

		} else {
			String repairInitiation = crdsr.getNext(sender);
			String fullChangedTurn = modifiedTurn[0] + " " + repairInitiation + " " + modifiedTurn[1];
			/// c.sendArtificialMazeGameTurnFromApparentOriginToRecipientWithEnforcedTextColour(sender.getUsername(),
			/// pRecipient, fullChangedTurn, 0, info, 1);

			this.isTypingOrNotTyping.processTurnSentByClient(sender);
			MazeGameController2WAY mgcNEW = this.getMazeGameController(sender);
			Vector additionalData = mgcNEW.getAdditionalData(sender);

			conversation.newsendArtificialTurnFromApparentOrigin(sender, pRecipient, fullChangedTurn);

			mgcNEW.appendToUI("*" + sender.getUsername() + ": " + mct.getText());
			mgcNEW.appendToUI("**" + sender.getUsername() + ": " + fullChangedTurn);
			sr.setNewTurn(sender);

			isTypingOrNotTyping.processTurnSentByClient(sender);
			mgccc.appendToUI("FAKEREPAIR: " + sender.getUsername() + ": " + mct.getText());
			sr.setNewTurn(sender);
			this.htol.put(sender, sender.getNumberOfChatMessagesProduced());
			return;

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
		String textInBox = "";
		try {
			// textInBox = "PRESET"+textInBoxOLD.replaceAll("\n",
			// "").replace("\n", "").replaceAll("\r\n", "").replaceAll("\r",
			// "")+"ENDSET";
			textInBox = textInBoxOLD.replaceAll("\n", "").replace("\n", "").replaceAll("\r\n", "").replaceAll("\r", "");
			this.sr.addRevision(sender, textInBox);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
