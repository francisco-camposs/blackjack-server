package br.imd.ufrn.blackjackServer.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hand {
	
	private List<Card> cards;
	private int handValue;
	
	public Hand() {
		
	}
	
	public Hand(List<Card> cards) {
		this.cards = cards;
		calculateHandValue();
	}

	public void addCard(Card card) {
		cards.add(card);
		calculateHandValue();
	}
	
	public void calculateHandValue() {
		for (Card card: cards) {
			handValue+=card.getValue();
		}
	}
	
	public String toString() {
		String cardsString = "";
		for(Card card: cards) {
			cardsString += card.toString() + " "; 
		}
		return cardsString;
	}
}
