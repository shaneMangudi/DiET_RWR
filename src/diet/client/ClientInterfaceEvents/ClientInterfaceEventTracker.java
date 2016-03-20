/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.client.ClientInterfaceEvents;

import diet.client.ClientInterfaceEvents.ClientInterfaceEvent;
import diet.attribval.AttribVal;
import diet.client.ClientEventHandler;
import diet.message.MessageClientInterfaceEvent;
import java.util.Vector;

/**
 *
 * @author gj
 */
public class ClientInterfaceEventTracker extends Thread {
	final public static int changeScreenBackgroundColour = 1;
	final public static int displaytextInStatusBarANDDisableTextentry = 2;
	final public static int changeBorderOfMazeFrame = 3;
	final public static int changeMazeWindow = 4;
	final public static int disableScrolling = 5;
	final public static int changeTextStyleOfTextFormulationWindow = 6; //
	final public static int disableTextPane = 7;
	final public static int changeBorderOfChatFrame = 8;
	final public static int displaytextInStatusBarANDEnableTextentry = 9;
	final public static int enableTextEntry = 10;
	final public static int clearAllWindowsExceptWindow0 = 11;
	final public static int displayTextInStatusBar = 12;
	final public static int clearMainTextWindows = 13;
	final public static int enableScrolling = 14;
	final public static int disableTextEntry = 15;
	final public static int enableTextPane = 16;
	final public static int clearTextEntryField = 17;
	final public static int enableDeletes = 20;
	final public static int disableDeletes = 21;

	// These aren't quite the same as those above - the codes above are
	// instructions. The number codes below are IDs
	public final static int normalturn = 50;
	public final static int splitscreenAddText = 51;
	public final static int splitscreenDeleteText = 52;

	Vector documentEvents = new Vector();
	boolean trackEvents = true;
	ClientEventHandler ceh;

	public final static String textentryfield_insertstring = "tefi";
	public final static String textentryfield_removestring = "termve";
	public final static String textentryfield_replacestring = "tefr";
	public final static String chatwindow_appendbyotherparticipant = "chwappbyo";
	public final static String chatwindow_appendbyself_clearingtextentry = "chwappbyselfclearingte";
	public final static String changeborder = "changeborder";
	public final static String changestatuslabel = "changelabel";
	public final static String changestatuslabel_disabletextentry = "changelabel_disablete";
	public final static String changestatuslabel_enabletextentry = "changelabel_enablete";

	/// This is at another level of abstraction - depending on the experiment,
	/// different signals of "is typing" might be used.
	public final static String istypingByOtherSTART = "istypstart";
	public final static String istypingByOtherFINISH = "istypfinish";

	public final static String mazegameOpenGates = "mgog";
	public final static String mazegameCloseGates = "mgcg";

	public ClientInterfaceEventTracker(ClientEventHandler ceh) {
		this.ceh = ceh;
		this.start();
		// ClientInterfaceEventTracker.c
	}

	public synchronized void deprecated_addClientEvent(String type, long timeOfDisplay, String attribname,
			String attribval) {
		// AttribVal av1 = new AttribVal(attribname,attribval);
		// ClientInterfaceEvent cie = new
		// ClientInterfaceEvent(ceh.getCts().getEmail(),ceh.getCts().getUsername()
		// ,type, timeOfDisplay ,av1);
		// needs to be moved to
	}

	public synchronized void addClientEvent(String type, long timeofclientdisplay, Vector attribvals) {

		// AttribVal[] attribvalues = ( AttribVal[] )attribvals.toArray();

		ClientInterfaceEvent cie = new ClientInterfaceEvent(ceh.getCts().getEmail(), ceh.getCts().getUsername(), type,
				timeofclientdisplay, attribvals);
		this.documentEvents.add(cie);

		this.notify();
	}

	public synchronized ClientInterfaceEvent addClientEvent(String type, long timeofclientdisplay,
			AttribVal... attribvalues) {
		ClientInterfaceEvent cie = new ClientInterfaceEvent(ceh.getCts().getEmail(), ceh.getCts().getUsername(), type,
				timeofclientdisplay, attribvalues);
		this.documentEvents.add(cie);
		this.notify();
		return cie;
	}

	public synchronized Object getNext() {
		while (this.documentEvents.size() == 0) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Object value = documentEvents.elementAt(0);
		documentEvents.remove(value);
		return value;

	}

	public void run() {
		while (this.trackEvents) {
			Object event = this.getNext();
			if (event instanceof ClientInterfaceEvent) {
				MessageClientInterfaceEvent mcie = new MessageClientInterfaceEvent(ceh.getCts().getEmail(),
						ceh.getCts().getUsername(), (ClientInterfaceEvent) event);
				ceh.getCts().sendMessage(mcie);
			}

		}
	}

}
