/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.client.experimentalinterface;

import java.awt.Color;
import java.util.Date;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author gj
 */
public class JTextPaneRightJustifiedDocument extends DefaultStyledDocument {

	int maximumLength = 50;

	public JTextPaneRightJustifiedDocument() {
		StyleContext context = new StyleContext();
		StyledDocument document = new DefaultStyledDocument(context);
		Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setFontSize(style, 30);
		style.addAttribute("created", new Date().getTime());
		style.addAttribute("originalforeground", StyleConstants.getForeground(style));
		this.setLogicalStyle(0, style);

	}

	public void insertString(int offs, String str, AttributeSet as) throws BadLocationException {

		SimpleAttributeSet newAttrs = new SimpleAttributeSet();
		StyleConstants.setForeground(newAttrs, Color.BLACK);
		StyleConstants.setBackground(newAttrs, Color.WHITE);
		StyleConstants.setFontSize(newAttrs, 20);
		StyleConstants.setFontFamily(newAttrs, StyleConstants.getFontFamily(as));
		StyleConstants.setAlignment(newAttrs, StyleConstants.ALIGN_RIGHT);
		newAttrs.addAttribute("created", new Date().getTime());
		newAttrs.addAttribute("originalforeground", StyleConstants.getForeground(newAttrs));

		int characterstodelete = 0;
		if (super.getLength() > maximumLength) {
			characterstodelete = super.getLength() - maximumLength;
			super.remove(0, characterstodelete);
			System.err.println("REMOVEDTEXT");
		}
		str = str.replace("\n", ".");
		super.insertString(this.getLength(), str, newAttrs);

	}

	@Override
	public void remove(int i, int i1) throws BadLocationException {
		// super.remove(i, i1); //To change body of generated methods, choose
		// Tools | Templates.

		// System.exit(-56);
	}

	@Override
	public void replace(int i, int i1, String string, AttributeSet as) throws BadLocationException {

		if (i != this.getLength())
			return;

		SimpleAttributeSet newAttrs = new SimpleAttributeSet();
		StyleConstants.setForeground(newAttrs, StyleConstants.getForeground(as));

		StyleConstants.setBackground(newAttrs, Color.WHITE);
		StyleConstants.setForeground(newAttrs, Color.BLACK);
		StyleConstants.setFontSize(newAttrs, 20);
		StyleConstants.setFontFamily(newAttrs, StyleConstants.getFontFamily(as));

		StyleConstants.setAlignment(newAttrs, StyleConstants.ALIGN_RIGHT);
		newAttrs.addAttribute("created", new Date().getTime());
		newAttrs.addAttribute("originalforeground", StyleConstants.getForeground(newAttrs));

		super.replace(i, i1, string, newAttrs); // To change body of generated
												// methods, choose Tools |
												// Templates.

		// super.replace(i, i1, string, as); //To change body of generated
		// methods, choose Tools | Templates.
	}

}
