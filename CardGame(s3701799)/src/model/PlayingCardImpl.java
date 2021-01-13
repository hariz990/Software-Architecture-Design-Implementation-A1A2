package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard
{
	private Suit suit;
	private Value value;
	private int score;
	
	public PlayingCardImpl(Suit suit, Value value)
	{
		this.suit = suit; 
		this.value = value; 
		this.score = getScore();
	}
	
	@Override
	public Suit getSuit() //card shape
	{
		return this.suit;
	}

	@Override
	public Value getValue() //card value
	{
		return this.value;
	}

	@Override
	public int getScore() 
	{
		switch(value) //Storing card's score according to card's value
		{
			case ACE:
				this.score = 1;
				break;
			case JACK:
				this.score = 10;
				break;
			case QUEEN:
				this.score = 10; 
				break;
			case KING:
				this.score = 10; 
				break;
			case TWO:
				this.score = 2; 
				break;
			case THREE:
				this.score = 3; 
				break;
			case FOUR:
				this.score = 4; 
				break;
			case FIVE:
				this.score = 5; 
				break;
			case SIX:
				this.score = 6; 
				break;
			case SEVEN:
				this.score = 7; 
				break;
			case EIGHT:
				this.score = 8; 
				break;
			case NINE:
				this.score = 9; 
				break;
			case TEN:
				this.score = 10;
				break;
		}
		
		return this.score;
	}
	
	public String getImagePath() 
	{
		/*
		 * Finding image according to its shape and value in card folder
		 */
		
		String prefix = "";
		String suffix = "";
		
		switch (suit) //SHAPE
		{
		
		case CLUBS:
			prefix = "c";
			break;
			
		case DIAMONDS:
			prefix = "d";
			break;
			
		case SPADES:
			prefix = "s";
			break;
			
		case HEARTS:
			prefix = "h";
			break;
			
		}
		
		
		switch (value) //VALUE
		{
		
		case ACE:
			suffix = "a";
			break;
			
		case TWO:
			suffix = "2";
			break;
			
		case THREE:
			suffix = "3";
			break;
			
		case FOUR:
			suffix = "4";
			break;
			
		case FIVE:
			suffix = "5";
			break;
			
		case SIX:
			suffix = "6";
			break;
			
		case SEVEN:
			suffix = "7";
			break;
			
		case EIGHT:
			suffix = "8";
			break;
			
		case NINE:
			suffix = "9";
			break;
			
		case TEN:
			suffix = "t";
			break;
			
		case JACK:
			suffix = "j";
			break;
			
		case QUEEN:
			suffix = "q";
			break;
			
		case KING:
			suffix = "k";
			break;		
		
		}
		
		return suffix + prefix;
	}

	@Override
	public boolean equals(PlayingCard card) 
	{
		if(card.getValue() == this.value && card.getSuit() == this.suit) //If the card has the same value and shape
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toString()
	{
		return String.format("Suit: %s, Value: %s, Score: %d", 
				this.suit,this.value,this.score);
	}

}
