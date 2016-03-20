package diet.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Vector;

import diet.message.Message;

import diet.message.MessageChatTextFromClient;
import diet.message.MessageChatTextToClient;
import diet.message.MessageClientLogon;
import diet.message.MessageDummy;
import diet.message.MessageKeypressed;
import diet.message.MessagePopupClientLogonEmailRequest;
import diet.message.MessagePopupClientLogonUsernameRequest;
import diet.message.MessageWYSIWYGDocumentSyncFromClientInsert;
import diet.message.MessageWYSIWYGDocumentSyncFromClientRemove;
import diet.server.ConversationController.DefaultConversationController;
import diet.server.ConversationController.ui.CustomDialog;
import diet.serverclientconsistencycheck.ServerClientConsistency;

/**
 * Deals with low-level networking between the server and client.
 * 
 * @author user
 */
public class ParticipantConnection extends Thread {

	private Vector v = new Vector();
	private Participant particip;
	private boolean isLoggedIn = false;
	public long timeOfLastTyping = 0;
	private String textEntryWindow = "";

	public long numberOfChatTurnsReceivedFromClient = 0;

	ObjectInputStream in;
	ObjectOutputStream out;
	private ExperimentManager expM;
	ConnectionToClientSendLockFlushingAndPeriodicResetting ctcSL;

	boolean mostRecentErrorhasBeenFlagged = false;
	long timeOfMostRecentmessageFromClient = 0;
	long timeOfMostRecentmChatTextFromClient = 0;

	public ParticipantConnection(ObjectInputStream instream, ObjectOutputStream outstream, ExperimentManager exM) {
		Conversation.printWSln("Main", "Creating new ParticipantConnection");
		expM = exM;
		in = instream;
		ctcSL = new ConnectionToClientSendLockFlushingAndPeriodicResetting(outstream);
		out = outstream;
	}

	/**
	 * Associates ParticipantConnection with Participant
	 * 
	 * @param p
	 *            Participant
	 */
	public void assignToParticipant(Participant p) {
		particip = p;
		this.mostRecentErrorhasBeenFlagged = false;

	}

	/**
	 * Sends message to Client
	 * 
	 * @param m
	 *            Message to be sent
	 */
	public void send(Message m) {

		try {
			// System.out.println("Trying to send Message");
			try {
				m.setTimeOfSending();
			} catch (Exception e) {
				if (particip != null)
					System.out.println("ERROR " + particip.getUsername());
				e.printStackTrace();

			}
			ctcSL.sendMessage(m);
			// System.out.println("Sent object");
			try {
				if (particip != null) {
					if (particip.getConversation() != null) {
						if (m instanceof MessageChatTextToClient)
							this.particip.getConversation().getConvIO().saveMessage(m);

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				Conversation.saveErr(e);
			}

		} catch (Exception e) {
			System.out.println("ClientThreadSendError" + e.getMessage());
			setConnected(false);
		}
	}

	public long getTimeOfLastMessageSentToServer() {
		return timeOfMostRecentmessageFromClient;
	}

	public long getTimeOfLastChatTextSentToServer() {
		return timeOfMostRecentmChatTextFromClient;
	}

	public long getNumberOfChatMessagesProduced() {
		return this.numberOfChatTurnsReceivedFromClient;
	}

	public MessageClientLogon mclContainingInternetSettings = null;

	public String getClientIPAddress() {
		if (this.mclContainingInternetSettings != null)
			return this.mclContainingInternetSettings.getLocal_ina().getHostAddress();
		return "not.yet.set";

	}

	/**
	 * Appends incoming message to Vector of incoming messages. Timestamps
	 * incoming message. If message indicates a keypress, updates the isTyping
	 * timing information.
	 * 
	 * @param o
	 *            incoming message
	 */
	private synchronized void appendand(Object o) {

		this.timeOfMostRecentmessageFromClient = new Date().getTime();

		Message m = (Message) o;
		m.setTimeOfReceipt();

		try {
			if (o instanceof MessageChatTextFromClient) {

				numberOfChatTurnsReceivedFromClient++;
				timeOfMostRecentmChatTextFromClient = new Date().getTime();
				if (diet.debug.Debug.debugtimers) {
					((MessageChatTextFromClient) o).saveTime("serverconnection.received");
				}
			}
		} catch (Exception e) {

		}

		if (o instanceof MessageKeypressed || o instanceof MessageWYSIWYGDocumentSyncFromClientInsert
				|| o instanceof MessageWYSIWYGDocumentSyncFromClientRemove || o instanceof MessageKeypressed) {
			timeOfLastTyping = new Date().getTime();
		}
		if (o instanceof MessageKeypressed) {
			this.textEntryWindow = ((MessageKeypressed) o).getContentsOfTextEntryWindow();
		}

		try {

			this.particip.getConversation().getParticipants().addMessageToIncomingQueue(m);

		} catch (Exception e) {
			Conversation.printWSln("Main", "Some Messages not relayed due to participant connecting");
			e.printStackTrace();
		}
		// v.addElement(o);
	}

	public String getTextEntryWindow() {
		return textEntryWindow;
	}

	public long getTimeOfLastTyping() {
		return this.timeOfLastTyping;
	}

	/**
	 * Co-ordinates between ExperimentManager and the client to establish
	 * Participant's email and login details.
	 * 
	 * @return false if Participant email and login can't be established
	 * @throws java.lang.Exception
	 */
	public boolean verifyLoginDetailsAndActivate() throws Exception {
		String participantIDgiven = "";
		String usernamegiven = "";
		send(new MessagePopupClientLogonEmailRequest("Please enter your Participant ID", "",
				expM.getMinParticipantIDLength()));
		Object o = in.readObject();
		if (o instanceof MessageClientLogon) {
			MessageClientLogon m = (MessageClientLogon) o;
			this.mclContainingInternetSettings = m;

			double scs = m.getServerClientConsistencyID();
			if (ServerClientConsistency.testForConsistency(scs)) {
				System.out.println(
						"Client has tried logging in...and server and client are using the same version of the software");
			} else {
				CustomDialog.showDialog(
						"A client is attempting to connect to the server, but the server and the client are not using the same version of the software!\n"
								+ "On the server, the version ID is: " + ServerClientConsistency.getVersionID() + ".\n"
								+ "On the client, the version ID is: " + scs + ".\n"
								+ "...this WILL PROBABLY LEAD TO SOME HORRIFICALLY BIZARRE ERRORS!!!!!\n"
								+ "You can proceed, but it is highly recommended that you download the latest version on ALL computers!");
			}

			participantIDgiven = m.getEmail();
			System.err.println("HERE");
			Object userNP = expM
					.findPrevioususernameOrParticipantFromPreviousLoginsAndCheckParticipantIDISOK(participantIDgiven);

			System.err.println("HERE2");
			while (userNP instanceof Boolean) {
				send(new MessagePopupClientLogonEmailRequest("ID is invalid..please check for typos and try again", "",
						expM.getMinParticipantIDLength()));
				o = in.readObject();
				if (o instanceof MessageClientLogon) {
					m = (MessageClientLogon) o;
					this.mclContainingInternetSettings = m;
					participantIDgiven = m.getEmail();
					Conversation.printWSln("Main", "ParticipantID " + participantIDgiven + " rejected as invalid\n"
							+ "This could be because:\n(1) The experimental setup requires specific participantIDs\n"
							+ "(2) The experiment is already running on the server, and there are already sufficient participants logged in.\n");
					userNP = expM.findPrevioususernameOrParticipantFromPreviousLoginsAndCheckParticipantIDISOK(
							participantIDgiven);
				}
			}

			if (userNP instanceof Participant) {
				System.err.println("HERE3a");
				send(new MessagePopupClientLogonUsernameRequest("You're already logged in",
						((Participant) userNP).getUsername(), false));
				System.err.println("HERE4a");
				o = in.readObject();
				System.err.println("HERE5a");
				boolean reactivated = expM.reactivateParticipant(this, (Participant) userNP);
				System.err.println("HERE6");
				if (reactivated) {
					this.setConnected(true);
					return true;
				}

			} else if (userNP instanceof String || userNP == null) {
				String suggestedUsername = "";
				if (userNP != null)
					suggestedUsername = (String) userNP;

				boolean usernameIsOK = false;
				String promptMessage = "Please enter your Username (At least 4 characters)";
				System.err.println("HERE3b" + participantIDgiven);
				if (userNP != null)
					System.err.println("HERE3b" + participantIDgiven + userNP);
				while (!usernameIsOK) {
					System.err.println("HERE3c" + participantIDgiven);
					send(new MessagePopupClientLogonUsernameRequest(promptMessage, suggestedUsername));
					System.err.println("HERE3d");
					o = in.readObject();
					System.err.println("HERE3d2 " + o.getClass().getName());
					if (o instanceof MessageClientLogon) {
						m = (MessageClientLogon) o;
						this.mclContainingInternetSettings = m;
						usernamegiven = m.getUsername();
						System.err.println("HERE3d25 " + usernamegiven);
						boolean usernameAlreadyUsed = expM.isParticipantNameAlreadyUsed(usernamegiven);
						System.err.println("HERE3d26 " + usernamegiven);
						System.err.println("HERE3d3" + participantIDgiven);
						if (usernameAlreadyUsed) {
							promptMessage = "Username already taken...choose another";
							System.err.println("HERE3e " + promptMessage);
						} else if (usernamegiven.length() < 4) {
							promptMessage = "Please enter your Username (At least 4 characters)";
							System.err.println("HERE3f" + "..usernamegiven is " + usernamegiven + "--email is"
									+ participantIDgiven);
						} else {
							System.err.println("HERE3g" + usernamegiven);
							promptMessage = "can't activate your login..";
							boolean activated = expM.activateParticipant(this, participantIDgiven, usernamegiven);
							System.err.println("HERE3h" + usernamegiven);
							if (activated) {
								this.setConnected(true);
								return true;
							}
						}

					}

				}

				/*
				 * System.err.println("HERE3"); send(new
				 * MessagePopupClientLogonUsernameRequest(
				 * "Please enter your username",
				 * (String)usernamestoredfrompreviouslogins)); o =
				 * in.readObject(); System.err.println("HERE5"); if (o
				 * instanceof MessageClientLogon) { m = (MessageClientLogon) o;
				 * usernamegiven = m.getUsername(); System.err.println("HERE6");
				 * expM.activateParticipant(this,
				 * participantIDgiven,usernamegiven);
				 * System.err.println(participantIDgiven+" USENAME"
				 * +usernamegiven+"ENDUSERNAME"); //System.exit(-1);
				 * this.setConnected(true);
				 * 
				 * return true; }
				 */
			}
		}

		return false;
	}

	/**
	 * Main loop which constantly checks connection for incoming messages
	 *
	 */
	public void run() {
		System.out.println("Thread starting up");

		try {
			System.out.println("run1");
			if (!verifyLoginDetailsAndActivate())
				return;
			System.out.println("run2");
			System.out.println("run2");
			// if(Debug.debugGROOP4) System.out.println("Thread setup for
			// "+particip.getUsername()+ " received first object");
			Object o = in.readObject();
			// if(Debug.debugGROOP4) System.out.println("Thread receiving from
			// "+particip.getUsername()+ " received first object");
			setConnected(true);
			// if(Debug.debugGROOP4) if(this.particip!=null
			// )Debug.printDBG("AAAAAAAAAAAAAAAAA\n");
			while (o instanceof Message & isConnected()) {
				if (!(o instanceof MessageDummy))
					this.appendand(o);
				o = in.readObject();

			}
		} catch (Exception e) {
			setConnected(false);
			System.out.println("SERVER DISCONNECT Detected " + e.getMessage());
			try {
				Conversation.printWSln("Main", "Disconnect detected of " + this.particip.getUsername());
			} catch (Exception e2) {
				Conversation.printWSln("Main", "Disconnect detected of unknown username");
			}
			e.printStackTrace();
			// if(Debug.showErrorsForMacIntegration)System.exit(-1);

		}
	}

	/**
	 *
	 * * Verifies whether participant has typed within a particular time
	 * threshold
	 * 
	 * @param typingWindow
	 *            threshold in milliseconds
	 * @return whether participant has typed
	 */
	public boolean isTyping(long typingWindow) {
		Date currDate = new Date();
		if (currDate.getTime() - timeOfLastTyping > typingWindow) {
			return false;
		}
		return true;
	}

	public void setConnected(boolean isConnected) {
		isLoggedIn = isConnected;
	}

	public boolean isConnected() {
		return isLoggedIn;
	}

	public void dispose() {
		if (particip != null)
			this.particip.disconnectParticipantConnection();
		try {
			in.close();
		} catch (Exception e) {
		}
		try {
			System.out.println("CLOSING");
			out.close();
		} catch (Exception e) {
		}
		this.setConnected(false);
	}

	/**
	 * Ensures that the outgoing queue is periodically reset (to avoid
	 * overflow...this is a well known java bug / feature!
	 */
	private class ConnectionToClientSendLockFlushingAndPeriodicResetting {
		ObjectOutputStream out;
		int turnsBetweenResets = 30;
		long counter = 0;

		public ConnectionToClientSendLockFlushingAndPeriodicResetting(ObjectOutputStream out) {
			this.out = out;
		}

		public synchronized void sendMessage(Message m) throws Exception {
			try {

				out.writeObject(m);
			} catch (Throwable t) {
				// if(Debug.debugBLOCKAGE)System.out.println("SINNNNNNIIII111");
				System.err.println("TRYING TO SEND MESSAGE TO DISCONNECTED PARTICIPANT");
				if (!mostRecentErrorhasBeenFlagged) {
					System.out.println(t.toString());
					t.printStackTrace();
					String errorMessage = "";
					if (particip == null) {
						errorMessage = "Disconnect detected of one of the clients\n"
								+ "Unkown username: The username of the participant isn't set.";
					} else {
						errorMessage = "Disconnect detected of ID: " + particip.getParticipantID() + " USERNAME:"
								+ particip.getUsername();
					}
					CustomDialog.showModelessDialog(errorMessage);
					try {
						//
						particip.getConversation().deprecated_saveAdditionalRowOfDataToSpreadsheetOfTurns(particip, "",
								new Date().getTime(), "Disconnected", "Disconnected");
						particip.getConversation().mm.registerDisconnection();

					} catch (Exception e) {

					}

					mostRecentErrorhasBeenFlagged = true;
				}

			}
			if (DefaultConversationController.config.debug_debugMESSAGEBLOCKAGE)
				System.out.println("SINNNNIIII2");
			out.flush();
			if (DefaultConversationController.config.debug_debugMESSAGEBLOCKAGE)
				System.out.println("SINNNNIIII3");
			counter++;
			if (DefaultConversationController.config.debug_debugMESSAGEBLOCKAGE)
				System.out.println("SINNNNIIII4");
			if (counter > turnsBetweenResets) {
				if (DefaultConversationController.config.debug_debugMESSAGEBLOCKAGE)
					System.out.println("SINNNNIIII5");
				out.reset();
				if (DefaultConversationController.config.debug_debugMESSAGEBLOCKAGE)
					System.out.println("SINNNNIIII6");
				counter = 0;
				if (DefaultConversationController.config.debug_debugMESSAGEBLOCKAGE)
					System.out.println("SINNNNIIII7");
			}
		}
	}

}
