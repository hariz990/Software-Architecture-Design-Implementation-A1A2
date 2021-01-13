package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import model.PlayingCardImpl;
import view.Card;

@SuppressWarnings("serial")
public class CardPairView extends JComponent
{
	 private static final Color BACKGROUND_COLOR = Color.GREEN;
	 private static final int TABLE_SIZE = 400;   
	 
	 private final String imageType = ".gif";
	 private final String imageLocation = "cards/images/";
	    
	 private int xPos;
	 private int yPos;
	    
	 private final int xFixedPos = 10;
	 private final int yFixedPos = 0;
	    
	 private ArrayList<Card> deck = new ArrayList<Card>(); 
	    
	 public CardPairView() // Setting Card preferences
	 {
		 setPreferredSize(new Dimension(TABLE_SIZE, TABLE_SIZE));
	 }
	 
	 public void clearDeck() //Clearing deck on console
	 {
		 deck.clear();
		 removeAll();
		    	
		 xPos = xFixedPos;
		 yPos = yFixedPos;
		    	
		 repaint();
	 }
	 
	 public void drawCard(PlayingCardImpl _card) 
	 {
		 String cardPath = _card.getImagePath(); //Setting card path in folder

	     ImageIcon img = new ImageIcon(imageLocation + cardPath + imageType);
	        
	     System.out.println(imageLocation + cardPath + imageType);
	        
	     //Create a card and add it to the deck
	     Card card = new Card(img);
	     card.moveTo(xPos, yPos);
	        
	     xPos += 20;
	        
	     deck.add(card);
	     repaint();
	 }
	 
	 public void paintComponent(Graphics g) 
	 {
		 /*
		  * Paint background
		  */
		 int width = getWidth();  //Width of JComponent
		 int height = getHeight(); //Height of JComponent
		
	     g.setColor(BACKGROUND_COLOR);
	     g.fillRect(0, 0, width, height);
	     
	     /*
	      * Display the cards, starting with the first array element
	      */
	     for (int i = 0; i < deck.size(); i++) 
	     {
	        if (deck.get(i) != null) 
	        {
	            deck.get(i).draw(g, this);
	        }

	     }
	        
	  } 
	 
	 public void setYPosition(int yPos) //Re-setting card y-axis position
	 {
		 this.yPos = yPos;
	 }
	 
}