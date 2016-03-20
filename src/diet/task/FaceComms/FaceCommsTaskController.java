/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.task.FaceComms;

import diet.attribval.AttribVal;
import diet.server.Conversation;
import diet.server.ConversationController.DefaultConversationController;
import diet.server.ConversationController.FaceComms2016;
import diet.server.ConversationController.ui.CustomDialog;
import diet.server.Participant;
import diet.utils.HashtableWithDefaultvalue;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author gj
 */
public class FaceCommsTaskController {

	DefaultConversationController cC;
	double probabOfSame = CustomDialog.getDouble("Enter the probability of SAME stimuli", 0, 1, 0.5);
	public Participant pA;
	public Participant pB;
	Random r = new Random();
	long durationOfStimulus = CustomDialog.getLong("How long should the stimuli be displayed for?", 5000);
	boolean blockTextEntryDuringStimulus = CustomDialog.getBoolean("Block text entry while stimulus is displayed?",
			"block", "do not block");

	String pA_Imagename;
	String pB_Imagename;

	boolean currentsethasbeensolved = false;

	public HashtableWithDefaultvalue htscoreCORRECT = new HashtableWithDefaultvalue(0);
	public HashtableWithDefaultvalue htscoreINCORRECT = new HashtableWithDefaultvalue(0);

	long gamenumber = 0;

	// --add a button on the stimulus window
	// --add the is typing timeout same / different long / short
	// --add the binary flag of do they see the same face
	// ---in the output file it needs to save whether they are seeing the same
	// or different faces
	// ---in the output file it needs to save an ID for the set two numbers - if
	// the first number is less than 5 it means it is short timeout..if it is
	// more than 5 then it is long timeout

	public FaceCommsTaskController(DefaultConversationController cC) {
		this.cC = cC;
	}

	public void startTask(Participant pA, Participant pB) {
		this.pA = pA;
		this.pB = pB;
		cC.conversation.showStimulusImageFromJarFile_InitializeWindow(pA, 500, 520, "");
		cC.conversation.showStimulusImageFromJarFile_InitializeWindow(pB, 500, 520, "");
		doCountdowntoNextSet("Countdown: Next face in ");
	}

	private void doCountdowntoNextSet(final String messageprefix) {
		Thread t = new Thread() {
			public void run() {
				try {
					cC.conversation.changeClientInterface_clearMainWindows(pA);
					cC.conversation.changeClientInterface_clearMainWindows(pB);
					if (blockTextEntryDuringStimulus)
						cC.conversation.changeClientInterface_disableTextEntry(pA);
					if (blockTextEntryDuringStimulus)
						cC.conversation.changeClientInterface_disableTextEntry(pB);

					// cC.c.changeClientInterface_backgroundColour(pA,
					// Color.red);
					// cC.c.changeClientInterface_backgroundColour(pB,
					// Color.red);
					cC.conversation.newsendInstructionToParticipant(pA, messageprefix + "5 secs");
					cC.conversation.newsendInstructionToParticipant(pB, messageprefix + "5 secs");
					Thread.sleep(1000);
					// cC.c.changeClientInterface_clearMainWindows(pA);
					// cC.c.changeClientInterface_clearMainWindows(pB);
					// cC.c.changeClientInterface_backgroundColour(pA,
					// Color.black);
					// cC.c.changeClientInterface_backgroundColour(pB,
					// Color.black);
					cC.conversation.newsendInstructionToParticipant(pA, messageprefix + "4 secs");
					cC.conversation.newsendInstructionToParticipant(pB, messageprefix + "4 secs");
					Thread.sleep(1000);
					// cC.c.changeClientInterface_clearMainWindows(pA);
					// cC.c.changeClientInterface_clearMainWindows(pB);
					// cC.c.changeClientInterface_backgroundColour(pA,
					// Color.red);
					// cC.c.changeClientInterface_backgroundColour(pB,
					// Color.red);
					cC.conversation.newsendInstructionToParticipant(pA, messageprefix + "3 secs");
					cC.conversation.newsendInstructionToParticipant(pB, messageprefix + "3 secs");
					Thread.sleep(1000);
					// cC.c.changeClientInterface_clearMainWindows(pA);
					// cC.c.changeClientInterface_clearMainWindows(pB);
					// cC.c.changeClientInterface_backgroundColour(pA,
					// Color.black);
					// cC.c.changeClientInterface_backgroundColour(pB,
					// Color.black);
					cC.conversation.newsendInstructionToParticipant(pA, messageprefix + "2 secs");
					cC.conversation.newsendInstructionToParticipant(pB, messageprefix + "2 secs");
					Thread.sleep(1000);
					// cC.c.changeClientInterface_clearMainWindows(pA);
					// cC.c.changeClientInterface_clearMainWindows(pB);
					// cC.c.changeClientInterface_backgroundColour(pA,
					// Color.red);
					// cC.c.changeClientInterface_backgroundColour(pB,
					// Color.red);
					cC.conversation.newsendInstructionToParticipant(pA, messageprefix + "1 sec");
					cC.conversation.newsendInstructionToParticipant(pB, messageprefix + "1 sec");
					Thread.sleep(1000);
					cC.conversation.changeClientInterface_clearMainWindows(pA);
					cC.conversation.changeClientInterface_clearMainWindows(pB);

					// cC.c.changeClientInterface_backgroundColour(pA,
					// Color.white);
					// cC.c.changeClientInterface_backgroundColour(pB,
					// Color.white);
					// cC.c.newsendInstructionToParticipant(pA, "Please " );

					// cC.c.newsendInstructionToParticipant(pB, messageprefix +
					// "1 seconds" );

					// cC.c.showStimulusImageFromJarFile_ChangeImage(pA,
					// "faceset1/face1.png", 4000);
					// cC.c.showStimulusImageFromJarFile_ChangeImage(pB,
					// "faceset1/face1.png", 4000);

					String newpA_Imagename = "";
					String newpB_Imagename = "";

					int index = 1 + r.nextInt(30);

					long threshold = (long) (probabOfSame * 1000);
					long number = r.nextInt(1000);
					if (number < threshold) {
						// do the same
						Conversation.printWSln("Main", "SAME:");
						newpA_Imagename = "faceset1/face" + index + ".png";
						newpB_Imagename = "faceset1/face" + index + ".png";
						System.err.println("SAME");

					} else {
						Conversation.printWSln("Main", "DIFFERENT:");
						newpA_Imagename = "faceset1/face" + index + ".png";
						newpB_Imagename = "faceset1/face" + getRandomNumberBetween(1, 30, index) + ".png";
						System.err.println("DIFFERENT");
					}

					pA_Imagename = newpA_Imagename;
					pB_Imagename = newpB_Imagename;

					gamenumber++;
					cC.conversation.showStimulusImageFromJarFile_ChangeImage(pA, newpA_Imagename, durationOfStimulus);
					cC.conversation.showStimulusImageFromJarFile_ChangeImage(pB, newpB_Imagename, durationOfStimulus);

					currentsethasbeensolved = false;

					int pAPercentageCorrect = 0;
					int pANumberCorrect = getScoreCORRECT(pA);
					int pANumberINCorrect = getScoreINCORRECT(pA);
					int pBPercentageCorrect = 0;
					int pBNumberCorrect = getScoreCORRECT(pB);
					int pBNumberINCorrect = getScoreINCORRECT(pB);
					if (gamenumber > 1) {
						pAPercentageCorrect = (100 * pANumberCorrect) / (pANumberCorrect + pANumberINCorrect);
						pBPercentageCorrect = (100 * pBNumberCorrect) / (pBNumberCorrect + pBNumberINCorrect);
					}

					if (!blockTextEntryDuringStimulus)
						cC.conversation.newsendInstructionToParticipant(pA, "Your success rate is: " + pAPercentageCorrect + "%");
					if (!blockTextEntryDuringStimulus)
						cC.conversation.newsendInstructionToParticipant(pB, "Your success rate is: " + pBPercentageCorrect + "%");

					if (!blockTextEntryDuringStimulus)
						cC.conversation.newsendInstructionToParticipant(pA, "enter '/s' if you saw the same face");
					if (!blockTextEntryDuringStimulus)
						cC.conversation.newsendInstructionToParticipant(pA, "enter '/d' if you saw different faces");
					if (!blockTextEntryDuringStimulus)
						cC.conversation.newsendInstructionToParticipant(pB, "enter '/s' if you saw the same face");
					if (!blockTextEntryDuringStimulus)
						cC.conversation.newsendInstructionToParticipant(pB, "enter '/d' if you saw different faces");
					((FaceComms2016) cC).changeInterfaceDelays();

					Thread tt = new Thread() {
						public void run() {
							try {
								Thread.sleep(durationOfStimulus);
								cC.conversation.changeClientInterface_enableTextEntry(pA);
								cC.conversation.changeClientInterface_enableTextEntry(pB);

								int pAPercentageCorrect = 0;
								int pANumberCorrect = getScoreCORRECT(pA);
								int pANumberINCorrect = getScoreINCORRECT(pA);
								int pBPercentageCorrect = 0;
								int pBNumberCorrect = getScoreCORRECT(pB);
								int pBNumberINCorrect = getScoreINCORRECT(pB);
								if (gamenumber > 1) {
									pAPercentageCorrect = (100 * pANumberCorrect)
											/ (pANumberCorrect + pANumberINCorrect);
									pBPercentageCorrect = (100 * pBNumberCorrect)
											/ (pBNumberCorrect + pBNumberINCorrect);
								}

								if (blockTextEntryDuringStimulus)
									cC.conversation.newsendInstructionToParticipant(pA,
											"Your success rate is: " + pAPercentageCorrect + "%");
								if (blockTextEntryDuringStimulus)
									cC.conversation.newsendInstructionToParticipant(pB,
											"Your success rate is: " + pBPercentageCorrect + "%");

								if (blockTextEntryDuringStimulus)
									cC.conversation.newsendInstructionToParticipant(pA, "enter '/s' if you saw the same face");
								if (blockTextEntryDuringStimulus)
									cC.conversation.newsendInstructionToParticipant(pA, "enter '/d' if you saw different faces");
								if (blockTextEntryDuringStimulus)
									cC.conversation.newsendInstructionToParticipant(pB, "enter '/s' if you saw the same face");
								if (blockTextEntryDuringStimulus)
									cC.conversation.newsendInstructionToParticipant(pB, "enter '/d' if you saw different faces");

							} catch (Exception eee) {
								eee.printStackTrace();
							}
						}
					};
					tt.start();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
		t.start();
	}

	public void processChatText(Participant sender, String text) {
		if (!text.startsWith("/"))
			return;
		if (this.currentsethasbeensolved) {
			cC.conversation.newsendInstructionToParticipant(sender, "The current face has already been solved");
			return;

		}
		text = text.replace("\n", "");
		text = text.replace(" ", "");
		if (text.equalsIgnoreCase("/s")) {
			if (this.pA_Imagename.equalsIgnoreCase(this.pB_Imagename)) {
				cC.conversation.newsendInstructionToParticipant(pA, "CORRECT! They are the SAME");
				cC.conversation.newsendInstructionToParticipant(pB, "CORRECT! They are the SAME");
				this.currentsethasbeensolved = true;
				doCountdowntoNextSet("CORRECT! They are the SAME. Next face in ");
				this.updateScores(true);
			} else {
				cC.conversation.newsendInstructionToParticipant(pA, "INCORRECT! They are DIFFERENT");
				cC.conversation.newsendInstructionToParticipant(pB, "INCORRECT! They are DIFFERENT");
				this.currentsethasbeensolved = true;
				doCountdowntoNextSet("INCORRECT! They are  DIFFERENT. Next face in ");
				this.updateScores(false);
			}
		} else if (text.equalsIgnoreCase("/d")) {
			if (!this.pA_Imagename.equalsIgnoreCase(this.pB_Imagename)) {
				cC.conversation.newsendInstructionToParticipant(pA, "CORRECT! They are DIFFERENT");
				cC.conversation.newsendInstructionToParticipant(pB, "CORRECT! They are DIFFERENT");
				this.currentsethasbeensolved = true;
				doCountdowntoNextSet("CORRECT! They are DIFFERENT. Next face in ");
				this.updateScores(true);
			} else {
				cC.conversation.newsendInstructionToParticipant(pA, "INCORRECT! They are the SAME");
				cC.conversation.newsendInstructionToParticipant(pB, "INCORRECT! They are the SAME");
				this.currentsethasbeensolved = true;
				doCountdowntoNextSet("INCORRECT! They are the SAME. Next face in ");
				this.updateScores(false);
			}
		} else {
			cC.conversation.newsendInstructionToParticipant(sender, "Incorrect command:");
			cC.conversation.newsendInstructionToParticipant(sender, "Choose '/s' if you think they are the same");
			cC.conversation.newsendInstructionToParticipant(sender, "Choose '/d' if you think they are different");
		}
	}

	private int getRandomNumberBetween(int lower_inclusive, int upper_exclusive, int exlusive) {
		int value = lower_inclusive + r.nextInt(upper_exclusive - lower_inclusive);
		while (value == exlusive) {
			value = lower_inclusive + r.nextInt(upper_exclusive - lower_inclusive);
		}
		return value;
	}

	public void updateScores(boolean success) {
		if (success) {
			int scorepAsuccess = this.getScoreCORRECT(pA);
			this.htscoreCORRECT.putObject(pA, scorepAsuccess + 1);

			int scorepBsuccess = this.getScoreCORRECT(pB);
			this.htscoreCORRECT.putObject(pB, scorepAsuccess + 1);

		} else {
			int scorepAsuccess = this.getScoreINCORRECT(pA);
			this.htscoreINCORRECT.putObject(pA, scorepAsuccess + 1);

			int scorepBsuccess = this.getScoreINCORRECT(pB);
			this.htscoreINCORRECT.putObject(pB, scorepAsuccess + 1);
		}

		cC.conversation.newsaveAdditionalRowOfDataToSpreadsheetOfTurns("gamedata_score_correct", pA,
				"" + this.getScoreCORRECT(pA));
		cC.conversation.newsaveAdditionalRowOfDataToSpreadsheetOfTurns("gamedata_score_correct", pB,
				"" + this.getScoreCORRECT(pB));
		cC.conversation.newsaveAdditionalRowOfDataToSpreadsheetOfTurns("gamedata_score_incorrect", pA,
				"" + this.getScoreINCORRECT(pA));
		cC.conversation.newsaveAdditionalRowOfDataToSpreadsheetOfTurns("gamedata_score_incorrect", pB,
				"" + this.getScoreINCORRECT(pB));

	}

	public int getScoreCORRECT(Participant p) {
		return (int) this.htscoreCORRECT.getObject(p);
	}

	public int getScoreINCORRECT(Participant p) {
		return (int) this.htscoreINCORRECT.getObject(p);
	}

	public Vector getAdditionalValues(Participant p) {

		String stimulusself = "";
		String stimulusother = "";
		Vector avs = new Vector();
		if (pA == p) {
			stimulusself = this.pA_Imagename;
			stimulusother = this.pB_Imagename;
			AttribVal av0 = new AttribVal("gamenumber", this.gamenumber);
			AttribVal av1 = new AttribVal("correct", this.getScoreCORRECT(pA));
			AttribVal av2 = new AttribVal("incorrect", this.getScoreINCORRECT(pA));
			AttribVal av3 = new AttribVal("stimulusself", stimulusself);
			AttribVal av4 = new AttribVal("stimulusother", stimulusself);
			avs.addElement(av1);
			avs.addElement(av2);
			avs.addElement(av3);
			avs.addElement(av4);
		}
		if (pB == p) {
			stimulusself = this.pB_Imagename;
			stimulusother = this.pA_Imagename;
			AttribVal av0 = new AttribVal("gamenumber", this.gamenumber);
			AttribVal av1 = new AttribVal("correct", this.getScoreCORRECT(pB));
			AttribVal av2 = new AttribVal("incorrect", this.getScoreINCORRECT(pB));
			AttribVal av3 = new AttribVal("stimulusself", stimulusself);
			AttribVal av4 = new AttribVal("stimulusother", stimulusself);
			avs.addElement(av1);
			avs.addElement(av2);
			avs.addElement(av3);
			avs.addElement(av4);
		}

		return avs;
	}

}
