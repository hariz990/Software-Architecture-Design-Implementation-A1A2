package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine 
{
	List<Player> playerList = new ArrayList<>();
	Deque<PlayingCard> deck = new LinkedList<PlayingCard>();
	List<GameEngineCallback> callBackList = new ArrayList<>();
	
	private int houseResult = 0; 
	
	@Override
	public Deque<PlayingCard> getShuffledDeck() 
	{
		//creates new deck 
		Deque<PlayingCard> deck = new LinkedList<PlayingCard>();
		for(Suit suit : Suit.values())
		{
			for(Value value : Value.values())
			{
				PlayingCardImpl card = new PlayingCardImpl(suit, value);
				deck.add(card); //adding cards into a temporary collection
			}                                                                                                                                                                             
		}
		
		Collections.shuffle((List) deck);//shuffle deck 
		this.deck = deck;//store shuffled deck into a new collection
		return deck;
	}
	
	@Override
	public void dealPlayer(Player player, int delay) 
	{
		
		if(deck == null || deck.size() == 0)
		{
			deck = getShuffledDeck();
		}
		
		boolean stop = false;
		
		// Loop when deck has cards
		while (stop == false) {
			
			delayTime(delay);//delay handing cards
			
			if (deck.isEmpty() == false) {
				
				PlayingCard card = this.deck.pop();//handing out cards
				
				player.setResult(player.getResult() + card.getScore());//update player score
				
				if (player.getResult() > GameEngine.BUST_LEVEL)//check if player's result is greater than bust level
				{
					
					player.setResult(player.getResult() - card.getScore());//setting player result
					
					for(GameEngineCallback callback : callBackList)//bust the player
					{
						callback.bustCard(player, card, this);
						delayTime(delay);
						callback.result(player, player.getResult(), this);
					}
					stop = true;
				}
				else
				{
					for(GameEngineCallback callback : callBackList)//hand next card
					{
						callback.nextCard(player, card, this);
					}
				}
			}
		}
	}

	@Override
	public void dealHouse(int delay) 
	{
		boolean stop = false;

		//loop when deck has cards
		while (stop == false) {
			
			delayTime(delay);//delay handing out card
			
			if (deck.isEmpty() == false) {
				
				PlayingCard card = this.deck.pop();//handing out cards
				
				houseResult += card.getScore();//update house score
				
				if (houseResult > GameEngine.BUST_LEVEL)//checking house score with bust level
				{
					
					houseResult -= card.getScore();//setting house result
					delayTime(delay);
					for(GameEngineCallback callback : callBackList)//busts house when house has bigger score than bust level
					{
						callback.houseBustCard(card, this);
					}
					stop = true;
				}
				else
				{
					for(GameEngineCallback callback : callBackList)
					{
						callback.nextHouseCard(card, this);
					}
				}
			}

		}	
		
		for(Player players : playerList)//Calculation for player's final points
		{
			if(players.getResult() > houseResult)
			{
				players.setPoints(players.getPoints() + (players.getBet())); //add points
			}
			else if(players.getResult() == houseResult)
			{
				players.setPoints(players.getPoints()); //setting to initial point
			}
			else if(players.getResult() < houseResult)
			{
				players.setPoints(players.getPoints() - players.getBet()); //deduct points
			}
			
			players.resetBet();
		}
		
		delayTime(delay);//delay displaying house and players final result
		for(GameEngineCallback callback : callBackList)
		{
			callback.houseResult(houseResult, this);
		}
	}
	
	@Override
	public void addPlayer(Player player) 
	{
		this.playerList.add(player);
	}

	@Override
	public Player getPlayer(String id) 
	{
		Player getPlayer = null;
		for (Player player : this.playerList) 
		{
			if (player.getPlayerId().equals(id)) //Checking for player in list
			{
				getPlayer = player;
			}
		}
		return getPlayer;
	}

	@Override
	public boolean removePlayer(Player player) 
	{
		return this.playerList.remove(player);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) 
	{
		callBackList.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) 
	{
		if(callBackList.contains(gameEngineCallback))
		{
			callBackList.remove(gameEngineCallback);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public Collection<Player> getAllPlayers() 
	{
		List<Player> allPlayer = new ArrayList<Player>(this.playerList);
		return allPlayer;
	}

	@Override
	public boolean placeBet(Player player, int bet) 
	{
		return player.placeBet(bet);
	}
	
	/*
	 * delayTime method is used to delay the time of dealing cards in seconds
	 */
	private void delayTime(int time)
	{
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getHouseResult() 
	{
		return houseResult;
	}
}
