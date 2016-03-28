package ua.ii.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PredictWindow {

	private JFrame frame;

	public PredictWindow(String date, int result) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 360, 260);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblDate = new JLabel(String.format("Date: %s", date));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(136, 62, 55, 16);
		frame.getContentPane().add(lblDate);

		JLabel lblResult = new JLabel(String.format("Passangers %d", result));
		lblResult.setBounds(136, 127, 46, 14);
		frame.getContentPane().add(lblResult);

	}

	public JFrame getFrame() {
		return frame;
	}
}
