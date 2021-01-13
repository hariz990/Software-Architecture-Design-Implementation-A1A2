package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PlayerView;

public class DealPlayer implements ActionListener
{
	/*
	 * Code executing task when clicking on deal player button
	 */
	
	private PlayerView playerView;

	public DealPlayer(PlayerView playerView) 
	{
		this.playerView = playerView;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.playerView.deal();
	}
}
