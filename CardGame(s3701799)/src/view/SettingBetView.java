package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.SettingBet;
import model.interfaces.Player;
import view.PlayerView;

@SuppressWarnings("serial")
public class SettingBetView extends JPanel
{
	private JComboBox<Integer> betList = new JComboBox<Integer>();
	private JLabel currentPlayer;
	private JLabel currentPoints;
	private JButton btnBet;
	private PlayerView playerView;
	
	private int[] bets = {10,25,50,100,200,1000}; //Bet choices
	
	public SettingBetView() 
	{
		setLayout(new GridLayout(0,1)); //bet button layout 
		
		JLabel lbBets = new JLabel("Place your bet : ");
		add(lbBets); //add bet label
		
		add(betList); //add bet choices as list
		btnBet = new JButton("Place bet"); //bet button text
		btnBet.addActionListener(new SettingBet(this));
		add(btnBet);
		btnBet.setEnabled(false);
		
		currentPlayer = new JLabel("Current player : ");
		currentPoints = new JLabel("Current points : ");
		
		add(currentPlayer);
		add(currentPoints);
	}
	
	public void setBetButton(boolean b) //setting bet button click ability 
	{
		btnBet.setEnabled(b);
	}
	
	public void updateBet() 
	{
		this.playerView.setBet(getBet());
	}
	
	public void update(Player player,PlayerView playerView) 
	{
		this.playerView = playerView;
		
		if (player == null) //when there are no players bet button is not clickable
		{
			setBetButton(false);
			
			currentPlayer.setText("Current player : ");
			currentPoints.setText("Current points : ");
			betList.removeAllItems();
			
			return;
		}
		
		btnBet.setEnabled(true);
		currentPlayer.setText(String.format("Current player : %s", player.getPlayerName()));
		currentPoints.setText(String.format("Current points : %d", player.getPoints()));
		
		betList.removeAllItems(); //remove bet choices in list
		
		/*
		 * For loop to indicate player's maximum bet according to their points
		 * Eg. when players have 100 points, list of bets will only display maximum bets that 
		 * a player can place. For this case, 10,25,50 and 100.
		 */
		for (int i = 0; i < bets.length; i++) 
		{
			if (bets[i] <= player.getPoints()) 
			{
				betList.addItem(bets[i]);
			}

		}
	}
	
	
	public int getBet() 
	{
		return (int)betList.getSelectedItem();
	}
}
