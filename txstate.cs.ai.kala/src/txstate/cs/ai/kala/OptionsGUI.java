package txstate.cs.ai.kala;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class OptionsGUI extends JFrame {

	private JPanel contentPane;
	public JTextField txtPlayer;
	public JTextField txtDepth;
	public JTextField txtEval1;
	public JButton start;
	public JTextField txtEval2;
	
	

	/**
	 * Create the frame.
	 */
	public OptionsGUI() {
		setTitle("Kalah Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Kalah Game!");
		lblNewLabel.setBounds(128, 11, 424, 17);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Choose Game config values");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setBounds(138, 39, 166, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Which Player goes first (0/1):");
		lblNewLabel_2.setBounds(10, 83, 168, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Choose the Game Tree depth (2/4):");
		lblNewLabel_2_1.setBounds(10, 128, 197, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Select the Evaluation Function for Player #1 (1/2/3):");
		lblNewLabel_2_1_1.setBounds(10, 177, 264, 14);
		contentPane.add(lblNewLabel_2_1_1);
		
		txtPlayer = new JTextField();
		txtPlayer.setBounds(188, 80, 86, 20);
		contentPane.add(txtPlayer);
		txtPlayer.setColumns(10);
		
		txtDepth = new JTextField();
		txtDepth.setColumns(10);
		txtDepth.setBounds(218, 125, 86, 20);
		contentPane.add(txtDepth);
		
		txtEval1 = new JTextField();
		txtEval1.setColumns(10);
		txtEval1.setBounds(293, 174, 86, 20);
		contentPane.add(txtEval1);
		
		JButton btnNewButton = new JButton("Start!");
		btnNewButton.setBounds(243, 261, 89, 23);
		contentPane.add(btnNewButton);
		start=btnNewButton;
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Select the Evaluation Function  for Player #2(1/2/3):");
		lblNewLabel_2_1_1_1.setBounds(10, 208, 264, 14);
		contentPane.add(lblNewLabel_2_1_1_1);
		
		txtEval2 = new JTextField();
		txtEval2.setColumns(10);
		txtEval2.setBounds(293, 205, 86, 20);
		contentPane.add(txtEval2);
	}
}
