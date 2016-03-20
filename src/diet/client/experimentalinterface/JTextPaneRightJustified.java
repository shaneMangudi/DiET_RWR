/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//http://stackoverflow.com/questions/13074428/how-can-i-set-each-character-to-a-different-color-background-color-in-a-jtextpan

package diet.client.experimentalinterface;

import diet.client.JTextPaneNavigationFilter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Date;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author gj
 */
public class JTextPaneRightJustified extends JTextPane {

	JTextPaneRightJustifiedDocument jtprjfd = new JTextPaneRightJustifiedDocument();
	JTextPaneNavigationFilter jtpnf = new JTextPaneNavigationFilter(this, null);

	public JTextPaneRightJustified() {
		this.setDocument(jtprjfd);
		this.setNavigationFilter(jtpnf);
	}

	public static void main(String args[]) throws Exception {
		JFrame frame = new JFrame("TextPane Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// document.insertString(document.getLength(), "abcde", style);

		JTextPaneRightJustified textPane = new JTextPaneRightJustified();
		JScrollPane scrollPane = new JScrollPane(textPane);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(800, 100);
		frame.setVisible(true);
		textPane.doTesting();
	}

	public void changeText() {
		int end = this.getDocument().getLength();
		StyledDocument doc = this.getStyledDocument();
		AttributeSet as = null;

		for (int i = 0; i < end; i++) {
			// as = this.createRandomAttributeSet();
			Element ee = doc.getCharacterElement(i);
			AttributeSet asnew = ee.getAttributes();
			Object o = asnew.getAttribute("created");
			long timestampcreated = (long) o;

			// long timestampcreated =
			// (long)doc.getCharacterElement(i).getAttributes().getAttribute("created");

			// doc.getCharacterElement(i).getAttributes().getAttribute("foreground").toString();

			// doc.setCharacterAttributes(i, i-1, as, false);
			AttributeSet asOLD = doc.getCharacterElement(i).getAttributes();
			try {
				System.err.print(doc.getText(i, 1) + "--");
			} catch (Exception e) {
				e.printStackTrace();
			}
			as = this.fadeOut(5000, timestampcreated, asOLD);
			doc.setCharacterAttributes(i, 1, as, false);
		}

		// need to make sure it is impossible to move the cursor to further back
		// in the text

		if (2 < 5)
			return;
		as = this.createRandomAttributeSet();
		doc.setCharacterAttributes(end - 1, 1, as, false);
		as = this.createRandomAttributeSet();
		doc.setCharacterAttributes(end - 2, 1, as, false);
		as = this.createRandomAttributeSet();
		doc.setCharacterAttributes(end - 3, 1, as, false);
		as = this.createRandomAttributeSet();
		doc.setCharacterAttributes(end - 4, 1, as, false);
		as = this.createRandomAttributeSet();
		doc.setCharacterAttributes(end - 5, 1, as, false);
		as = this.createRandomAttributeSet();
		doc.setCharacterAttributes(end - 6, 1, as, false);
	}

	public void doTesting() {
		while (2 < 5) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					changeText();
				}
			});

			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public AttributeSet fadeOut(long durationToDisplay, long timecreated, AttributeSet as) {
		Random random = new Random();

		Color foreground = (Color) as.getAttribute("originalforeground");

		double oforegroundR = foreground.getRed();

		double oforegroundG = foreground.getGreen();
		double oforegroundB = foreground.getBlue();

		System.err.print(" timecreated:" + timecreated + "---");

		double proportionCompleted = ((double) new Date().getTime() - timecreated) / ((double) durationToDisplay);
		if (proportionCompleted == 1)
			proportionCompleted = 1;
		System.err.println("ProportionCompleted: " + proportionCompleted);
		SimpleAttributeSet set = new SimpleAttributeSet();
		if (proportionCompleted >= 1) {
			StyleConstants.setForeground(set, new Color(255, 255, 255));
		}
		if (proportionCompleted <= 0) {
			StyleConstants.setForeground(set, new Color(0, 0, 0));
		} else {
			double oforegroundR_remaining = 255 - oforegroundR;
			double oforegroundG_remaining = 255 - oforegroundG;
			double oforegroundB_remaining = 255 - oforegroundB;

			double oforegroundRNEW = oforegroundR + ((proportionCompleted) * oforegroundR_remaining);
			double oforegroundGNEW = oforegroundG + ((proportionCompleted) * oforegroundG_remaining);
			double oforegroundBNEW = oforegroundB + ((proportionCompleted) * oforegroundB_remaining);
			if (oforegroundRNEW > 255)
				oforegroundRNEW = 255;
			if (oforegroundGNEW > 255)
				oforegroundGNEW = 255;
			if (oforegroundBNEW > 255)
				oforegroundBNEW = 255;

			StyleConstants.setForeground(set,
					new Color((int) oforegroundRNEW, (int) oforegroundGNEW, (int) oforegroundBNEW));
			StyleConstants.setBackground(set, new Color(255, 255, 255));

			// System.err.println( "ooforegroundnew: "+oforegroundRNEW);

		}

		// StyleConstants.setBold(set, random.nextBoolean());

		return set;
	}

	public AttributeSet createRandomAttributeSet() {
		Random random = new Random();
		SimpleAttributeSet set = new SimpleAttributeSet();
		// StyleConstants.setBackground(set, new Color(random.nextInt(256),
		// random.nextInt(256), random.nextInt(256)));
		StyleConstants.setForeground(set, new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		StyleConstants.setFontSize(set, random.nextInt(12) + 12);
		StyleConstants.setBold(set, random.nextBoolean());
		StyleConstants.setItalic(set, random.nextBoolean());
		StyleConstants.setUnderline(set, random.nextBoolean());
		set.addAttribute("created", new Date().getTime());
		return set;
	}

}
