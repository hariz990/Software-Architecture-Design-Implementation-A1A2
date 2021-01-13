package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ToolBox;
import view.PlayerView;

public class ChangePlayer implements ActionListener
{
	/*
	 * Code executing task when clicking on changing player button
	 */
	
	private ToolBox toolBox;
	private PlayerView playerView;
	
	public ChangePlayer (ToolBox toolBox, PlayerView playerView) 
	{
		this.toolBox = toolBox;
		this.playerView = playerView;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (toolBox.getPlayer() != null) 
		{
			playerView.changePlayer(toolBox.getPlayer());
		}
	}

}
