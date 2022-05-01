package br.ufrn.imd.blackjack.model;

import br.ufrn.imd.blackjack.enums.Rank;
import br.ufrn.imd.blackjack.enums.Suit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
	
	private Suit suit;
	private Rank rank;
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public String toString() {
		return ("[" + rank + " de " + suit + "] (" + this.getValue()+")");
	}
	
	public int getValue() {
		return rank.value;
	}
}
