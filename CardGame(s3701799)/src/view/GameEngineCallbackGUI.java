package view;

import javax.swing.JOptionPane;

import model.PlayingCardImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;
import view.HouseView;
import view.PlayerView;
import view.CardPairView;

public class GameEngineCallbackGUI implements GameEngineCallback
{
	private PlayerView playerView;
	private HouseView houseView;
	private CardPairView cardView;
	
	public GameEngineCallbackGUI(PlayerView playerView, HouseView houseView, CardPairView cardView) 
	{
		this.playerView = playerView;
		this.houseView = houseView;
		this.cardView = cardView;		
	}
	
	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) //Player's card
	{
		cardView.drawCard((PlayingCardImpl)card); //Displaying card on console
		playerView.updatePlayerResult();
	}

	@Override
	public void result(Player player, int result, GameEngine engine) 
	{
		playerView.updatePlayerResult();
		playerView.setDealButton(false);
		houseView.deal();
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine)
	{
		cardView.setYPosition(200);//adjusting card positioning for house
		cardView.drawCard((PlayingCardImpl)card); //Displaying card on console
		houseView.updateHouseResult();
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) //House bust
	{
		houseView.updateHouseResult();
		JOptionPane.showMessageDialog(null, "House busted! House Result Total: " + houseView.getResult(), 
				"CARD BUST", JOptionPane.INFORMATION_MESSAGE);
		playerView.updatePoints(houseView); //Displaying player's game status
		cardView.clearDeck(); //clear cards on console
	}

	@Override
	public void houseResult(int result, GameEngine engine) 
	{
		houseView.updateHouseResult();
		playerView.updatePlayerResult();//Displaying player result in status bar
		houseView.setHouseResult(0); //Displaying house result as 0 in status bar
	}
	
	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) //Player bust
	{
		JOptionPane.showMessageDialog(null, "You busted! Your Result Total: " + player.getResult() + "\nDealing House next..", 
				"CARD BUST", JOptionPane.INFORMATION_MESSAGE);
	}

}
