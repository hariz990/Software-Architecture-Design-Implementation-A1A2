package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

import view.StatusBar;
import view.GameEngineCallbackGUI;
import view.ToolBox;
import view.SettingBetView;
import view.HouseView;
import view.PlayerView;

@SuppressWarnings("serial")
public class AppFrame extends JFrame 
{

	public AppFrame()
	{
		super("BLACK JACK GAME"); //console title
		setBounds(100, 100, 650, 500); //console size
		
		/*
		 * Initiate new models in console
		 */
		GameEngine engine = new GameEngineImpl();
		StatusBar status = new StatusBar();
		ShortcutMenu shortcut = new ShortcutMenu();
		GameEngineCallbackImpl callback = new GameEngineCallbackImpl();
		
		CardPairView cardView = new CardPairView();
		SettingBetView bets = new SettingBetView();
		
		PlayerView player = new PlayerView(engine,status,bets,cardView);
		HouseView house = new HouseView(engine,status);
		ToolBox toolbox = new ToolBox(player, bets);
		GameEngineCallbackGUI gui = new GameEngineCallbackGUI(player,house,cardView);
		
		/*
		 * Adding callback
		 */
		engine.addGameEngineCallback(gui);
		engine.addGameEngineCallback(callback);
		
		/*
		 * Setting button locations
		 */
		JPanel leftside = new JPanel(new GridLayout(2, 1));
		leftside.add(bets);

		JPanel rightSide = new JPanel(new GridLayout(5, 1));
		rightSide.add(player);
		
		/*
		 * Adding buttons in console
		 */
		setJMenuBar(shortcut);
		add(cardView, BorderLayout.CENTER);
		add(leftside, BorderLayout.WEST);
		add(rightSide, BorderLayout.EAST);
		add(status, BorderLayout.PAGE_END);
		add(toolbox, BorderLayout.PAGE_START);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
