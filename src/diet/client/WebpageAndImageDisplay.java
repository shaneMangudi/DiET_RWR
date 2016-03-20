/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.client;

import java.awt.Color;
import java.awt.Font;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.JEditorPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;

/**
 *
 * @author Greg
 */
public class WebpageAndImageDisplay {

	ConnectionToServer cts;

	public WebpageAndImageDisplay(ConnectionToServer cts) {
		this.cts = cts;
	}

	public static void main(String[] args) {
		WebpageAndImageDisplay wdi = new WebpageAndImageDisplay(null);
		wdi.displayNEWWebpage("webpage", "the headertext", "localhost/blankspace.txt", 100, 200, false, false, false);
		wdi.changeJProgressBar("webpage", "newtext", Color.green, 90);
	}

	Hashtable displays = new Hashtable();

	public void displayNEWWebpage(String id, String header, String url, int width, int height, boolean vScrollBar,
			boolean progressBar, boolean forceCourierFont) {
		if (cts != null) {
			// cts.sendErrorMessage("SETTING NEW WEBPAGE
			// UP:"+url+"-----------------------");
			// cts.sendChatText("SETTING NEW WEBPAGE
			// UP:"+url+"-----------------------", 0, false, null);
			// cts.sendChatText(url, 0, false, new Vector());
			header = header + " - " + cts.getUsername();
		}
		WebpageAndImageDisplayComponent wid = (WebpageAndImageDisplayComponent) displays.get(id);
		if (wid == null) {
			wid = new WebpageAndImageDisplayComponent(header, url, width, height, vScrollBar, progressBar,
					forceCourierFont);
			displays.put(id, wid);
		}
	}

	public void changeWebpage(String id, String url, String prepend, String append) {
		changeWebpage(id, url, prepend, append, -9999);
	}

	public void changeWebpage(String id, String url, String prepend, String append, long lengthOfTime) {
		WebpageAndImageDisplayComponent wid = (WebpageAndImageDisplayComponent) displays.get(id);
		if (cts != null) {
			// cts.sendErrorMessage("TRYING TO CONNECT
			// TO:"+url+"-----------------------");
			// cts.sendChatText("SETTING NEW WEBPAGE
			// UP:"+url+"-----------------------", 0, false, null);

		}
		if (wid != null) {
			if (lengthOfTime < 0) {
				wid.changeWebpage(url, prepend, append);
			} else {
				wid.changeWebpage(url, prepend, append, lengthOfTime);
			}

			// this.addText(id, "\nThis really would be all the text that would
			// be added...here and there", false);
			// this.addText(id, "And at the beginning this\n", true);
			// System.exit(-234);
		}
	}

	public void destroyWebpage(String id) {
		WebpageAndImageDisplayComponent wid = (WebpageAndImageDisplayComponent) displays.get(id);
		if (wid != null) {
			wid.closeDown();
			displays.remove(wid);
		}
	}

	public void destroyAllWebpages() {
		Collection c = displays.values();
		Object[] o = c.toArray();
		for (int i = 0; i < o.length; i++) {
			WebpageAndImageDisplayComponent wid = (WebpageAndImageDisplayComponent) o[i];
			wid.closeDown();
			c.remove(wid);
		}
	}

	public void changeJProgressBar(String id, final String text, final Color colorForeground, int value) {
		WebpageAndImageDisplayComponent wid = (WebpageAndImageDisplayComponent) displays.get(id);
		if (wid != null) {
			wid.changeJProgressBar(text, colorForeground, value);
		}
	}

	public static void setJTextPaneFont(JEditorPane jtp, Font font, Color c) {
		DefaultStyledDocument doc = (DefaultStyledDocument) jtp.getDocument();
		Element e = doc.getDefaultRootElement();
		AttributeSet attr = e.getAttributes().copyAttributes();

	}

}
