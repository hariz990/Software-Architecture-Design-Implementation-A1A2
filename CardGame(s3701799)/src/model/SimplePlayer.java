package model;

import model.interfaces.Player;

public class SimplePlayer implements Player 
{
	private String playerName;
	private String playerID;
	private int points; 
	private int bet;
	private int result;

	public SimplePlayer(String playerID, String playerName, int points)
	{
		this.playerID = playerID;
		this.playerName = playerName; 
		this.points = points;
	}
	
	@Override
	public String getPlayerName() 
	{
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}

	@Override
	public int getPoints() //get player's score value
	{
		return this.points;
	}

	@Override
	public void setPoints(int points) 
	{
		this.points = points;
	}

	@Override
	public String getPlayerId() 
	{
		return this.playerID;
	}

	@Override
	public boolean placeBet(int bet) //player placing a bet
	{
		if(bet >= 0 && this.points >= bet)
		{
			this.bet = bet;
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int getBet() //get bet value
	{
		return this.bet;
	}

	@Override
	public void resetBet() //resetting players bet to 0
	{
		this.bet = 0;	
		this.result = 0;
	}

	@Override
	public int getResult() //player's score
	{
		return this.result;
	}

	@Override
	public void setResult(int result) //setting player's score
	{
		this.result = result;
	}
	
	public String toString()
	{
		return String.format("Player id: %s, Player name: %s, Player points: %d\n", 
				this.playerID,this.playerName,this.points);
	}

}
