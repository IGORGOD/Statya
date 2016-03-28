package ua.ii.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InputWindow {

	private JFrame frame;
	private JTextField dateField;
	private JButton btnGetPredict;

	public InputWindow() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 360, 260);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		dateField = new JTextField();
		dateField.setToolTipText("dd.mm.yyyy");
		dateField.setBounds(142, 91, 122, 28);
		frame.getContentPane().add(dateField);
		dateField.setColumns(10);

		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(63, 97, 55, 16);
		frame.getContentPane().add(lblDate);

		btnGetPredict = new JButton("Get Predict");
		btnGetPredict.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PredictWindow pw = new PredictWindow(dateField.getText(), 1);
			}
		});
		btnGetPredict.setBounds(121, 162, 90, 28);
		frame.getContentPane().add(btnGetPredict);

	}

	public JFrame getFrame() {
		return frame;
	}
}
