package diet.message;

import diet.client.StyledDocumentStyleSettings;
import java.io.Serializable;

@SuppressWarnings("serial")
public class MessageClientSetupParametersWithSendButtonAndTextEntryWidthByHeight extends MessageClientSetupParameters
		implements Serializable {

	private final int windowOfOwnText;
	private final boolean alignmentIsVertical;
	private final int mainWindowWidth;
	private final int mainWindowHeight;
	private final int textEntryWidth;
	private final int textEntryHeight;
	private final boolean participantHasStatusWindow;
	private final int numberOfWindows;
	private final int maxLengthOfTextEntry;
	private final StyledDocumentStyleSettings styledDocumentStyleSettings;
	private final String experimentClassName;

	public MessageClientSetupParametersWithSendButtonAndTextEntryWidthByHeight(String servername, String servername2,
			int mainWindowWidth, int mainWindowHeight, boolean alignmentIsVertical, int numberOfWindows,
			int windowOfOwnText, boolean windowIsEnabled, boolean participantHasStatusWindow, String statusDisplay,
			boolean statusIsInRed, int textEntryWidth, int textEntryHeight, int maxNumberOfChars,
			StyledDocumentStyleSettings styledDocumentStyleSettings, String experimentClassName) {
		super(servername, servername2);

		this.numberOfWindows = numberOfWindows;
		this.windowOfOwnText = windowOfOwnText;
		this.alignmentIsVertical = alignmentIsVertical;
		this.participantHasStatusWindow = participantHasStatusWindow;

		this.mainWindowWidth = mainWindowWidth;
		this.mainWindowHeight = mainWindowHeight;
		this.textEntryWidth = textEntryWidth;
		this.textEntryHeight = textEntryHeight;
		this.maxLengthOfTextEntry = maxNumberOfChars;
		this.styledDocumentStyleSettings = styledDocumentStyleSettings;
		this.experimentClassName = experimentClassName;
	}

	public int getMaxCharLength() {
		return maxLengthOfTextEntry;
	}

	public StyledDocumentStyleSettings getStyledDocumentStyleSettings() {
		return styledDocumentStyleSettings;
	}

	public int getParticipantsTextWindow() {
		return getWindowOfOwnText();
	}

	public boolean getAlignmentIsVertical() {
		return alignmentIsVertical;
	}

	public boolean getParticipantHasStatusWindow() {
		return participantHasStatusWindow;
	}

	public int getTextEntryWidth() {
		return this.textEntryWidth;
	}

	public int getTextEntryHeight() {
		return this.textEntryHeight;
	}

	public int getMainWindowWidth() {
		return this.mainWindowWidth;
	}

	public int getMainWindowHeight() {
		return this.mainWindowHeight;
	}

	public int getWindowOfOwnText() {
		return windowOfOwnText;
	}

	public int getNumberOfWindows() {
		return numberOfWindows;
	}
	
	public String getExperimentClassName() {
		return experimentClassName;
	}
}
