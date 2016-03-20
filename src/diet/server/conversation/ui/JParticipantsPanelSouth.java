/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.server.conversation.ui;

import diet.server.Conversation;
import diet.server.ConversationController.DefaultConversationController;
import diet.server.ConversationController.ui.CustomDialog;
import diet.server.Participant;
import java.util.Vector;

/**
 *
 * @author gj
 */
public class JParticipantsPanelSouth extends javax.swing.JPanel {

	Conversation c;
	JParticipantsPanel jpp;

	/**
	 * Creates new form JParticipantsPanelSouth
	 */
	public JParticipantsPanelSouth(Conversation c, JParticipantsPanel jpp) {
		this.c = c;
		this.jpp = jpp;
		initComponents();
		jTextField4.setText(DefaultConversationController.config.server_mainwindow_instructionToClient);
		jTextField3.setText(DefaultConversationController.config.server_mainwindow_websiteURL);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jButton9 = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		jButton2 = new javax.swing.JButton();
		jTextField4 = new javax.swing.JTextField();
		jPanel2 = new javax.swing.JPanel();
		jTextField3 = new javax.swing.JTextField();
		jButton8 = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jButton11 = new javax.swing.JButton();
		jButton12 = new javax.swing.JButton();
		jPanel6 = new javax.swing.JPanel();
		jButton19 = new javax.swing.JButton();
		jButton20 = new javax.swing.JButton();
		jPanel7 = new javax.swing.JPanel();
		jButton21 = new javax.swing.JButton();
		jButton22 = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		jButton23 = new javax.swing.JButton();

		jButton9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton9.setText("open");

		setMinimumSize(new java.awt.Dimension(742, 179));

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Send Instruction to client(s)",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 0, 14))); // NOI18N

		jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton2.setText("send");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jTextField4.setText("Please wait for further instructions - thanks!");
		jTextField4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField4ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButton2,
								javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jButton2)));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Load web-page on client(s)",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 0, 14))); // NOI18N
		jPanel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

		jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jTextField3.setText("http://www.qualtrix.com");

		jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton8.setText("open");
		jButton8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton8ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addComponent(jTextField3)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButton8,
								javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jButton8)));

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Scrolling",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 0, 14))); // NOI18N
		jPanel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

		jButton11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton11.setText("enable");
		jButton11.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton11ActionPerformed(evt);
			}
		});

		jButton12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton12.setText("disable");
		jButton12.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton12ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup()
						.addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel3Layout.createSequentialGroup()
								.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jButton11).addComponent(jButton12))
						.addGap(0, 0, Short.MAX_VALUE)));

		jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Text entry",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 0, 14))); // NOI18N
		jPanel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

		jButton19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton19.setText("enable");
		jButton19.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton19ActionPerformed(evt);
			}
		});

		jButton20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton20.setText("disable");
		jButton20.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton20ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup()
						.addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 79,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 82,
								javax.swing.GroupLayout.PREFERRED_SIZE)));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup()
						.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton19).addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE,
										27, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(0, 0, Short.MAX_VALUE)));

		jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dialogue history",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 0, 14))); // NOI18N
		jPanel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

		jButton21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton21.setText("visible");
		jButton21.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton21ActionPerformed(evt);
			}
		});

		jButton22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton22.setText("block");
		jButton22.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton22ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout
				.setHorizontalGroup(
						jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel7Layout.createSequentialGroup()
										.addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 79,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(1, 1, 1)));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jButton21).addComponent(jButton22)));

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reset",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 0, 14))); // NOI18N

		jButton23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton23.setText("kill client(s)");
		jButton23.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton23ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE)
				.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE)
				.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup()
								.addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
				.addGap(0, 0, 0)));
	}// </editor-fold>//GEN-END:initComponents

	public Vector getSelected() {
		Vector participantsselected = new Vector();
		int[] selectedrows = jpp.jpt.getSelectedRows();
		for (int i = 0; i < selectedrows.length; i++) {
			String s = (String) jpp.jpt.getModel().getValueAt(selectedrows[i], 0);
			Participant p = c.getParticipants().findParticipantWithEmail(s);
			System.err.println("DETECTEDSELECTED" + p.getUsername());
			participantsselected.add(p);
		}
		return participantsselected;
	}

	private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField4ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField4ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		Vector pselected = this.getSelected();
		for (int i = 0; i < pselected.size(); i++) {
			Participant p = (Participant) pselected.elementAt(i);
			c.newsendInstructionToParticipant(p, this.jTextField4.getText());
			Conversation.printWSln("Main",
					"Sending instructions " + jTextField4.getText() + " to " + p.getParticipantID());
		}
	}// GEN-LAST:event_jButton2ActionPerformed

	private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton19ActionPerformed
		Vector pselected = this.getSelected();
		for (int i = 0; i < pselected.size(); i++) {
			Participant p = (Participant) pselected.elementAt(i);
			c.changeClientInterface_enableTextEntry(p);
			Conversation.printWSln("Main", "Enabling text entry of " + p.getParticipantID());
		}

	}// GEN-LAST:event_jButton19ActionPerformed

	private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton20ActionPerformed
		Vector pselected = this.getSelected();
		for (int i = 0; i < pselected.size(); i++) {
			Participant p = (Participant) pselected.elementAt(i);
			c.changeClientInterface_disableTextEntry(p);
			Conversation.printWSln("Main", "Disabling text entry of " + p.getParticipantID());
		}
	}// GEN-LAST:event_jButton20ActionPerformed

	private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton11ActionPerformed
		Vector pselected = this.getSelected();
		for (int i = 0; i < pselected.size(); i++) {
			Participant p = (Participant) pselected.elementAt(i);
			c.changeClientInterface_enableScrolling(p);
			Conversation.printWSln("Main", "Enabling scrolling for " + p.getParticipantID());
		}
	}// GEN-LAST:event_jButton11ActionPerformed

	private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton12ActionPerformed
		Vector pselected = this.getSelected();
		for (int i = 0; i < pselected.size(); i++) {
			Participant p = (Participant) pselected.elementAt(i);
			c.changeClientInterface_disableScrolling(p);
			Conversation.printWSln("Main", "Disabling scrolling for " + p.getParticipantID());
		}
	}// GEN-LAST:event_jButton12ActionPerformed

	private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton21ActionPerformed
		Vector pselected = this.getSelected();
		for (int i = 0; i < pselected.size(); i++) {
			Participant p = (Participant) pselected.elementAt(i);
			c.changeClientInterface_enableTextDisplay(p);
			Conversation.printWSln("Main", "Making conversation history visible for" + p.getParticipantID());
		}
	}// GEN-LAST:event_jButton21ActionPerformed

	private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton22ActionPerformed
		Vector pselected = this.getSelected();
		for (int i = 0; i < pselected.size(); i++) {
			Participant p = (Participant) pselected.elementAt(i);
			c.changeClientInterface_disableTextDisplay(p);
			Conversation.printWSln("Main", "Blocking the conversation history of " + p.getParticipantID());
		}
	}// GEN-LAST:event_jButton22ActionPerformed

	private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton23ActionPerformed
		String[] options = { "kill the clients", "cancel" };
		String choice = CustomDialog.show2OptionDialog(options, "Do you really want to kill the clients?",
				"Kill the clients?");
		if (choice.equalsIgnoreCase(options[0])) {
			Vector pselected = this.getSelected();
			for (int i = 0; i < pselected.size(); i++) {
				Participant p = (Participant) pselected.elementAt(i);
				c.killClientOnRemoteMachine(p);
				Conversation.printWSln("Main", "Killed client " + p.getParticipantID());
			}
		}
	}// GEN-LAST:event_jButton23ActionPerformed

	private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton8ActionPerformed
		Vector pselected = this.getSelected();
		for (int i = 0; i < pselected.size(); i++) {
			Participant p = (Participant) pselected.elementAt(i);
			c.openClientBrowserToWebpage(p, jTextField3.getText());

			Conversation.printWSln("Main",
					"Opening webpage " + jTextField3.getText() + " on machine of " + p.getParticipantID());
		}
	}// GEN-LAST:event_jButton8ActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton11;
	private javax.swing.JButton jButton12;
	private javax.swing.JButton jButton19;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton20;
	private javax.swing.JButton jButton21;
	private javax.swing.JButton jButton22;
	private javax.swing.JButton jButton23;
	private javax.swing.JButton jButton8;
	private javax.swing.JButton jButton9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	// End of variables declaration//GEN-END:variables
}
