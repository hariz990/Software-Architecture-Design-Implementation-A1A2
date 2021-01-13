package view;

import javax.swing.JPanel;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class HouseView extends JPanel
{
	private GameEngine engine; 
	private StatusBar statusBar;
	
	
	public HouseView(GameEngine engine, StatusBar status) 
	{
		this.engine = engine;
		this.statusBar = status;
	}

	public void deal() 
	{
		new Thread() 
		{
			@Override
			public void run() 
			{
				engine.dealHouse(1); //deal card to house with 1 second delay
				
			}
		}.start();
		
	}
	
	public void updateHouseResult() 
	{
		GameEngineImpl engineImpl = (GameEngineImpl)engine;
		statusBar.updateHouse(engineImpl.getHouseResult()); //updating House score in status bar on console
	}
	
	public void setHouseResult(int result)//Re-setting house result
	{
		statusBar.updateHouse(result);
	}

	public int getResult() 
	{
		GameEngineImpl engineImpl = (GameEngineImpl)engine;
		return engineImpl.getHouseResult();
	}
}
