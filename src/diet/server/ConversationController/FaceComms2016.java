/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.server.ConversationController;

import diet.attribval.AttribVal;
import diet.message.MessageChatTextFromClient;
import diet.message.MessageKeypressed;
import diet.message.MessageWYSIWYGDocumentSyncFromClientInsert;
import diet.message.MessageWYSIWYGDocumentSyncFromClientRemove;
import diet.server.Conversation;
import diet.server.Participant;
import diet.task.FaceComms.FaceCommsTaskController;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author gj
 */
public class FaceComms2016 extends DefaultDyadicConversationController {

	FaceCommsTaskController fctc;

	public FaceComms2016(Conversation c) {
		super(c);
		config.client_MainWindow_width = 800;
		fctc = new FaceCommsTaskController(this);
	}

	@Override
	public synchronized void processChatText(Participant sender, MessageChatTextFromClient mct) {
		super.processChatText(sender, mct); // To change body of generated
											// methods, choose Tools |
											// Templates.
		fctc.processChatText(sender, mct.getText());

	}

	@Override
	public synchronized void participantJoinedConversation(Participant p) {

		super.participantJoinedConversation(p);
		if (conversation.getParticipants().getAllParticipants().size() == 2) {
			Participant pA = (Participant) conversation.getParticipants().getAllParticipants().elementAt(0);
			Participant pB = (Participant) conversation.getParticipants().getAllParticipants().elementAt(1);
			fctc.startTask(pA, pB);
		}

	}

	@Override
	public Vector getAdditionalInformationForParticipant(Participant p) {
		// return super.getAdditionalInformationForParticipant(p); //To change
		// body of generated methods, choose Tools | Templates.

		Vector fctcadditionalvalues = this.fctc.getAdditionalValues(p);

		Vector spoof = this.getAdditionalInfoAboutSppofTyping(p);
		fctcadditionalvalues.addAll(spoof);

		fctcadditionalvalues.addAll(super.getAdditionalInformationForParticipant(p));

		return fctcadditionalvalues;
	}

	@Override
	public void processKeyPress(Participant sender, MessageKeypressed mkp) {
		super.processKeyPress(sender, mkp); // To change body of generated
											// methods, choose Tools |
											// Templates.

		if (!mkp.isENTER()) {
			this.determineIfToDoSpoofTyping(sender);
		}

	}

	@Override
	public void processWYSIWYGTextRemoved(Participant sender, MessageWYSIWYGDocumentSyncFromClientRemove mWYSIWYGkp) {
		isTypingOrNotTyping.processDoingsByClient(sender);
		this.determineIfToDoSpoofTyping(sender);
		// doSpoofTyping(sender);

	}

	@Override
	public void processWYSIWYGTextInserted(Participant sender, MessageWYSIWYGDocumentSyncFromClientInsert mWYSIWYGkp) {

		if (mWYSIWYGkp.getTextTyped().equalsIgnoreCase("\n")) {
			System.err.println("Determined it was an ENTER");
		} else {
			isTypingOrNotTyping.processDoingsByClient(sender);
			this.determineIfToDoSpoofTyping(sender);
		}

		// doSpoofTyping(sender);
	}

	Random r = new Random();
	boolean pADelay = false;
	boolean pBDelay = true;

	public void determineIfToDoSpoofTyping(Participant p) {
		if (p == this.fctc.pA && pADelay == true)
			doSpoofTyping(p);
		else if (p == this.fctc.pB && pBDelay == true)
			doSpoofTyping(p);
	}

	public void doSpoofTyping(Participant sender) {
		isTypingOrNotTyping.addSpoofTypingInfo(sender, new Date().getTime() + 500);
		isTypingOrNotTyping.addSpoofTypingInfo(sender, new Date().getTime() + 1000);
		isTypingOrNotTyping.addSpoofTypingInfo(sender, new Date().getTime() + 1500);
		isTypingOrNotTyping.addSpoofTypingInfo(sender, new Date().getTime() + 2000);
		isTypingOrNotTyping.addSpoofTypingInfo(sender, new Date().getTime() + 2500);
		isTypingOrNotTyping.addSpoofTypingInfo(sender, new Date().getTime() + 3000);

	}

	public Vector getAdditionalInfoAboutSppofTyping(Participant p) {
		Vector returnVal = new Vector();
		if (p == this.fctc.pA) {
			if (this.pADelay) {
				AttribVal av = new AttribVal("S", "D");
				returnVal.addElement(av);
			} else {
				AttribVal av = new AttribVal("S", "N");
				returnVal.addElement(av);
			}
			if (this.pBDelay) {
				AttribVal av = new AttribVal("O", "D");
				returnVal.addElement(av);
			} else {
				AttribVal av = new AttribVal("O", "N");
				returnVal.addElement(av);
			}
		}
		if (p == this.fctc.pB) {
			if (this.pBDelay) {
				AttribVal av = new AttribVal("S", "D");
				returnVal.addElement(av);
			} else {
				AttribVal av = new AttribVal("S", "N");
				returnVal.addElement(av);
			}
			if (this.pADelay) {
				AttribVal av = new AttribVal("O", "D");
				returnVal.addElement(av);
			} else {
				AttribVal av = new AttribVal("O", "N");
				returnVal.addElement(av);
			}
		}
		return returnVal;
	}

	public void changeInterfaceDelays() {
		pADelay = r.nextBoolean();
		pBDelay = r.nextBoolean();
		System.err.println("CHANGINGDEL-");
	}

}
