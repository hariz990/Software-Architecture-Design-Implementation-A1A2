package view;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Card extends JComponent
{
	private ImageIcon cardImage;
    private int xPos; 
    private int yPos;
    
    
    public Card(ImageIcon cardImage) //Setting card image
    {
        this.cardImage = cardImage;
    }
    
    /*
     * Setting x-axis and y-axis of card on console
     */
    public void moveTo(int xPos, int yPos) 
    {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    /*
     * Drawing the card on console
     */
    public void draw(Graphics g, Component c) 
    {
        cardImage.paintIcon(c, g, xPos, yPos);
    }
}