package txstate.cs.ai.kala;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


class MiniMaxABStruct{
	int value;
	ArrayList<Board> boardPath;
	
	
	public MiniMaxABStruct()
	{
		this.value = 0;
		this.boardPath = new ArrayList<Board>();
	}
}

class ABData{
	int value;
	Board boardPath;
	
	
	public ABData()
	{
		this.value = 0;
	}
}

class UserPlayer
{
	String randomplayer;
	
	public UserPlayer()
	{
		randomplayer="";
	}
	
	public String getPlayerName()
	{
		return randomplayer;
	}
	
	public void setPlayerName(String tempStr)
	{
		randomplayer=tempStr;
	}
}

public class GameEngine {
	int succLength;
	int depth;
	int numberofnodesgenerated1;
	int numberofnodesgenerated2;
	Board board = new Board();
	ArrayList<UserPlayer> randomplayers = new ArrayList<UserPlayer>();
	int currentPlayerIndex;
	int winnerIndex;
	int numberOfStonesInplayerOneKalah;
	int numberOfStonesInplayerTwoKalah;
	int stoneThreshold;
	int halfPits;
	int sidePits;
	int numPits;
	int initStones;
	int minDepth;
	int value;
	int player;
	int oppositePlayer;
	int currentPath;
	int currentDepth;
	int EvalChoice;
	ArrayList<ABData> arrayList = new ArrayList<ABData>();
	StringBuilder str = new StringBuilder();
	//constructor for kalah game
	GameEngine()
	{
		//Top player's currentPlayerIndex = 0;
		//Down Player's currentPlayerIndex = 1
		numberofnodesgenerated1=1;
		numberofnodesgenerated2=1;
		currentPlayerIndex = 1;
		numberOfStonesInplayerOneKalah = 0;
		numberOfStonesInplayerTwoKalah = 0;
		stoneThreshold = 36;
		halfPits = 7;
		sidePits = 6;
		numPits = 14;
		initStones = 6;
		winnerIndex = -1;
		minDepth = 0;
		succLength = 0;
		board.player = 0;
		board.oppositePlayer=1;
	}
	//constructor for kalah game
	GameEngine(int dep, int pl)
	{
		numberofnodesgenerated1=1;
		numberofnodesgenerated2=1;
		currentPlayerIndex = 0;
		numberOfStonesInplayerOneKalah = 0;
		numberOfStonesInplayerTwoKalah = 0;
		stoneThreshold = 36;
		halfPits = 7;
		sidePits = 6;
		numPits = 14;
		initStones = 6;
		winnerIndex = -1;
		minDepth = 0;
		succLength = 0;
		depth = dep;
		player = pl;
		oppositePlayer=1-pl;
		board.player = pl;
		board.oppositePlayer=1-pl;
		currentDepth = 0;
	}
	//initialize players
	void init()
	{
		for(int i = 0; i < 2; i++)
		{
			UserPlayer p = new UserPlayer();
			p.setPlayerName("Player#"+i);
			randomplayers.add(p);
		}
	}
	//---------------------Block start for player to player kalah game------------------------------------
	//checking for game over for user to user kalah game
	boolean isUserUserGameOver()
	{
		if(numberOfStonesInplayerOneKalah > stoneThreshold ||numberOfStonesInplayerTwoKalah > stoneThreshold)
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
	//getting correct pit for the player
	int playerPitChoice(GameGUI frame)
	{
		int pitInput;
		//Scanner sc = new Scanner(System.in);
		System.out.println("Player "+(board.player+1)+" turn");
		frame.txtArea.append("Player "+(board.player+1)+" turn\n");
		pitInput = new Random().nextInt(7);
		System.out.println(pitInput);
		while(pitInput < 1 || pitInput > 6)
		{
			pitInput = new Random().nextInt(6);
			System.out.println(pitInput);
		}
		return pitInput;
	}
	//update board based on player choice
	void updateBoard(int position, GameGUI frame)
	{
		System.out.println("Expanded move #"+position);
		frame.txtArea.append("Expanded move #"+position);
		
		int playerKalah,opponentKalah, pitInput, posIndex;
		int trackPosition = position;
		int grabOppositePosition, numOfOppPositionStones;
		int numberOfStones = board.getBoardValueByPosition(position);
		board.setBoardValueByPosition(position, 0);
		playerKalah = currentPlayerIndex * halfPits;
		opponentKalah = halfPits - playerKalah;
		
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
				if((board.getBoardValueByPosition(trackPosition) == 0 && trackPosition <= 6 && trackPosition >= 1) && tempPosition == 1 && numberOfStones == 0)
				{
					if(board.getBoardValueByPosition(trackPosition) == 0 && numberOfStones == 0)
					{
						grabOppositePosition = numPits-trackPosition;
						numOfOppPositionStones =
						board.getBoardValueByPosition(grabOppositePosition);
						board.setBoardValueByPosition(grabOppositePosition, 0);
						board.setBoardValueByPosition(playerKalah,
						board.getBoardValueByPosition(playerKalah) + numOfOppPositionStones + 1);
					}
				}
				else if(board.getBoardValueByPosition(trackPosition) == 0 && (trackPosition <= 13 && trackPosition >= 8) && tempPosition == 8 && numberOfStones == 0)
				{
					if(board.getBoardValueByPosition(trackPosition) == 0 && numberOfStones == 0)
					{
						grabOppositePosition = numPits-trackPosition;
						numOfOppPositionStones =
						board.getBoardValueByPosition(grabOppositePosition);
						board.setBoardValueByPosition(grabOppositePosition, 0);
						board.setBoardValueByPosition(playerKalah,
						board.getBoardValueByPosition(playerKalah) + numOfOppPositionStones + 1);
					}
				}
				
				else
				{
					board.setBoardValueByPosition(trackPosition,
					board.getBoardValueByPosition(trackPosition) + 1);
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
					board.setBoardValueByPosition(trackPosition,
					board.getBoardValueByPosition(trackPosition) + 1);
				}
			}
		}
		
		if(trackPosition == playerKalah)
		{
			displayBoard(frame);
			pitInput = playerPitChoice(frame);
			if(currentPlayerIndex == 0)
			{
				board.player = 0;
				numberOfStones = board.getBoardValueByPosition(pitInput);
				if(numberOfStones > 0)
				{
					updateBoard(pitInput, frame);
				}
			}
			else if(currentPlayerIndex == 1)
			{
				board.player = 1;
				pitInput = pitInput+halfPits;
				numberOfStones = board.getBoardValueByPosition(pitInput);
				if(numberOfStones > 0)
				{
					updateBoard(pitInput, frame);
				}
			}
		}
		numberOfStonesInplayerOneKalah = board.getBoardValueByPosition(playerKalah);
		numberOfStonesInplayerTwoKalah = board.getBoardValueByPosition(opponentKalah);
		
		if(trackPosition != playerKalah && !isUserUserGameOver())
		{
			currentPlayerIndex = 1-currentPlayerIndex;
			board.player = 1-board.player;
		}
	}
	//operating user to user kalah game
	void playUserUser(GameGUI frame)
	{
		int pitInput, position=0;
		int numberOfStones;
		int playerKalah, opponentKalah;
		playerKalah = currentPlayerIndex * halfPits;
		opponentKalah = halfPits - playerKalah;
		board.player = 1;
		do
		{
			pitInput = playerPitChoice(frame);
			if(currentPlayerIndex == 1)
			{
				position = pitInput+halfPits;
			}
			else if(currentPlayerIndex == 0)
			{
				position = pitInput;
			}
			numberOfStones = board.getBoardValueByPosition(position);
			if(numberOfStones > 0)
			{
				System.out.println("Expanded move #"+position);
				frame.txtArea.append("Expanded move #"+position+"\n");
				
				updateBoard(position, frame);
			}
			displayBoard(frame);
		}while(!isUserUserGameOver());
		
		if(numberOfStonesInplayerOneKalah == numberOfStonesInplayerTwoKalah)
		{
			System.out.println("Game has been drawn");
			frame.txtArea.append("Game has been drawn\n");
		}
		else
		{
			System.out.println("Player "+randomplayers.get(currentPlayerIndex).getPlayerName()+" wins");
			frame.txtArea.append("Player "+randomplayers.get(currentPlayerIndex).getPlayerName()+" wins\n");
		}
	}
	
	//---------------------Block end for player to player kalah game------------------------------------
	//---------------------Block Start for AI algorithm--------------------------------
	//checking game over for AI algorithm
	boolean isGameOver()
	{
		return board.isGameOver();
	}
	
	//------Block start for Minimax-Alpha-Beta Search-----//
	//return true if depth matched with user input else false
	boolean deepEnough(int depth)
	{
		if(depth == this.depth)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	//getting possible moves for the given player in the given board
	ArrayList getPossibleMoves(Board b, int player)
	{
		ArrayList possible = new ArrayList();
		if(player == 0)
		{
			for(int i = 1; i <= 6; i++)
			{
				if(b.getBoardValueByPosition(i) > 0)
				{
					possible.add(i);
				}
			}
		}
		
		else
		{
			for(int i = 8; i <= 13; i++)
			{
				if(b.getBoardValueByPosition(i) > 0)
				{
					possible.add(i);
				}
			}
		}
		return possible;
	}
	
	//opening one more ply for the possible moves
	ArrayList<Board> MoveGen(Board b, int player)
	{
		ArrayList possible = getPossibleMoves(b, player);
		ArrayList<Board> boardStorage = new ArrayList<Board>();
		
		if(possible.size() == 0)
		{
			return boardStorage;
		}
		
		//Board b1;
		for(int i = 0; i < possible.size(); i++)
		{
			Board b1 = new Board(b);
			Board temp = b;
			b1.move((int)(possible.get(i)));
			b=temp;
			boardStorage.add(b1);
		}

		return boardStorage;
	}
	//performing minimax-a-b algorithm
	MiniMaxABStruct miniMaxABRK(Board b, int depth, int player, int ut, int pt)
	{
		int newValue;
		int val;
		MiniMaxABStruct s = new MiniMaxABStruct();
		
		if(deepEnough(depth))
		{
			b.player = player;
			//change evaluation function here
			s.value = eval(b, player);
			return s;
		}
		int thisPlayer=player;
		ArrayList<Board> succ = MoveGen(b, player);
		if(succ.size() == 0)
		{
			return s;
		}
		
		else
		{
			//numberofnodesgenerated += succ.size();
			NodesGenerated(succ.size(), thisPlayer);
			for(int i = 0; i < succ.size(); i++)
			{
				MiniMaxABStruct res = miniMaxABRK(succ.get(i), depth+1, b.getOppositePlayer(), -pt, -ut);
				newValue = -(res.value);
				if(newValue > pt)
				{
					pt = newValue;
					s.value = pt;
					s.boardPath.add(b);
					for(int j = 0; j < res.boardPath.size(); j++)
					{
						s.boardPath.add(res.boardPath.get(j));
					}
					s.boardPath.add(succ.get(i));
					for (int j = 0; j < s.boardPath.size(); j++)
					{
						for (int k = 0; k < j; k++) 
						{
							if(s.boardPath.get(j).equal(s.boardPath.get(k)))
							{
								s.boardPath.remove(s.boardPath.size()-1);
								j--;
								k--;
							}
						}
					}
				}
				
				if(pt >= ut)
				{
					return s;
				}
			}
			
			return s;
		}
	}
	
	//------Block end for Minimax-Alpha-Beta Search-----//
	//------Block start for Alpha-Beta Search-----//
	//checking the depth with currentDepth
	boolean deepEnoughAB(int depth, int currentDepth)
	{
		if(depth == currentDepth)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	int maxValue(Board b, int alpha, int beta)
	{
		if(deepEnoughAB(depth, currentDepth))
		{
			return eval(b, player);
		}
		
		int v = -1000;
		int thisPlayer=player;
		ArrayList<Board> succ = MoveGen(b, player);
		player=b.getOppositePlayer(); //swap the player
		b.player=player;
		if(succ.isEmpty())
		{
			return -1;
		}
		else
		{
			currentDepth++;
			//numberofnodesgenerated += succ.size();
			NodesGenerated(succ.size(), thisPlayer);
			for(int i=0; i<succ.size(); i++)
			{

				int minvalue = minValue(succ.get(i), alpha, beta);
				if(v<minvalue)
				{
					v = minvalue;
				}
				
				ABData action = new ABData();
				action.value = v;
				action.boardPath = succ.get(i);
				arrayList.add(action);
				if(v<=alpha)
				{
					System.out.println("tree with alpha < "+v+" is pruned");
					return v;
				}
				if(beta>v)
				{
					beta = v;
				}

				else
				{
					if(v < minValue(succ.get(i), alpha, beta))
					{
						v = minValue(succ.get(i), alpha, beta);
					}
					
					if(v > beta)
					{
						return v;
					}
					
					if(alpha < v)
					{
						alpha = v;
					}
				}
			}
			
			return v;
		}
	}
	
	/*
	returning max turn best value,
	alpha-is the best already explored value to the root for max
	beta-is the best already explored value to the root for min
	*/
	
	int minValue(Board b, int alpha, int beta)
	{
		
		if (deepEnoughAB(depth, currentDepth))
		{

			ABData ac = new ABData();
			//change evaluation function here
			ac.value = eval(b, player);
			ac.boardPath = b;
			arrayList.add(ac);
			
			return ac.value;
	}
	int v = 1000;
	int thisPlayer=player;
	ArrayList<Board> succ = MoveGen(b, player);
	player=b.getOppositePlayer(); //swap the player
	b.player=player;
	if (succ.size() == 0)
	{
		return -1;
	}
	else
	{
		currentDepth++;
		//numberofnodesgenerated += succ.size();
		NodesGenerated(succ.size(), thisPlayer);
		for(int i = 0; i < succ.size(); i++)
		{
			if (v > maxValue(succ.get(i), alpha, beta))
			{
				v = maxValue(succ.get(i), alpha, beta);
			}
			if (v <= alpha)
			{
				return v;
			}
			if (beta > v)
			{
				beta = v;
			}
		}
	return v;
	}
	}
	//Function to back up the best value at each node and update the board state
	Board getActionBoard(int v)
	{
		if(v == -1)
		{
			System.out.println("Integer -1 returned");
		}
		if(arrayList.size() == 0)
		{
			System.out.println("Arraylist is blank");
			return this.board;
		}
		for(int i = 0; i< arrayList.size()-1 ; i++)
		{
			if(arrayList.get(i).value == v)
			{
				return arrayList.get(i).boardPath;
			}
		}
		ABData max = arrayList.get(0);
		int maxVal = arrayList.get(0).value;
		for(int i = 0; i < arrayList.size(); i++)
		{
			ABData track = arrayList.get(i);
			if(track.value > maxVal)
			{
				max = arrayList.get(i);
				maxVal = track.value;
			}
		}
		//System.out.println("\nSize : " + arrayList.size()+" \nReturned null at the end\n");
		return max.boardPath;
	}

	//performing alpha beta search
	Board alphaBetaSearch(Board b)
	{
		//player = b.player;
		//oppositePlayer = b.getOppositePlayer();
		int value = maxValue(b, -1000, 1000);
		Board b1 = getActionBoard(value);
		currentDepth = 0;
		arrayList.clear();
		return b1;
	}
	//------Block end for Alpha-Beta Search-----//
	//---------------------Block end for AI algorithm--------------------------------
	
	
	//---------------------Block Start For Evaluation function-----------------------
	//returning the evaluation value
	int evaluationFunction1(Board b, int player)
	{
		int player1stones=0;
		for(int i=1;i<7;i++)
		{
			player1stones+=b.getBoardValueByPosition(i);
		}

		int player2stones=0;
		for(int i=8;i<13;i++)
		{
			player2stones+=b.getBoardValueByPosition(i);
		}

		if(player == 0)
		{

			return ((int)b.getBoardValueByPosition(0)+player1stones) - ((int)b.getBoardValueByPosition(7)+player2stones)*2+((int)b.getBoardValueByPosition(0)+player1stones) - ((int)b.getBoardValueByPosition(7)+player2stones);
		}
		else
		{
			return ((int)b.getBoardValueByPosition(7)+player2stones) - ((int)b.getBoardValueByPosition(0)+player1stones)*2+((int)b.getBoardValueByPosition(7)+player2stones) - ((int)b.getBoardValueByPosition(0)+player1stones);
		}

	}
	
	//returning the evaluation value
	int evaluationFunction2(Board b, int player)
	{
		int player0Kalah = b.getBoardValueByPosition(0);
		int player1Kalah = b.getBoardValueByPosition(7);
		if(player == 0)
		{
			if(player1Kalah != 0)
			{
				return (b.getBoardValueByPosition(0)-b.getBoardValueByPosition(7));
			}
			else
			{
				return -1;
			}
		}
		
		else
		{
			if(player0Kalah != 0)
			{
				return (b.getBoardValueByPosition(7)-b.getBoardValueByPosition(0));
			}
			else
			{
				return -1;
			}
		}
	}
	//returning the evaluation value
	int evaluationFunction3(Board b, int player)
	{
		int player1stones=0;
		for(int i=1;i<7;i++)
		{
			player1stones+=b.getBoardValueByPosition(i);
		}
		
		int player2stones=0;
		for(int i=8;i<13;i++)
		{
			player2stones+=b.getBoardValueByPosition(i);
		}
		
		if(player == 0)
		{
			
			return (int)b.getBoardValueByPosition(0)+player2stones;
		}
		else
		{
			return (int)b.getBoardValueByPosition(7)+player1stones;
		}
		
	}
	
	int eval(Board b, int player)
	{
		int ret = 0;
		switch(EvalChoice)
		{
		case 1:
			ret= evaluationFunction1(b, player);
			break;
		case 2:
			ret= evaluationFunction2(b, player);
			break;
		case 3:
			ret= evaluationFunction3(b, player);
			break;
		}

		return ret;
		
	}
	//---------------------Block End for Evaluation function-------------------------
	
	
	//-----------------------Block start for basic functionality---------------------
	
	void NodesGenerated(int nodes, int TempPlayer)
	{
		if(TempPlayer==0)
		{
			numberofnodesgenerated1+=nodes;
		}
		else
		{
			numberofnodesgenerated2+=nodes;
		}
	}
	//displaying board
	void displayBoard(GameGUI frame)
	{
		board.displayBoard(frame);
	}
	//checking board is empty or not
	boolean isBoardEmpty()
	{
		return board.isBoardEmpty();
	}
	//getting current board
	Board getBoard()
	{
		return board;
	}
	//setting current board with given board and player
	void setBoard(Board b, int player)
	{
		board = b;
		board.player = player;
	}
	//returning the current player
	int getPlayer()
	{
		return board.getPlayer();
	}
	//returning the opposite player
	int getOppositePlayer()
	{
		return board.getOppositePlayer();
	}
	//returning the opposite player for the given player
	int getOppositePlayerFromBoard(int player)
	{
		return 1-player;
	}
	//setting next player of the given player
	void setNextPlayer(int player)
	{
		board.player = 1-player;
	}
	//returning true if the player turns into the same kalah
	boolean itsInSameKalah()
	{
		return board.itsInSameKalah();
	}
	//returning player1 kalah value
	int getPlayer1Kalah()
	{
		return board.getBoardValueByPosition(0);
	}
	//returning player2 kalah value
	int getPlayer2Kalah()
	{
		return board.getBoardValueByPosition(7);
	}
	//-----------------------Block end for basic functionality-----------------------

}
