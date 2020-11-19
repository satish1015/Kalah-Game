package txstate.cs.ai.kala;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class StaticGUI {

	public JFrame frmKalahGame;
	HashMap<String, JButton> mainbuttonlist = new HashMap<String, JButton>();
	/**
	 * Create the application.
	 */
	public StaticGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKalahGame = new JFrame();
		frmKalahGame.setTitle("Kalah Game");
		frmKalahGame.setBounds(100, 100, 562, 359);
		frmKalahGame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Kalah Game!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(187, 0, 218, 25);
		frmKalahGame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Choose the play:");
		lblNewLabel_1.setBounds(220, 201, 93, 14);
		frmKalahGame.getContentPane().add(lblNewLabel_1);
		
		JButton minmaxab = new JButton("MinMax-AB vs MinMax-AB");
		minmaxab.setBounds(10, 225, 208, 23);
		frmKalahGame.getContentPane().add(minmaxab);
		mainbuttonlist.put("minmaxab", minmaxab);
		
		JButton alphabetasearch = new JButton("AlphaBeta-Search vs AlphaBeta-Search");
		alphabetasearch.setBounds(297, 224, 227, 24);
		frmKalahGame.getContentPane().add(alphabetasearch);
		mainbuttonlist.put("alphabetasearch", alphabetasearch);
		
		JButton uservsuser = new JButton("Random Bot vs Random Bot");
		uservsuser.setBounds(173, 299, 199, 23);
		frmKalahGame.getContentPane().add(uservsuser);
		mainbuttonlist.put("uservsuser", uservsuser);
		
		JButton alphamin = new JButton("AlphaBeta-Search vs MinMax-AB");
		alphamin.setBounds(10, 265, 208, 23);
		frmKalahGame.getContentPane().add(alphamin);
		mainbuttonlist.put("alphamin", alphamin);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(StaticGUI.class.getResource("/txstate/cs/ai/kala/icon.png")));
		lblNewLabel_2.setBounds(173, 22, 171, 173);
		frmKalahGame.getContentPane().add(lblNewLabel_2);
		
		JButton minalpha = new JButton("MinMax-AB vs AlphaBeta-Search ");
		minalpha.setBounds(297, 265, 227, 23);
		frmKalahGame.getContentPane().add(minalpha);
		mainbuttonlist.put("minalpha", minalpha);
	}
}
