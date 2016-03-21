package diet.server.ConversationController;

import java.awt.Color;
import java.io.File;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import javax.swing.text.MutableAttributeSet;

///THIS IS A MAIN CLASS
import diet.client.StyledDocumentStyleSettings;
import diet.message.MessageChatTextFromClient;
import diet.message.MessageClientInterfaceEvent;
import diet.message.MessageClientSetupParameters;
import diet.message.MessageClientSetupParametersWithSendButtonAndTextEntryWidthByHeight;
import diet.message.MessageKeypressed;
import diet.message.MessagePopupResponseFromClient;
import diet.message.MessageTask;
import diet.message.MessageWYSIWYGDocumentSyncFromClientInsert;
import diet.message.MessageWYSIWYGDocumentSyncFromClientRemove;
import diet.server.Configuration;
import diet.server.Conversation;
import diet.server.Participant;
import diet.server.IsTypingController.IsTypingOrNotTyping;
import diet.server.ParticipantPartnering.ParticipantPartnering;
import diet.server.io.IntelligentIO;
import diet.server.stylemanager.StyleManager;
import diet.task.TaskControllerInterface;
import diet.textmanipulationmodules.CyclicRandomTextGenerators.CyclicRandomParticipantIDGeneratorGROOP;
import diet.textmanipulationmodules.CyclicRandomTextGenerators.IRandomParticipantIDGenerator;

/**
 * This is the main (preferably only) class that should be changed when creating
 * a new experimental design. Every message sent from the clients passes through
 * the methods provided by this class. This includes each keypress, each turn
 * typed and sent.
 * <p>
 * On receiving a message, this class determines what is to be done with the
 * message. In normal operation it relays the messages to the other
 * participants. However, to create interventions this behaviour can be replaced
 * with commands to modify the turn or create artificial turns.
 *
 * <p>
 * Most of the methods of this class are called by
 * {@link diet.server.Conversation}. It is expected that the methods of this
 * class will do necessary detection of targets, transforming of turns, and on
 * this basis call methods in {@link diet.server.Conversation} to send the
 * artificially created messages to the participants.
 *
 *
 * @author user
 */
public abstract class DefaultConversationController {

	public static Configuration config = new Configuration();

	public static IRandomParticipantIDGenerator autologinParticipantIDGenerator = new CyclicRandomParticipantIDGeneratorGROOP();

	public Conversation conversation;
	public IsTypingOrNotTyping isTypingOrNotTyping; // = new
													// IsTypingOrNotTyping(this,
	// param_isTypingTimeOut);
	public ParticipantPartnering participantPartnering;// = new
														// ParticipantPartnering(c);
	public StyleManager styleManager = new StyleManager(this);
	public TaskControllerInterface taskControllerInterface;
	public Random random = new Random(new Date().getTime());
	public boolean experimentHasStarted = false;

	public DefaultConversationController(Conversation c) {
		this.conversation = c;
		c.convIO = new IntelligentIO(c,
				System.getProperty("user.dir") + File.separator + "data" + File.separator + "saved experimental data",
				this.config.param_experimentID);
		participantPartnering = new ParticipantPartnering(c);
		isTypingOrNotTyping = new IsTypingOrNotTyping(this, config.client_TextEntryWindow_istypingtimeout);

	}

	public static boolean showcCONGUI() {
		return true;

	}

	public Conversation getConversation() {
		return conversation;
	}

	public StyleManager getStyleManager() {
		return styleManager;
	}

	public boolean requestParticipantJoinConversation(String participantID) {
		return true;
	}
	
	public void setupGlobalConfig() {
		
	}

	public MessageClientSetupParameters processRequestForInitialChatToolSettings() {
		boolean alignmentIsVertical = true;
		boolean deletesPermitted = true;

		Color background = new Color(config.client_backgroundcolour_rgb[0], config.client_backgroundcolour_rgb[1],
				config.client_backgroundcolour_rgb[2]);
		Vector othersColors = new Vector();
		Color selfColor = Color.black;
		StyledDocumentStyleSettings styleddocsettings;
		int ownWindowNumber = 0;
		try {
			MutableAttributeSet masSELF = this.getStyleManager().getStyleForSelf();
			styleddocsettings = new StyledDocumentStyleSettings(background, selfColor, masSELF);
			return new MessageClientSetupParametersWithSendButtonAndTextEntryWidthByHeight("server", "servername2",
					this.config.client_MainWindow_width, this.config.client_MainWindow_height, alignmentIsVertical,
					this.config.client_numberOfWindows, ownWindowNumber,
					false, true, "Setting up", true, this.config.client_TextEntryWindow_width,
					this.config.client_TextEntryWindow_height, this.config.client_TextEntryWindow_maximumtextlength,
					styleddocsettings, this.getClass().getName());
		} catch (Exception e) {
			Conversation.printWSln("Main",
					"Could not find parameters for chat tool client interface...attempting to use defaults");

			e.printStackTrace();

		}
		return null;
	}

	public void participantJoinedConversation(Participant p) {

	}

	public void participantRejoinedConversation(Participant p) {
	}

	/**
	 *
	 * This method is invoked by {@link diet.server.Conversation} whenever a
	 * participant presses a key while typing text in their chat window. The
	 * default behaviour is to inform the other participants that the
	 * participant is typing.
	 *
	 * @param sender
	 *            Participant who has pressed a key
	 * @param mkp
	 *            message containing the keypress information
	 */
	public void processKeyPress(Participant sender, MessageKeypressed mkp) {
		if (!mkp.isENTER()) {
			this.isTypingOrNotTyping.processDoingsByClient(sender);
		}
	}

	/**
	 * This method is invoked by {@link diet.server.Conversation} whenever the
	 * text in a participant's text entry window changes by having one or more
	 * characters inserted.
	 *
	 * @param sender
	 *            participant who inserted text
	 * @param mWYSIWYGkp
	 *            message containing information about the text inserted
	 */
	public void processWYSIWYGTextInserted(Participant sender, MessageWYSIWYGDocumentSyncFromClientInsert mWYSIWYGkp) {
	}

	/**
	 * This method is invoked by {@link diet.server.Conversation} whenever the
	 * text in a participant's text entry window changes by having one or more
	 * characters deleted. This is separate from Keypresses (a user might delete
	 * a whole segment of text by highlighting the text and pressing delete
	 * once).
	 *
	 * @param sender
	 *            participant who deleted text
	 * @param mWYSIWYGkp
	 *            message containing information about the text deleted
	 */
	public void processWYSIWYGTextRemoved(Participant sender, MessageWYSIWYGDocumentSyncFromClientRemove mWYSIWYGkp) {
	}

	/**
	 * This method is invoked by {@link diet.server.Conversation} whenever a
	 * participant has typed a message The default behaviour is to relay the
	 * message to the other participants. This is the main locus for programming
	 * interventions in the chat tool.
	 *
	 * @param sender
	 *            the participant who typed the turn
	 * @param mct
	 *            the message typed by the participant
	 */
	public void processChatText(Participant sender, MessageChatTextFromClient mct) {
		if (!this.experimentHasStarted) {
			conversation.newsendInstructionToParticipant(sender, "Please wait until the experiment has started");
		}

		isTypingOrNotTyping.processTurnSentByClient(sender);
		if (config.debug_allow_client_to_send_debug_commands) {
			cmnd(sender, mct.getText());
		}
	}

	public void processTaskMove(MessageTask mt, Participant p) {
		if (taskControllerInterface != null) {
			taskControllerInterface.processTaskMove(mt, p);
		}
	}

	public void closeDown() {
		if (taskControllerInterface != null) {
			taskControllerInterface.closeDown();
		}
	}

	public void processPopupResponse(Participant origin, MessagePopupResponseFromClient mpr) {
	}

	public void processClientEvent(Participant origin, MessageClientInterfaceEvent mce) {

	}

	public void startExperiment() {
		this.experimentHasStarted = true;
	}

	public Vector getAdditionalInformationForParticipant(Participant p) {
		return new Vector();
	}

	public void cmnd(String command) {
		if (command.equalsIgnoreCase("////d")) {
			Vector v = conversation.getParticipants().getAllParticipants();
			Participant p = (Participant) v.elementAt(0);
			p.getConnection().dispose();
		}
	}

	public void cmnd(Participant p, String command) {
		if (command.equalsIgnoreCase("////d")) {
			if (p != null) {
				p.getConnection().dispose();
			}
		}
	}

	public void typingtimeout(int n) {
		this.isTypingOrNotTyping.setInactivityThreshold(n);
	}

}
