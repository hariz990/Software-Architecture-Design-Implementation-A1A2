package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.DealPlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.SettingBetView;
import view.CardPairView;
import view.HouseView;
import view.StatusBar;

@SuppressWarnings("serial")
public class PlayerView extends JPanel
{
	private JButton deal;
	private GameEngine engine;
	private StatusBar status;
	private SettingBetView bets;
	private CardPairView cardView;
	
	private Player player;
	private int bet;


	public PlayerView(GameEngine engine, StatusBar status, SettingBetView bets, CardPairView cardView) 
	{
		this.engine = engine;
		this.status = status;
		this.bets = bets;
		this.cardView = cardView;

		JLabel player = new JLabel("Start :");
		deal = new JButton("Player deal");
		deal.addActionListener(new DealPlayer(this));
		deal.setEnabled(false);
		this.add(player, BorderLayout.NORTH); //add Player label on console
		this.add(deal, BorderLayout.SOUTH);	//add Player Deal button on console
	}

	public void addPlayer(Player player) 
	{
		this.player = player;
		engine.addPlayer(player);
		update(player);
	}
	
	public void setDealButton(boolean b) //Setting Player deal button click ability
	{
		deal.setEnabled(b);
	}
	
	public void update(Player player) //Update player bets
	{
		bets.update(player, this);
	}

	public void changePlayer(String id) 
	{
		this.player = engine.getPlayer(id);
		update(this.player);
	}
	
	public void deletePlayer(String id) 
	{
		this.player = engine.getPlayer(id);
		engine.removePlayer(this.player);
		if (this.player == null) {
			bets.setBetButton(false); //set bet button un-clickable 
		}
		else {
			bets.setBetButton(true); //set bet button to clickable
			setDealButton(false);
		}
	}

	public void setBet(int i) 
	{
		bet = i;
		
		this.engine.placeBet(this.player, bet);
		setDealButton(true);
	}

	public void deal() 
	{
		cardView.clearDeck(); //Clear deck on console
		
		new Thread() 
		{
			@Override
			public void run() 
			{
				engine.dealPlayer(player, 1); //deal player with 1 second delay
				setDealButton(false); //set Player deal button to false when dealing cards
			}
		}.start();
	}

	public void updatePlayerResult() 
	{
		status.updatePlayer(player.getResult());
		bets.update(player, this);
	}
	
	public void updatePoints(HouseView houseView) 
	{
		int houseResult = houseView.getResult();
		int playerResult = this.player.getResult();
		
		/*
		 * updating game status
		 */
		
		if (playerResult < houseResult) 
		{
			status.updateLose(); 
		}
		else if (playerResult > houseResult)
		{
			status.updateWin();
		}
		else
		{
			status.updateDraw();
		}
		
		update(this.player); //update new bets choices for player according to the player's points
		status.updatePlayer(player.getResult()); //update player's results

		setDealButton(true);//enabling Player Deal button to click
	}
}
