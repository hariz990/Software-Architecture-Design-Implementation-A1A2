package view;

import java.awt.Dimension;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PlayerPrompt extends JPanel
{
	private int playerPoints;
	private String playerName;
	
	public PlayerPrompt() 
	{
		JTextField name = new JTextField();
		JTextField points = new JTextField();
		add(new JLabel("Player name : ")); //Adding label on console
		name.setPreferredSize(new Dimension(100, 30)); //Set label size
		add(name);
		add(new JLabel("Initial Points :"));
		points.setPreferredSize(new Dimension(100,30));
		add(points);
		
		/*
		 * sub console for entering details
		 */
		int result = JOptionPane.showConfirmDialog(null, this, "Please Enter Name and Points", 
											JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{
			checkValue(name,points);
		}
	}

	private boolean checkValue(JTextField name, JTextField points) 
	{
		boolean check = true;
	
		if (name.getText().isEmpty() || points.getText().isEmpty()) //Execute when player name or player bet field is empty
		{
			JOptionPane.showMessageDialog(null, "Name or Intital Point cannot be blank", 
					"Error", JOptionPane.ERROR_MESSAGE);
			check = false;
		}
		else if(Pattern.matches("[0-9]+", points.getText()) == false) //Execute when points are not fully numerical
		{
			JOptionPane.showMessageDialog(null, "Points contains or is a non-numerical value", 
					"Error", JOptionPane.ERROR_MESSAGE);
			check = false;
		}
		else if (Integer.parseInt(points.getText()) <= 0) //Execute when player bet value is less than or equals to 0
		{
			JOptionPane.showMessageDialog(null, "Value must be greater than zero", 
					"Error", JOptionPane.ERROR_MESSAGE);
			check = false;
		}
		else 
		{
			//Store player
			this.playerName = name.getText();
			this.playerPoints = Integer.parseInt(points.getText());
		}
		
		return check;
	}
	
	public String getName() 
	{
		return this.playerName;
	}
	
	public int getPoints() 
	{
		return this.playerPoints;
	}
			
}
