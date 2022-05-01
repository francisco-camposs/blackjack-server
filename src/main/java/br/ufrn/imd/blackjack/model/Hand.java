package br.ufrn.imd.blackjack.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hand {
	
	private List<Card> cards;
	private int handValue;
	
	public Hand() {
		this.cards = new ArrayList<>();
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
		this.handValue = 0;
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
