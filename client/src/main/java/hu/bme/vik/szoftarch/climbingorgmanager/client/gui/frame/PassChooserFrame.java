package hu.bme.vik.szoftarch.climbingorgmanager.client.gui.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import hu.bme.vik.szoftarch.climbingorgmanager.client.controller.Controller;

/**
 * Created by Dani on 2014.11.21..
 */
public class PassChooserFrame extends JFrame {

	public PassChooserFrame() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		final Container contentPane = getContentPane();

		JPanel inputPanel = new JPanel();
		contentPane.add(inputPanel, BorderLayout.CENTER);
		inputPanel.setLayout(new GridBagLayout());
		inputPanel.setBorder(new TitledBorder("Choose a pass"));

		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 0, 5);
//		constraints.anchor = GridBagConstraints.WEST;

		final JRadioButton pass1RadioButton = new JRadioButton("Pass 10/2 months");
		final JRadioButton pass2RadioButton = new JRadioButton("Pass 20/4 months");
		final JRadioButton pass3RadioButton = new JRadioButton("Pass 20/6 months");

		final ButtonGroup validityGroup = new ButtonGroup();
		validityGroup.add(pass1RadioButton);
		validityGroup.add(pass2RadioButton);
		validityGroup.add(pass3RadioButton);

		Box validityBox = Box.createVerticalBox();
		constraints.gridx++;
		inputPanel.add(validityBox, constraints);
		validityBox.add(pass1RadioButton);
		validityBox.add(pass2RadioButton);
		validityBox.add(pass3RadioButton);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(buttonPanel, BorderLayout.PAGE_END);

		JButton okButton = new JButton("OK");
		buttonPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validityGroup.getSelection() != null) {
					int passId = 0;
					if (pass1RadioButton.isSelected()) {
						passId = 2;
					} else if (pass2RadioButton.isSelected()) {
						passId = 3;
					} else if (pass3RadioButton.isSelected()) {
						passId = 4;
					}
					Controller controller = Controller.getInstance();
					controller.buyPass(passId, PassChooserFrame.this);
				}
			}
		});

		pack();
		setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
	}
}
