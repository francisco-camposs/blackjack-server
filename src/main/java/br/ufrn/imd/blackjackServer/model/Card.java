package br.ufrn.imd.blackjackServer.model;

import java.util.Random;

import br.ufrn.imd.blackjackServer.enums.Rank;
import br.ufrn.imd.blackjackServer.enums.Suit;
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