/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.serverclientconsistencycheck;

import diet.server.ConversationController.DefaultConversationController;
import diet.server.ConversationController.ui.CustomDialog;

/**
 *
 * @author GM
 */
public class ServerClientConsistency {

	static double versionID = DefaultConversationController.config.about_softwareversion;

	public static boolean testForConsistency(double scsdouble) {
		if (scsdouble == versionID) {
			return true;
		}

		{
			CustomDialog.showDialog(
					"WARNING!\n The server has detected that the client is NOT using the same version of the software\n"
							+ "This usually isn't a problem for minor updates. But if you are seeing this, this means there has been a large update\n"
							+ "Most probably of the messaging format between client and server! Please update!\n"
							+ "To make sure you don't click and forget...there will be a second error message!!");
			return false;

		}
	}

	static public double getVersionID() {
		return versionID;
	}

}
