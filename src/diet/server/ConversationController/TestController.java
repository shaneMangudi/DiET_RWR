/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.server.ConversationController;

import diet.client.ClientInterfaceEvents.ClientInterfaceEvent;
import diet.client.ClientInterfaceEvents.ClientInterfaceEventStringPrettifier;
import diet.debug.Debug;
import diet.message.MessageChatTextFromClient;
import diet.message.MessageKeypressed;
import diet.server.Conversation;
import diet.server.Participant;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Vector;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author gj
 */
public class TestController extends DefaultDyadicConversationController {

	public TestController(Conversation c) {
		super(c);
	}

	@Override
	public synchronized void processChatText(Participant sender, MessageChatTextFromClient mct) {
		super.processChatText(sender, mct); // To change body of generated
											// methods, choose Tools |
											// Templates.
		conversation.showStimulusImageFromJarFile_InitializeWindow(sender, 600, 600, "set1/a.png");
		conversation.showStimulusImageFromJarFile_ChangeImage(sender, "set1/a.png", 3000);

		Vector v = mct.getClientInterfaceEvents();
		String clientinterfaceevents = "";
		for (int i = 0; i < v.size(); i++) {
			ClientInterfaceEvent cie = (ClientInterfaceEvent) v.elementAt(i);
			clientinterfaceevents = clientinterfaceevents + cie.getType() + ": "
					+ cie.getAllAttribValuesAsStringForDebug() + "\n";
			JsonObjectBuilder jsob = ClientInterfaceEventStringPrettifier.getVerboseJSONDescription(cie);
			JsonObject jsobs = jsob.build();
			System.err.println(jsobs.toString());
		}
		String turnasitwasproduced = ClientInterfaceEventStringPrettifier
				.getTextFormulationProcessRepresentationLOGARHYTHMICSPACE(v);
		Debug.showDebug2(turnasitwasproduced + "\n");

	}

	@Override
	public synchronized void participantJoinedConversation(Participant p) {
		super.participantJoinedConversation(p); // To change body of generated
												// methods, choose Tools |
												// Templates.
		if (conversation.getParticipants().getAllParticipants().size() == 1) {
			conversation.showStimulusImageFromJarFile_InitializeWindow(p, 600, 600, "set1/a.png");
			conversation.showStimulusImageFromJarFile_ChangeImage(p, "set1/a.png", 3000);
		}

	}

	@Override
	public void processKeyPress(Participant sender, MessageKeypressed mkp) {

		Participant other = (Participant) conversation.getParticipants().getAllOtherParticipants(sender).elementAt(0);
		System.err.println("THE KEYCODE IS: " + mkp.getKeypressed().getKeycode());

		if (mkp.getKeypressed().getKeycode() != KeyEvent.VK_ENTER) {
			for (long l = new Date().getTime(); l < new Date().getTime() + random.nextInt(5000); l = l + 900) {
				isTypingOrNotTyping.addSpoofTypingInfo(other, l);
			}
		}
	}

}
