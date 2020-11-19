package txstate.cs.ai.kala;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class GamePlay {
	
	int depth;
	int player;
	int EvalChoice1;
	int EvalChoice2;
	
	ArrayList<Board> path = new ArrayList<Board>();
	MiniMaxABStruct s = new MiniMaxABStruct();
	
	long totalTime = 0;
	int count = 0;
	Board board;
	StringBuilder str = new StringBuilder();
	public GameGUI newFrame = new GameGUI();

	public static void main(String[] args) {
		
		StaticGUI gui1 = new StaticGUI();
		OptionsGUI optionsFrame = new OptionsGUI();
		gui1.frmKalahGame.setVisible(true);

		
		gui1.mainbuttonlist.get("alphabetasearch").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionsFrame.setVisible(true);
				optionsFrame.start.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GamePlay obj = new GamePlay();
						obj.SetOptions(Integer.parseInt(optionsFrame.txtPlayer.getText()), Integer.parseInt(optionsFrame.txtDepth.getText()), Integer.parseInt(optionsFrame.txtEval1.getText()), Integer.parseInt(optionsFrame.txtEval2.getText()));
						obj.AlphaBeta();
					}
				});
			}
		});
		
		gui1.mainbuttonlist.get("minmaxab").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionsFrame.setVisible(true);
				optionsFrame.start.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GamePlay obj = new GamePlay();
						obj.SetOptions(Integer.parseInt(optionsFrame.txtPlayer.getText()), Integer.parseInt(optionsFrame.txtDepth.getText()), Integer.parseInt(optionsFrame.txtEval1.getText()), Integer.parseInt(optionsFrame.txtEval2.getText()));
						obj.MinMaxAB();
					}
				});
			}
		});
		
		gui1.mainbuttonlist.get("minalpha").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionsFrame.setVisible(true);
				optionsFrame.start.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GamePlay obj = new GamePlay();
						obj.SetOptions(Integer.parseInt(optionsFrame.txtPlayer.getText()), Integer.parseInt(optionsFrame.txtDepth.getText()), Integer.parseInt(optionsFrame.txtEval1.getText()), Integer.parseInt(optionsFrame.txtEval2.getText()));
						obj.MinMaxABAlphaBeta();
					}
				});
			}
		});
		
		gui1.mainbuttonlist.get("alphamin").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionsFrame.setVisible(true);
				optionsFrame.start.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GamePlay obj = new GamePlay();
						obj.SetOptions(Integer.parseInt(optionsFrame.txtPlayer.getText()), Integer.parseInt(optionsFrame.txtDepth.getText()), Integer.parseInt(optionsFrame.txtEval1.getText()), Integer.parseInt(optionsFrame.txtEval2.getText()));
						obj.AlphaBetaMinMaxAB();
					}
				});
			}
		});
		
		gui1.mainbuttonlist.get("uservsuser").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionsFrame.setVisible(true);
				optionsFrame.start.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new GamePlay().SetOptions(Integer.parseInt(optionsFrame.txtPlayer.getText()), Integer.parseInt(optionsFrame.txtDepth.getText()), Integer.parseInt(optionsFrame.txtEval1.getText()), Integer.parseInt(optionsFrame.txtEval2.getText()));
						new GamePlay().UservsUser();
					}
				});
			}
		});
		

	}
	
	public void SetOptions(int tempPlayer, int tempDepth, int tempEval1, int tempEval2)
	{
		depth = tempDepth;
		player = tempPlayer;
		EvalChoice1 = tempEval1;
		EvalChoice2 = tempEval2;
		
		
	}
	
	public void SetEvalForPlayer(GameEngine tempGameEngine)
	{
		tempGameEngine.EvalChoice=((tempGameEngine.board.player) == 0)?EvalChoice1:EvalChoice2;
	}
	
	public void MinMaxAB()
	{
		newFrame.gametype.setText("MINMAX-AB vs MINMAX-AB");
		
		GameEngine k = new GameEngine(depth, player);
		System.out.println("\n\nInitial State: ");
		newFrame.txtArea.append("Initial State: ");
		k.displayBoard(newFrame);
		path.add(k.getBoard());
		//k.EvalChoice=EvalChoice;
		
		int moved=0;
		int count1=1;
		int count2=1;
		boolean playerchanged=false;
		int val=0;
		
		long start_s=System.currentTimeMillis();
		while(!k.isGameOver())
		{
			
			SetEvalForPlayer(k);
			
			s = k.miniMaxABRK(k.getBoard(), 0, k.getPlayer(), 100, -100);
			if(s.boardPath.size() > 0)
			{
				moved=s.boardPath.get(1).expanded;
				val=s.value;
				k.setBoard(s.boardPath.get(1), k.getPlayer());
				path.add(k.getBoard());
				playerchanged=s.boardPath.get(1).isPlayerChanged;
							
				
			}
			
			System.out.println("Player P"+k.getPlayer()+" expanded Node #"+moved+" with Evl: "+val);
			newFrame.txtArea.append("Player P"+k.getPlayer()+" expanded Node #"+moved+" with Evl: "+val);
			System.out.println("New Board position is: ");
			newFrame.txtArea.append("\nNew Board position is: ");
			k.displayBoard(newFrame);
			
			count1=(k.getPlayer()==0)?count1+1:count1;
			count2=(k.getPlayer()==1)?count2+1:count2;
			
			if(playerchanged)
			{
				k.setNextPlayer(k.getPlayer());
			}
			else
			{
				System.out.println("Free move!");
				newFrame.txtArea.append("\nFree move!");
			}
			
				
		}
		if(k.getPlayer1Kalah() == k.getPlayer2Kalah())
		{
			System.out.println("Game is drawn");
			newFrame.txtArea.append("Game is drawn\n");
			
		}
		else if(k.getPlayer1Kalah() > k.getPlayer2Kalah())
		{
			System.out.println("\nPlayer P"+k.getPlayer()+" won the game!");
			newFrame.txtArea.append("\nPlayer P"+k.getPlayer()+" won the game!\n");
		}
		else
		{
			System.out.println("\nPlayer P"+k.getPlayer()+" won the game!");
			newFrame.txtArea.append("\nPlayer P"+k.getPlayer()+" won the game!\n");
			
		}
		
		long stop_s=System.currentTimeMillis();
		totalTime += (stop_s-start_s);
		
		System.out.println("Number of nodes generated by Player#1 : "+k.numberofnodesgenerated1);
		newFrame.txtArea.append("Number of nodes generated by Player#1 : "+k.numberofnodesgenerated1);
		System.out.println("\nNumber of nodes generated by Player#2 : "+k.numberofnodesgenerated2);
		newFrame.txtArea.append("\nNumber of nodes generated by Player#2 : "+k.numberofnodesgenerated2);
		System.out.println("\nNumber of nodes expanded by Player#1 : "+count1);
		newFrame.txtArea.append("\nNumber of nodes expanded by Player#1 : "+count1);
		System.out.println("\nNumber of nodes expanded by Player#2 : "+count2);
		newFrame.txtArea.append("\nNumber of nodes expanded by Player#2 : "+count2);
		System.out.println("\nExecution time : "+(totalTime)+" milliseconds");
		newFrame.txtArea.append("\nExecution time : "+(totalTime)+" milliseconds");
		System.out.println("Total memory occupied : "+(k.numberofnodesgenerated1+k.numberofnodesgenerated2) * 4+" bytes");
		newFrame.txtArea.append("\nTotal memory occupied : "+(k.numberofnodesgenerated1+k.numberofnodesgenerated2) * 4+" bytes");
		
		newFrame.setVisible(true);
	}
	
	public void AlphaBeta()
	{
		newFrame.gametype.setText("ALPHABETA SEARCH vs ALPHABETA SEARCH");
		
		GameEngine k = new GameEngine(depth, player);
		int moved=0;
		int count1=1;
		int count2=1;
		boolean playerchanged=false;
		
		//k.EvalChoice=EvalChoice;
		
		long start_s=System.currentTimeMillis();
		
		System.out.println("\nInitial state: \n"+depth+player);
		newFrame.txtArea.append("\nInitial State: \n");
		k.displayBoard(newFrame);
		
		while(!k.isGameOver())
		{
				
			SetEvalForPlayer(k);

			Board board = k.alphaBetaSearch(k.getBoard());
			
			k.setBoard(board, board.getPlayer());
			moved=board.expanded;
			playerchanged=board.isPlayerChanged;
			
			System.out.println("Player P"+k.board.getPlayer()+" expanded Node #"+moved);
			newFrame.txtArea.append("Player P"+k.board.getPlayer()+" expanded Node #"+moved);
			System.out.println("New Board position is: ");
			newFrame.txtArea.append("\nNew Board position is: ");
			k.displayBoard(newFrame);
			
			count1=(k.getPlayer()==0)?count1+1:count1;
			count2=(k.getPlayer()==1)?count2+1:count2;
			
			if(playerchanged)
			{
				k.setNextPlayer(k.getPlayer());
			}
			else
			{
				System.out.println("Free move!");
				newFrame.txtArea.append("\nFree move!");
			}
				
			
		}
		
		if(k.getPlayer1Kalah() == k.getPlayer2Kalah())
		{
			System.out.println("Game is drawn");
			newFrame.txtArea.append("Game is drawn\n");
			
		}
		else if(k.getPlayer1Kalah() > k.getPlayer2Kalah())
		{
			System.out.println("\nPlayer P"+k.getPlayer()+" won the game!");
			newFrame.txtArea.append("\nPlayer P"+k.getPlayer()+" won the game!\n");
		}
		else
		{
			System.out.println("\nPlayer P"+k.getPlayer()+" won the game!");
			newFrame.txtArea.append("\nPlayer P"+k.getPlayer()+" won the game!\n");
			
		}
		
		long stop_s=System.currentTimeMillis();
		totalTime += (stop_s-start_s);
		
		System.out.println("Number of nodes generated by Player#1 : "+k.numberofnodesgenerated1);
		newFrame.txtArea.append("Number of nodes generated by Player#1 : "+k.numberofnodesgenerated1);
		System.out.println("\nNumber of nodes generated by Player#2 : "+k.numberofnodesgenerated2);
		newFrame.txtArea.append("\nNumber of nodes generated by Player#2 : "+k.numberofnodesgenerated2);
		System.out.println("\nNumber of nodes expanded by Player#1 : "+count1);
		newFrame.txtArea.append("\nNumber of nodes expanded by Player#1 : "+count1);
		System.out.println("\nNumber of nodes expanded by Player#2 : "+count2);
		newFrame.txtArea.append("\nNumber of nodes expanded by Player#2 : "+count2);
		System.out.println("\nExecution time : "+(totalTime)+" milliseconds");
		newFrame.txtArea.append("\nExecution time : "+(totalTime)+" milliseconds");
		System.out.println("Total memory occupied : "+(k.numberofnodesgenerated1+k.numberofnodesgenerated2) * 4+" bytes");
		newFrame.txtArea.append("\nTotal memory occupied : "+(k.numberofnodesgenerated1+k.numberofnodesgenerated2) * 4+" bytes");
		
		newFrame.setVisible(true);

	}
	
	
	public void MinMaxABAlphaBeta()
	{
		newFrame.gametype.setText("MINMAX-AB vs Alpha-Beta Search");
		
		GameEngine k = new GameEngine(depth, player);
		System.out.println("\n\nInitial State: ");
		newFrame.txtArea.append("Initial State: ");
		k.displayBoard(newFrame);
		path.add(k.getBoard());
		//k.EvalChoice=EvalChoice;
		
		int moved=0;
		int count1=1;
		int count2=1;
		boolean playerchanged=false;
		int val=0;
		
		long start_s=System.currentTimeMillis();
		while(!k.isGameOver())
		{
			SetEvalForPlayer(k);
			
			s = k.miniMaxABRK(k.getBoard(), 0, k.getPlayer(), 100, -100);
			if(s.boardPath.size() > 0)
			{
				moved=s.boardPath.get(1).expanded;
				val=s.value;
				k.setBoard(s.boardPath.get(1), k.getPlayer());
				path.add(k.getBoard());
				playerchanged=s.boardPath.get(1).isPlayerChanged;
							
				
			}
			
			System.out.println("Player P"+k.getPlayer()+" expanded Node #"+moved+" with Evl: "+val);
			newFrame.txtArea.append("Player P"+k.getPlayer()+" expanded Node #"+moved+" with Evl: "+val);
			System.out.println("New Board position is: ");
			newFrame.txtArea.append("\nNew Board position is: ");
			k.displayBoard(newFrame);
			
			count1=(k.getPlayer()==0)?count1+1:count1;
			count2=(k.getPlayer()==1)?count2+1:count2;
			
			if(playerchanged)
			{
				k.setNextPlayer(k.getPlayer());
				
				Board board = k.alphaBetaSearch(k.getBoard());
				
				k.setBoard(board, board.getPlayer());
				moved=board.expanded;
				playerchanged=board.isPlayerChanged;
				
				System.out.println("Player P"+k.board.getPlayer()+" expanded Node #"+moved);
				newFrame.txtArea.append("Player P"+k.board.getPlayer()+" expanded Node #"+moved);
				System.out.println("New Board position is: ");
				newFrame.txtArea.append("\nNew Board position is: ");
				k.displayBoard(newFrame);
				
				count1=(k.getPlayer()==0)?count1+1:count1;
				count2=(k.getPlayer()==1)?count2+1:count2;
				
			}
			else
			{
				System.out.println("Free move!");
				newFrame.txtArea.append("\nFree move!");
			}
			
				
		}
		if(k.getPlayer1Kalah() == k.getPlayer2Kalah())
		{
			System.out.println("Game is drawn");
			newFrame.txtArea.append("Game is drawn\n");
			
		}
		else if(k.getPlayer1Kalah() > k.getPlayer2Kalah())
		{
			System.out.println("\nPlayer P"+k.getPlayer()+" won the game!");
			newFrame.txtArea.append("\nPlayer P"+k.getPlayer()+" won the game!\n");
		}
		else
		{
			System.out.println("\nPlayer P"+k.getPlayer()+" won the game!");
			newFrame.txtArea.append("\nPlayer P"+k.getPlayer()+" won the game!\n");
			
		}
		
		long stop_s=System.currentTimeMillis();
		totalTime += (stop_s-start_s);
		
		System.out.println("Number of nodes generated by Player#1 : "+k.numberofnodesgenerated1);
		newFrame.txtArea.append("Number of nodes generated by Player#1 : "+k.numberofnodesgenerated1);
		System.out.println("\nNumber of nodes generated by Player#2 : "+k.numberofnodesgenerated2);
		newFrame.txtArea.append("\nNumber of nodes generated by Player#2 : "+k.numberofnodesgenerated2);
		System.out.println("\nNumber of nodes expanded by Player#1 : "+count1);
		newFrame.txtArea.append("\nNumber of nodes expanded by Player#1 : "+count1);
		System.out.println("\nNumber of nodes expanded by Player#2 : "+count2);
		newFrame.txtArea.append("\nNumber of nodes expanded by Player#2 : "+count2);
		System.out.println("\nExecution time : "+(totalTime)+" milliseconds");
		newFrame.txtArea.append("\nExecution time : "+(totalTime)+" milliseconds");
		System.out.println("Total memory occupied : "+(k.numberofnodesgenerated1+k.numberofnodesgenerated2) * 4+" bytes");
		newFrame.txtArea.append("\nTotal memory occupied : "+(k.numberofnodesgenerated1+k.numberofnodesgenerated2) * 4+" bytes");
		
		newFrame.setVisible(true);
	}

	
	
	public void AlphaBetaMinMaxAB()
	{
		newFrame.gametype.setText("ALPHABETA SEARCH vs ALPHABETA SEARCH");
		
		GameEngine k = new GameEngine(depth, player);
		int moved=0;
		int count1=1;
		int count2=1;
		int val=0;
		boolean playerchanged=false;
		
		//k.EvalChoice=EvalChoice;
		
		long start_s=System.currentTimeMillis();
		
		System.out.println("\nInitial state: \n"+depth+player);
		newFrame.txtArea.append("\nInitial State: \n");
		k.displayBoard(newFrame);
		
		while(!k.isGameOver())
		{
				

			SetEvalForPlayer(k);
			
			Board board = k.alphaBetaSearch(k.getBoard());
			
			k.setBoard(board, board.getPlayer());
			moved=board.expanded;
			playerchanged=board.isPlayerChanged;
			
			System.out.println("Player P"+k.board.getPlayer()+" expanded Node #"+moved);
			newFrame.txtArea.append("Player P"+k.board.getPlayer()+" expanded Node #"+moved);
			System.out.println("New Board position is: ");
			newFrame.txtArea.append("\nNew Board position is: ");
			k.displayBoard(newFrame);
			
			count1=(k.getPlayer()==0)?count1+1:count1;
			count2=(k.getPlayer()==1)?count2+1:count2;
			
			if(playerchanged)
			{
				k.setNextPlayer(k.getPlayer());
				s = k.miniMaxABRK(k.getBoard(), 0, k.getPlayer(), 100, -100);
				if(s.boardPath.size() > 0)
				{
					moved=s.boardPath.get(1).expanded;
					val=s.value;
					k.setBoard(s.boardPath.get(1), k.getPlayer());
					path.add(k.getBoard());
					playerchanged=s.boardPath.get(1).isPlayerChanged;
								
					
				}
				
				System.out.println("Player P"+k.getPlayer()+" expanded Node #"+moved+" with Evl: "+val);
				newFrame.txtArea.append("Player P"+k.getPlayer()+" expanded Node #"+moved+" with Evl: "+val);
				System.out.println("New Board position is: ");
				newFrame.txtArea.append("\nNew Board position is: ");
				k.displayBoard(newFrame);
				
				count1=(k.getPlayer()==0)?count1+1:count1;
				count2=(k.getPlayer()==1)?count2+1:count2;
				
			}
			else
			{
				System.out.println("Free move!");
				newFrame.txtArea.append("\nFree move!");
			}
				
			
		}
		
		if(k.getPlayer1Kalah() == k.getPlayer2Kalah())
		{
			System.out.println("Game is drawn");
			newFrame.txtArea.append("Game is drawn\n");
			
		}
		else if(k.getPlayer1Kalah() > k.getPlayer2Kalah())
		{
			System.out.println("\nPlayer P"+k.getPlayer()+" won the game!");
			newFrame.txtArea.append("\nPlayer P"+k.getPlayer()+" won the game!\n");
		}
		else
		{
			System.out.println("\nPlayer P"+k.getPlayer()+" won the game!");
			newFrame.txtArea.append("\nPlayer P"+k.getPlayer()+" won the game!\n");
			
		}
		
		long stop_s=System.currentTimeMillis();
		totalTime += (stop_s-start_s);
		
		System.out.println("Number of nodes generated by Player#1 : "+k.numberofnodesgenerated1);
		newFrame.txtArea.append("Number of nodes generated by Player#1 : "+k.numberofnodesgenerated1);
		System.out.println("\nNumber of nodes generated by Player#2 : "+k.numberofnodesgenerated2);
		newFrame.txtArea.append("\nNumber of nodes generated by Player#2 : "+k.numberofnodesgenerated2);
		System.out.println("\nNumber of nodes expanded by Player#1 : "+count1);
		newFrame.txtArea.append("\nNumber of nodes expanded by Player#1 : "+count1);
		System.out.println("\nNumber of nodes expanded by Player#2 : "+count2);
		newFrame.txtArea.append("\nNumber of nodes expanded by Player#2 : "+count2);
		System.out.println("\nExecution time : "+(totalTime)+" milliseconds");
		newFrame.txtArea.append("\nExecution time : "+(totalTime)+" milliseconds");
		System.out.println("Total memory occupied : "+(k.numberofnodesgenerated1+k.numberofnodesgenerated2) * 4+" bytes");
		newFrame.txtArea.append("\nTotal memory occupied : "+(k.numberofnodesgenerated1+k.numberofnodesgenerated2) * 4+" bytes");
		
		newFrame.setVisible(true);

	}
	
	public void UservsUser()
	{
		newFrame.gametype.setText("Random Bot vs Random Bot");
		
		newFrame.setVisible(true);
		
		GameEngine k = new GameEngine();
		k.init();
		System.out.println("\nInitial state: \n");
		newFrame.txtArea.append("\nInitial State: \n");
		k.displayBoard(newFrame);
		k.playUserUser(newFrame);
			
	}

}
