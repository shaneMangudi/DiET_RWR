/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diet.task.stimuliset;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author sre
 */
public class JConfidenceTaskControllerInitialFrame2WAY extends javax.swing.JFrame {

	Vector v = new Vector();

	/**
	 * Creates new form JConfidenceTaskControllerInitialFrame
	 */
	public JConfidenceTaskControllerInitialFrame2WAY(Vector v) {
		this.v = v;
		initComponents();
		this.setVisible(true);

	}

	public String getSAMEDIFFTRANSTIONS() {
		return (String) this.jComboBox1.getSelectedItem();
	}

	public int getPracticeMinutes() {
		int value = -9999;
		try {
			value = Integer.parseInt((String) this.jComboBox2.getSelectedItem());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public int getConditionMinutes() {
		int value = -9999;
		try {
			value = Integer.parseInt((String) this.jComboBox3.getSelectedItem());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public boolean getAddExtraTimetoEnd() {
		return this.jCheckBox1.isSelected();
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

		jLabel1 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jComboBox3 = new javax.swing.JComboBox();
		jLabel4 = new javax.swing.JLabel();
		jComboBox2 = new javax.swing.JComboBox();
		jLabel3 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jCheckBox1 = new javax.swing.JCheckBox();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText(
				"These values must be set before logging in participants, otherwise it could go non-deterministic");

		jComboBox1.setModel(new DefaultComboBoxModel(v));
		jComboBox1.setSelectedIndex(0);

		jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
		jComboBox3.setSelectedIndex(4);

		jLabel4.setText("Add extra time in last trial");

		jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
		jComboBox2.setSelectedIndex(4);

		jLabel3.setText("Practice phase:");

		jLabel2.setText("Transitions:");

		jLabel6.setText("minutes");

		jLabel5.setText("Trial length");

		jLabel7.setText("minutes");

		jCheckBox1.setSelected(true);
		jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox1ActionPerformed(evt);
			}
		});

		jButton1.setText("OK");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
								jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
								javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 144,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
								.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 105,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 105,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(37, 37, 37)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(jLabel7).addComponent(jLabel6))
								.addGap(0, 645, Short.MAX_VALUE))
								.addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jCheckBox1)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel6)
								.addGroup(layout.createSequentialGroup().addGap(1, 1, 1)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel3))))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel5).addComponent(jLabel7))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel4).addComponent(jCheckBox1)).addGap(0, 0, Short.MAX_VALUE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jButton1)))
						.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jCheckBox1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jCheckBox1ActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		setOKPressed();
		this.setVisible(false);
	}// GEN-LAST:event_jButton1ActionPerformed

	boolean okPRESSED = false;

	public synchronized void setOKPressed() {
		okPRESSED = true;
		this.notifyAll();
	}

	public synchronized void waitFOROK() {
		while (!okPRESSED) {
			try {
				this.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

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
			java.util.logging.Logger.getLogger(JConfidenceTaskControllerInitialFrame2WAY.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(JConfidenceTaskControllerInitialFrame2WAY.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(JConfidenceTaskControllerInitialFrame2WAY.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(JConfidenceTaskControllerInitialFrame2WAY.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/*
		 * Create and display the form
		 */
		// JConfidenceTaskControllerInitialFrame jctcif = new
		// JConfidenceTaskControllerInitialFrame();
		// jctcif.waitFOROK();
		// System.exit(-5);

	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JCheckBox jCheckBox1;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	// End of variables declaration//GEN-END:variables
}
