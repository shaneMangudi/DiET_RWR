/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.server.stylemanager;

import diet.server.ConversationController.DefaultConversationController;
import static diet.server.ConversationController.DefaultConversationController.config;
import diet.server.Participant;
import java.awt.Color;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author Greg
 */
public class StyleManager {

	DefaultConversationController cC;

	public SimpleAttributeSet defaultFONTSETTINGSOTHER = new SimpleAttributeSet();
	public SimpleAttributeSet defaultFONTSETTINGSSELF = new SimpleAttributeSet();
	public SimpleAttributeSet defaultFONTSETTINGSSERVER = new SimpleAttributeSet();

	public StyleManager(DefaultConversationController cC) {

		// These should all be initialized from the ConversationController

		this.cC = cC;

		StyleConstants.setFontFamily(defaultFONTSETTINGSSELF, cC.config.client_font_SELF_fontfamily);
		StyleConstants.setFontSize(defaultFONTSETTINGSSELF, cC.config.client_font_SELF_size);
		StyleConstants.setBold(defaultFONTSETTINGSSELF, cC.config.client_font_SELF_isbold);
		StyleConstants.setItalic(defaultFONTSETTINGSSELF, cC.config.client_font_SELF_isitalic);
		StyleConstants.setForeground(defaultFONTSETTINGSSELF,
				new Color(cC.config.client_font_SELF_foregroundcolour_rgb[0],
						cC.config.client_font_SELF_foregroundcolour_rgb[1],
						cC.config.client_font_SELF_foregroundcolour_rgb[2]));

		StyleConstants.setFontFamily(defaultFONTSETTINGSOTHER, cC.config.client_font_OTHER_fontfamily);
		StyleConstants.setFontSize(defaultFONTSETTINGSOTHER, cC.config.client_font_OTHER_size);
		StyleConstants.setBold(defaultFONTSETTINGSOTHER, cC.config.client_font_OTHER_isbold);
		StyleConstants.setItalic(defaultFONTSETTINGSOTHER, cC.config.client_font_OTHER_isitalic);
		StyleConstants.setForeground(defaultFONTSETTINGSOTHER,
				new Color(cC.config.client_font_OTHER_foregroundcolour_rgb[0],
						cC.config.client_font_OTHER_foregroundcolour_rgb[1],
						cC.config.client_font_OTHER_foregroundcolour_rgb[2]));

		StyleConstants.setFontFamily(defaultFONTSETTINGSSERVER, cC.config.client_font_SERVER_fontfamily);
		StyleConstants.setFontSize(defaultFONTSETTINGSSERVER, cC.config.client_font_SERVER_fontsize);
		StyleConstants.setBold(defaultFONTSETTINGSSERVER, cC.config.client_font_SERVER_isbold);
		StyleConstants.setItalic(defaultFONTSETTINGSSERVER, cC.config.client_font_SERVER_isitalic);
		StyleConstants.setForeground(defaultFONTSETTINGSSERVER,
				new Color(cC.config.client_font_SERVER_foregroundcolour_rgb[0],
						cC.config.client_font_SERVER_foregroundcolour_rgb[1],
						cC.config.client_font_SERVER_foregroundcolour_rgb[2]));
	}

	public MutableAttributeSet getStyleForRecipient(Participant sender, Participant recipient) {
		if (recipient == sender) {
			return defaultFONTSETTINGSSELF;
		}
		return defaultFONTSETTINGSOTHER;
	}

	public MutableAttributeSet getStyleForInstructionMessages(Participant recipient) {
		return this.defaultFONTSETTINGSSERVER;
	}

	public MutableAttributeSet getDefaultStyleForInstructionMessages() {
		return this.defaultFONTSETTINGSSERVER;
	}

	public MutableAttributeSet getStyleForSelf() {
		return this.defaultFONTSETTINGSSELF;
	}

	public int getWindowNumberInWhichParticipantBReceivesTextFromParticipantA(Participant senderA,
			Participant recipientB) {
		return 0;
	}

	public int getWindowNumberInWhichParticipantBReceivesTextFromServer(Participant recipientB) {
		return 0;
	}

}
