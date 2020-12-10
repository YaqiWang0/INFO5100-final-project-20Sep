package FinalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class mistake extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

					mistake frame = new mistake();
				
	}

	/**
	 * Create the frame.
	 */
	public mistake() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(204, 170);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Your information is not right!");
		contentPane.add(lblNewLabel, BorderLayout.CENTER);
		
		JButton confButton = new JButton("OK");
		confButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();// click OK to close
			}
		});
		contentPane.add(confButton, BorderLayout.SOUTH);
		
		setVisible(true);
	}

}
