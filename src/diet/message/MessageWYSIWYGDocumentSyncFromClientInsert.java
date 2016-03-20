package diet.message;

import java.io.Serializable;

import javax.swing.text.AttributeSet;

public class MessageWYSIWYGDocumentSyncFromClientInsert extends Message implements Serializable {

	private String textTyped;
	private int offset;
	private int length;
	private AttributeSet attr;
	private String allTextInWindow = "";
	private String[] priorChatText;

	/*
	 * public MessageWYSIWYGDocumentSyncFromClientInsert(String email, String
	 * username, String textAppended, int offset,int length,AttributeSet a) {
	 * this(email, username, textAppended, offset,length); attr=a; }
	 */

	public MessageWYSIWYGDocumentSyncFromClientInsert(String email, String username, String textTyped, int offset,
			int length, String allTextInWindow, String[] priorChatText) {
		super(email, username);
		this.textTyped = textTyped;
		this.setOffset(offset);
		this.setLength(length);
		this.allTextInWindow = allTextInWindow;
		if (allTextInWindow == null)
			allTextInWindow = "";
		this.priorChatText = priorChatText;
	}

	public AttributeSet getAttr() {
		return attr;
	}

	public String getTextTyped() {
		return textTyped;
	}

	public int getOffset() {
		return offset;
	}

	public int getLength() {
		return length;
	}

	public String getTextToAppendToWindow() {
		return getTextTyped();
	}

	public String getMessageClass() {
		return "WYSIWYGDocSyncFromClient";
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getAllTextInWindow() {
		return this.allTextInWindow;
	}

	public String[] getPreviousTurnByOther() {
		return this.priorChatText;
	}
}
