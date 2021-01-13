package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Exit system controller
public class CloseAction implements ActionListener
{
	public void actionPerformed(ActionEvent arg0) 
	{
		System.exit(0);
	}
}
