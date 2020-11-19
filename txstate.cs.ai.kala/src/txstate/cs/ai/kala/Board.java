package txstate.cs.ai.kala;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Board {
	
	int player;
	int oppositePlayer;
	int stoneThreshold;
	boolean isPlayerChanged;
	int numberOfStonesInplayerOneKalah;
	int numberOfStonesInplayerTwoKalah;
	int[] board;
	int numPits;
	int trackPosition;
	int expanded;
	String sameKalah;
	
	Board()
	{
		board=new int[14];
		board[0]=0;
		for(int i = 1; i < 7; i++)
		{
			board[i]=6;
		}
		
		board[7]=0;
		for(int i = 8; i < 14; i++)
		{
			board[i]=6;
		}
		numberOfStonesInplayerOneKalah = 0;
		numberOfStonesInplayerTwoKalah = 0;
		numPits = 14;
		player = 1;
		sameKalah = "no";
		stoneThreshold = 36;
		isPlayerChanged = false;
	}
	
	Board(Board MainBoard)
	{
		int[] temparr = new int[14];
		for(int i=0;i<temparr.length;i++)
		{
			temparr[i]=MainBoard.getBoard()[i];
		}
		board=temparr;
		isPlayerChanged=MainBoard.isPlayerChanged;
		numberOfStonesInplayerOneKalah=MainBoard.numberOfStonesInplayerOneKalah;
		numberOfStonesInplayerTwoKalah=MainBoard.numberOfStonesInplayerTwoKalah;
		numPits=MainBoard.numPits;
		oppositePlayer=MainBoard.oppositePlayer;
		player=MainBoard.player;
		sameKalah=MainBoard.sameKalah;
		stoneThreshold=MainBoard.stoneThreshold;
		trackPosition=MainBoard.trackPosition;
	}
	
	//constructor
	Board(int[] vec)
	{
		board = vec;
	}
	//displaying board
	void displayBoard(GameGUI frame)
	{

		String msg="";
		System.out.println("\n\n");
		msg+="\n";
		for(int i = 0; i < 38; i++)
		{
			System.out.print("-");
			msg+="-";
		}
		
		System.out.println();
		msg+="\n";
		System.out.println("P#0");
		System.out.print("| ");
		msg+="P#0";
		msg+="\n| ";

		for(int i = 1; i < 7; i++)
		{
			System.out.print(board[i]+" ");
			msg+=board[i]+" ";
			frame.buttonlist[i].setText(board[i]+"");
		}
		System.out.println("|");
		msg+="|\n";
		System.out.println("|"+board[0]+"           "+board[7]+ "|");
		msg+="|"+board[0]+"          "+board[7]+ "|";
		frame.buttonlist[0].setText(board[0]+"");
		frame.buttonlist[7].setText(board[7]+"");
		msg+="\n";
		System.out.print("| ");
		msg+="| ";
		
		for(int i = 13; i >= 8; i--)
		{
			System.out.print(board[i]+" ");
			msg+=board[i]+" ";
			frame.buttonlist[i].setText(board[i]+"");
		}
		System.out.print("|");
		msg+="|";
		System.out.println();
		msg+="\n";
		System.out.println("P#1");
		msg+="P#1\n";
		

		for(int i = 0; i < 38; i++)
		{
			System.out.print("-");
			msg+="-";
		}
		System.out.println();
		msg+="\n";
		System.out.println("\n\n");
		
		frame.txtArea.append(msg);

	}
	
	//return true if board is empty
	boolean isBoardEmpty()
	{
		boolean result = true;
		for(int i = 0; i < 14; i++)
		{
			if(i != 0 && i != 7)
			{
				if((int)board[i] > 0)
				{
					result = false;
				}
			}
		}
		return result;
	}
	
	//returning board vector
	int[] getBoard()
	{
		return board;
	}
	
	//setting board array for the given board
	void setBoard(int[] b)
	{
		board = b;
	}
	
	//returning current player
	int getPlayer()
	{
		return player;
	}
	
	//returning opposite player
	int getOppositePlayer()
	{
		return 1-player;
	}
	
	//returning board value for the given position
	int getBoardValueByPosition(int pos)
	{
		return board[pos];
	}
	
	//set board value for the given position and value
	void setBoardValueByPosition(int pos,int val)
	{
		board[pos]=val;
	}
	
	//returning kalah position for the given position index
	int getPlayerKalahPositionByIndex(int pos)
	{
		int ret=0;
		
		if(pos >= 1 && pos <= 6)
		{
			ret = 0;
		}
		else if(pos >= 8 && pos <= 13)
		{
			ret = 7;
		}
		
		return ret;
	}
	
	//returning opponent kalah position of the given position
	int getOpponentKalahPositionByIndex(int pos)
	{
		int ret=0;
		
		if(pos >= 1 && pos <= 6)
		{
			ret = 7;
		}
		else if(pos >= 8 && pos <= 13)
		{
			ret = 0;
		}
		
		return ret;
	}
	
	//returning player kalah value of the given position
	int getPlayerKalahValueByIndex(int pos)
	{
		int playerKalahIndex = getPlayerKalahPositionByIndex(pos);
		return board[playerKalahIndex];
	}
	
	//returning opponent kalah value of the given position
	int getOpponentKalahValueByIndex(int pos)
	{
		int opponentKalahIndex = getOpponentKalahPositionByIndex(pos);
		return board[opponentKalahIndex];
	}
	
	//returning number of stones of player one kalah
	int getNumberOfStonesInPlayerOneKalah()
	{
		return numberOfStonesInplayerOneKalah;
	}
	
	//returning number of stones of player two kalah
	int getNumberOfStonesInPlayerTwoKalah()
	{
		return numberOfStonesInplayerTwoKalah;
	}
	
	//returning true if the player turn goes into player kalah
	boolean itsInSameKalah()
	{
		if(sameKalah == "yes")
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//getting notification for player turn kalah position
	void getSameKalahOrNot(int trackPosition, int playerKalah)
	{
		if(trackPosition == playerKalah)
		{
			sameKalah = "yes";
		}
		else
		{
			sameKalah = "no";
		}
	}
	
	//checking kalah game is over or not
	boolean isGameOver()
	{
		if(numberOfStonesInplayerOneKalah > stoneThreshold || numberOfStonesInplayerTwoKalah > stoneThreshold)
		{
			return true;
		}
		
		else if(numberOfStonesInplayerOneKalah == stoneThreshold &&	numberOfStonesInplayerTwoKalah == stoneThreshold)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	//returning true if the current board is equal to the given board
	boolean equal(Board b1)
	{
		int[] b1Vec = b1.getBoard();
		boolean result = true;
		for(int i = 0; i < 14; i++)
		{
			if(board[i] != b1Vec[i])
			{
				result = false;
			}
		}
		return result;
	}
	
	//moving stones from the given position
	void move(int position)
	{
		int playerKalah,opponentKalah, pitInput, posIndex;
		trackPosition = position;
		expanded=position;
		int grabOppositePosition, numOfOppPositionStones;
		int numberOfStones = getBoardValueByPosition(position);
		setBoardValueByPosition(position, 0);
		playerKalah =getPlayerKalahPositionByIndex(position);
		opponentKalah = getOpponentKalahPositionByIndex(position);
		
		int tempPosition=0;
		if(position >= 1 && position <= 6)
		{
			tempPosition = 1;
		}
		
		else if(position >= 8 && position <= 13)
		{
			tempPosition = 8;
		}
		
		if(numberOfStones == 1)
		{
			trackPosition--;
			if(trackPosition < 0)
			{
				trackPosition = numPits-1;
			}
			if(trackPosition != opponentKalah)
			{
				numberOfStones--;
				if((getBoardValueByPosition(trackPosition) == 0 && trackPosition <= 6 && trackPosition >= 1) && tempPosition == 1 && numberOfStones == 0)
				{
					if(getBoardValueByPosition(trackPosition) == 0 && numberOfStones == 0)
					{
						grabOppositePosition = numPits-trackPosition;
						numOfOppPositionStones = getBoardValueByPosition(grabOppositePosition);
						setBoardValueByPosition(grabOppositePosition, 0);
						setBoardValueByPosition(playerKalah, getBoardValueByPosition(playerKalah) + numOfOppPositionStones + 1);
					}
				}
				
				else if(getBoardValueByPosition(trackPosition) == 0 && (trackPosition <= 13 && trackPosition >= 8) && tempPosition == 8 && numberOfStones == 0)
				{
					if(getBoardValueByPosition(trackPosition) == 0 && numberOfStones == 0)
					{
						grabOppositePosition = numPits-trackPosition;
						numOfOppPositionStones = getBoardValueByPosition(grabOppositePosition);
						setBoardValueByPosition(grabOppositePosition, 0);
						setBoardValueByPosition(playerKalah, getBoardValueByPosition(playerKalah) +	numOfOppPositionStones + 1);
					}
				}
				
				else
				{
					setBoardValueByPosition(trackPosition, getBoardValueByPosition(trackPosition) +	1);
				}
			}
		}
		
		else
		{
			while(numberOfStones > 0)
			{
				trackPosition--;
				if(trackPosition < 0)
				{
					trackPosition = numPits-1;
				}
				if(trackPosition != opponentKalah)
				{
					numberOfStones--;
					setBoardValueByPosition(trackPosition, (getBoardValueByPosition(trackPosition) + 1));
				}
			}
		}
		
		numberOfStonesInplayerOneKalah = getBoardValueByPosition(playerKalah);
		numberOfStonesInplayerTwoKalah = getBoardValueByPosition(opponentKalah);
		getSameKalahOrNot(trackPosition, playerKalah);
		
		if(trackPosition != playerKalah && !isGameOver())
		{
			player = 1-player;
			isPlayerChanged = true;
		}
		else
		{
			isPlayerChanged = false;
		}
	}
	

}
