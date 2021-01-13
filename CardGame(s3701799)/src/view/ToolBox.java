package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import controller.AddPlayer;
import controller.ChangePlayer;
import controller.RemovePlayer;
import model.interfaces.Player;
import view.PlayerView;

@SuppressWarnings("serial")
public class ToolBox extends JToolBar
{
	private JComboBox<String> PlayerList = new JComboBox<String>();
	private PlayerView playerView;
	private SettingBetView betView;
	private Player player;
	
	JButton btnaddPlayer;
	JButton btnremovePlayer;
	
	public ToolBox(PlayerView playerView, SettingBetView betView) 
	{
		this.playerView = playerView;
		this.betView = betView;
		
		btnaddPlayer = new JButton("Add Player"); //button text
		btnremovePlayer = new JButton("Remove Player");
		
		btnaddPlayer.addActionListener(new AddPlayer(this, this.playerView));
		btnremovePlayer.addActionListener(new RemovePlayer(this, this.playerView));
		btnremovePlayer.setEnabled(false);
		add(btnaddPlayer); //adding button to console
		add(btnremovePlayer);
		add(PlayerList); //add a list of players on console
		setFloatable(false);
	}
	
	public void update(Player player) 
	{
		this.player = player;
		
		PlayerList.addItem(String.format("id : %s  player : %s", player.getPlayerId(), player.getPlayerName()));
		PlayerList.addActionListener(new ChangePlayer(this, playerView));
		
		updateRemovePlayerButton();
	}
	
	public String getPlayer() 
	{
		String id = null;
		String player = (String) PlayerList.getSelectedItem();
		if(player != null) //Checking if a player exists
		{
			id = player.substring(5,6); //Alteration for player's ID
		}
		return id;
	}
	
	public void removePlayer() 
	{
		if(PlayerList.getItemCount() > 0) //checking if there are players 
		{
			PlayerList.removeItem(PlayerList.getSelectedItem());
		}
		
		updateRemovePlayerButton();
		betView.update(player, playerView);
	}
	
	/*
	 * Updating Remove Player button enable or disable
	 */
	private void updateRemovePlayerButton() {
		
		if (PlayerList.getItemCount() == 0) 
		{
			btnremovePlayer.setEnabled(false);
			player = null;
			betView.update(player, playerView);
		}
		else 
		{
			btnremovePlayer.setEnabled(true);
		}

	}
}
