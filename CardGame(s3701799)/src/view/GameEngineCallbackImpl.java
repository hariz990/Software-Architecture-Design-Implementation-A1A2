package view;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private final Logger logger = Logger.getLogger(this.getClass().getName());

   public GameEngineCallbackImpl()
   {
	   logger.setUseParentHandlers(false);
		Handler[] handlers = logger.getHandlers();
		for (Handler handler : handlers) {
			if (handler.getClass() == ConsoleHandler.class)
				logger.removeHandler(handler);
		}
		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(Level.FINER);
		logger.addHandler(ch);
		logger.setLevel(Level.FINE);
   }

   @Override
   public void nextCard(Player player, PlayingCard card, GameEngine engine)
   {
      String name = player.getPlayerName();
      logger.log(Level.FINE,
				String.format("Card Dealt to " + name + " .. %s", card.toString()));
   }

   @Override
   public void result(Player player, int result, GameEngine engine)
   {
	   String name = player.getPlayerName();
	      logger.log(Level.INFO,
					String.format(name + ", final result= %d\n", result));
   }

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) 
	{
		String name = player.getPlayerName();
	    logger.log(Level.FINE,
					String.format("Card Dealt to " + name + " .. %s" + " ... YOU BUSTED!", card.toString()));
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		
		logger.log(Level.FINE,
				String.format("Card Dealt to House .. %s", card.toString()));
	}
	
	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) 
	{
		logger.log(Level.FINE,
				String.format("Card Dealt to House .. %s" + " ... HOUSE BUSTED!", card.toString()));
	}
	
	@Override
	public void houseResult(int result, GameEngine engine) 
	{
		String playerResult = "";
		
		logger.log(Level.INFO,
				String.format("House, final result = " + result + "\n"));
		
		for(Player player : engine.getAllPlayers())//adding all player's toString in playerResult variable
		{
			playerResult += player.toString();
		}
		
		logger.log(Level.INFO,
				String.format("Final Player Results\n%s", playerResult));
	}

}
