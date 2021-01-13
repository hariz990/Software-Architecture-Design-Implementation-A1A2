package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ToolBox;
import view.PlayerView;

public class RemovePlayer implements ActionListener
{
	/*
	 * Code executing task when clicking on remove player button
	 */
	
	private ToolBox toolBox; 
	private PlayerView playerView;

	public RemovePlayer(ToolBox toolBox, PlayerView playerView) 
	{
		this.toolBox = toolBox;
		this.playerView = playerView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.playerView.deletePlayer(this.toolBox.getPlayer());
		toolBox.removePlayer();
	}
}
