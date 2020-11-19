package txstate.cs.ai.kala;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class GameGUI extends JFrame {

	private JPanel contentPane;
	JButton[] buttonlist = new JButton[14];
	JTextArea txtArea;
	JLabel gametype;

	/**
	 * Create the frame.
	 */
	public GameGUI() {
		
		
		
		setTitle("Welcome to Kalah Game!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton zero = new JButton("");
		zero.setBounds(63, 123, 61, 34);
		contentPane.add(zero);
		buttonlist[0]=zero;
		
		JButton two = new JButton("");
		two.setBounds(197, 81, 44, 23);
		contentPane.add(two);
		buttonlist[2]=two;
		
		JButton one = new JButton("");
		one.setBounds(139, 81, 44, 23);
		contentPane.add(one);
		buttonlist[1]=one;
		
		JButton four = new JButton("");
		four.setBounds(311, 81, 44, 23);
		contentPane.add(four);
		buttonlist[4]=four;
		
		JButton seven = new JButton("");
		seven.setBounds(475, 123, 61, 34);
		contentPane.add(seven);
		buttonlist[7]=seven;
		
		JButton thirteen = new JButton("");
		thirteen.setBounds(139, 168, 44, 23);
		contentPane.add(thirteen);
		buttonlist[13]=thirteen;
		
		JButton eight = new JButton("");
		eight.setBounds(418, 168, 44, 23);
		contentPane.add(eight);
		buttonlist[8]=eight;
		
		JButton ten = new JButton("");
		ten.setBounds(311, 168, 44, 23);
		contentPane.add(ten);
		buttonlist[10]=ten;
		
		JButton eleven = new JButton("");
		eleven.setBounds(257, 168, 44, 23);
		contentPane.add(eleven);
		buttonlist[11]=eleven;
		
		JButton twelve = new JButton("");
		twelve.setBounds(197, 168, 44, 23);
		contentPane.add(twelve);
		buttonlist[12]=twelve;
		
		JButton three = new JButton("");
		three.setBounds(257, 81, 44, 23);
		contentPane.add(three);
		buttonlist[3]=three;
		
		JButton five = new JButton("");
		five.setBounds(365, 81, 44, 23);
		contentPane.add(five);
		buttonlist[5]=five;
		
		JButton six = new JButton("");
		six.setBounds(419, 81, 44, 23);
		contentPane.add(six);
		buttonlist[6]=six;
		
		JButton nine = new JButton("");
		nine.setBounds(365, 168, 44, 23);
		contentPane.add(nine);
		buttonlist[9]=nine;
		
		JLabel lblNewLabel = new JLabel("Program trace:");
		lblNewLabel.setBounds(10, 246, 87, 14);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(-100, 271, 595, 190);
		//contentPane.add(textArea);
		txtArea = textArea;
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 271, 595, 190);
		contentPane.add(scrollPane);
		
		JLabel gametypelabel = new JLabel("");
		gametypelabel.setBounds(197, 11, 212, 14);
		contentPane.add(gametypelabel);
		gametype = gametypelabel;
	}
	
	public JButton[] getButtonList()
	{
		return buttonlist;
	}
}
