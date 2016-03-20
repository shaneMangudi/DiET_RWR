/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diet.keylogging;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author sre
 */
public class JKeyloggingChatFrame extends javax.swing.JFrame {

	Keylogging kl;
	String username = "";

	public JKeyloggingChatFrame(String username, Keylogging kl) {
		this();
		this.username = username;
		this.setTitle("Chat v2.1 - " + username);
		this.kl = kl;
		this.pack();
		this.jTextPane2.requestFocusInWindow();
		this.validate();
		this.repaint();
	}

	/**
	 * Creates new form JChatFrame
	 */
	public JKeyloggingChatFrame() {

		initComponents();
		this.setLocationRelativeTo(null);
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

		jScrollPane1 = new javax.swing.JScrollPane();
		jTextPane1 = new javax.swing.JTextPane();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextPane2 = new javax.swing.JTextPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jScrollPane1.setAutoscrolls(true);
		jScrollPane1.setFocusable(false);

		jTextPane1.setFocusable(false);
		jScrollPane1.setViewportView(jTextPane1);

		jTextPane2.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				jTextPane2KeyPressed(evt);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				jTextPane2KeyReleased(evt);
			}

			public void keyTyped(java.awt.event.KeyEvent evt) {
				jTextPane2KeyTyped(evt);
			}
		});
		jScrollPane2.setViewportView(jTextPane2);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
				.addComponent(jScrollPane2));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(30, 30, 30).addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jTextPane2KeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextPane2KeyPressed
		// TODO add your handling code here:

		String charpressed = "" + evt.getKeyChar();
		String contentsOfTextArea = this.jTextPane2.getText().replace("\n", "");
		System.out.println("KEYPRESSED:" + charpressed + " contents are:" + contentsOfTextArea);
		kl.klo.saveKeypress("Keypressed", evt.getWhen(), charpressed, "" + evt.getKeyCode(), contentsOfTextArea);
	}// GEN-LAST:event_jTextPane2KeyPressed

	private void jTextPane2KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextPane2KeyReleased
		// deleted to make compatible with new version - this will need to be
		// rewritten
	}// GEN-LAST:event_jTextPane2KeyReleased

	private void jTextPane2KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextPane2KeyTyped
		// TODO add your handling code here:
		String charKeyTyped = "" + evt.getKeyChar();
		String contentsOfTextArea = this.jTextPane2.getText().replace("\n", "");
		System.out.println("Keytyped:" + charKeyTyped + " contents are:" + contentsOfTextArea);
		kl.klo.saveKeypress("KeyTyped", evt.getWhen(), charKeyTyped, "" + evt.getKeyCode(), contentsOfTextArea);

	}// GEN-LAST:event_jTextPane2KeyTyped

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/*
		 * Set the Nimbus look and feel
		 */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
		 * html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(JKeyloggingChatFrame.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(JKeyloggingChatFrame.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(JKeyloggingChatFrame.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(JKeyloggingChatFrame.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>

		/*
		 * Create and display the form
		 */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new JKeyloggingChatFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextPane jTextPane1;
	private javax.swing.JTextPane jTextPane2;
	// End of variables declaration//GEN-END:variables
}
