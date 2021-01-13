package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.SettingBetView;

public class SettingBet implements ActionListener
{
	/*
	 * Code executing task when clicking on placing bet button
	 */
	
	private SettingBetView bet;
	
	public SettingBet(SettingBetView bet) 
	{
		this.bet = bet;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JOptionPane.showMessageDialog(null, String.format("%d points have been placed ", this.bet.getBet()), 
				"Betplaced", JOptionPane.INFORMATION_MESSAGE);
		
		bet.updateBet();
	}

}
