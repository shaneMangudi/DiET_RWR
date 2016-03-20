package diet.server.ConversationController.obsoltebucket;

import diet.message.MessageChatTextFromClient;
import diet.message.MessageKeypressed;
import diet.message.MessageWYSIWYGDocumentSyncFromClientInsert;
import diet.message.MessageWYSIWYGDocumentSyncFromClientRemove;

import diet.server.Conversation;

import diet.server.Participant;
import diet.task.stimuliset.ConfidenceTaskController;
import diet.task.stimuliset.ConfidenceTaskControllerSequenceSet;

public class CCCONFIDENCE2WAY extends CCCONFIDENCE {

	public CCCONFIDENCE2WAY(Conversation c) {
		super(c);
	}

	ConfidenceTaskControllerSequenceSet ctcss = new ConfidenceTaskControllerSequenceSet();
	ConfidenceTaskController ctc = new ConfidenceTaskController(this, ctcss.ctcseq1, "ctc", "", 99999999, 100);

	public static boolean showcCONGUI() {
		return false;
	}

	@Override
	public void processChatText(Participant sender, MessageChatTextFromClient mct) {

	}

	@Override
	public void processKeyPress(Participant sender, MessageKeypressed mkp) {
		// c.informIsTypingToAllowedParticipants(sender);//,mkp);
		conversation.newsaveClientKeypressToFile(sender, mkp);

	}

	@Override
	public void processWYSIWYGTextInserted(Participant sender, MessageWYSIWYGDocumentSyncFromClientInsert mWYSIWYGkp) {

	}

	public void setSTime(long stime) {
		this.ctc.setSTime(stime);
	}

	public void setFTime(long fixationtime) {
		this.ctc.setFTime(fixationtime);
	}

	public void setBTime(long backgroundtime) {
		this.ctc.setBTime(backgroundtime);
	}

	@Override
	public void processWYSIWYGTextRemoved(Participant sender, MessageWYSIWYGDocumentSyncFromClientRemove mWYSIWYGkp) {
		// c.relayWYSIWYGTextRemovedToAllowedParticipants(sender,mWYSIWYGkp);
		// turnBeingConstructed.remove(mWYSIWYGkp.getOffset(),mWYSIWYGkp.getLength(),mWYSIWYGkp.getTimeStamp().getTime());
		// chOut.addMessage(sender,mWYSIWYGkp);
	}

	@Override
	public void participantJoinedConversation(Participant p) {
		super.participantJoinedConversation(p);
		ctc.participantJoinedConversation(p);
		if (conversation.getParticipants().getAllParticipants().size() > 1)
			ctc.sendStartMessageToParticipants();
		// System.exit(-4);
	}

	@Override
	public void participantRejoinedConversation(Participant p) {
		super.participantRejoinedConversation(p);
		ctc.participantRejoinedConversation(p);
	}

}
