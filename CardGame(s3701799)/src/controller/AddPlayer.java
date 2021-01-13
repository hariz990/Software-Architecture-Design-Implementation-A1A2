package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimplePlayer;
import model.interfaces.Player;
import view.ToolBox;
import view.PlayerPrompt;
import view.PlayerView;

public class AddPlayer implements ActionListener
{
	/*
	 * Code executing task when clicking on adding player button
	 */
	
	private ToolBox toolBox;
	private PlayerView playerView;
	private int i = 1;
	
	public AddPlayer(ToolBox toolBox, PlayerView playerView) 
	{
		this.toolBox = toolBox;
		this.playerView = playerView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		PlayerPrompt prompt = new PlayerPrompt();
		
		if (prompt.getName() == null || prompt.getPoints() <= 0) //Execute when player has a name and points
		{
			prompt = new PlayerPrompt();
		} 
		else 
		{
			Player player = new SimplePlayer(String.valueOf(i), prompt.getName(), prompt.getPoints());
			this.toolBox.update(player);
			this.playerView.addPlayer(player);
			i++;
		}	
	}
	
}
