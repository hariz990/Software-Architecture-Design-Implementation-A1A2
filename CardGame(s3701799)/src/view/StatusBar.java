package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class StatusBar extends JPanel
{
	private JLabel status; 
	private JLabel card;
	private JLabel house;
	
	public StatusBar() {

		setLayout(new GridLayout(1, 5)); 

		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK); //Border for status bar 
		
		status = new JLabel();
		card = new JLabel("Your Card : ");
		house = new JLabel("House Card : ");
		
		status.setBorder(blackBorder);
		card.setBorder(blackBorder);
		house.setBorder(blackBorder);

		add(status);
		add(card);
		add(house);
	}
	
	public void reset() //reset player and house value to 0 for a new deal
	{
		status.setText("");
		updatePlayer(0); //display player score to 0 on console
		updateHouse(0);	//displayer house score to 0
	}
	
	public void updateWin() //Displaying game status text 
	{
		status.setText("You Win");
	}
	
	public void updateLose() 
	{
		status.setText("You Lose");
	}
	
	public void updateDraw()
	{
		status.setText("Game Draw");
	}
	
	public void updatePlayer(int i) //Displaying player's final total card
	{
		card.setText(String.format("Your Total Card : %d", i));
	}
	
	public void updateHouse(int i) 
	{
		house.setText(String.format("House Total Card : %d", i));
	}
}
