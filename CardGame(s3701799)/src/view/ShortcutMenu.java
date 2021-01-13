package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.CloseAction;

//Shortcut header on console

@SuppressWarnings("serial")
public class ShortcutMenu extends JMenuBar
{
	public ShortcutMenu() {
		
		/*
		 * Shortcut header contents
		 */
		
		JMenu menu = new JMenu("Shortcut");
		this.add(menu);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new CloseAction());
		menu.add(exit);
		
	}
}
