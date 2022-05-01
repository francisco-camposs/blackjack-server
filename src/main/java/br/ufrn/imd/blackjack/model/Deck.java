package br.ufrn.imd.blackjack.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import br.ufrn.imd.blackjack.enums.Rank;
import br.ufrn.imd.blackjack.enums.Suit;

public class Deck {
	
	private Map<Integer, Card> cards;
	
	public Deck() {
		this.cards = new HashMap<>();
	}
	
	public Card pullCard() {
		Random random = new Random();
		
		for(;;) {
			int cardNumber = random.nextInt(51);
			int suit = cardNumber % 4;
			int rank = cardNumber % 13;
			
			Card card = new Card(Suit.values()[suit], Rank.values()[rank]);
			System.out.println("Card: " + card.toString());
			if(!cards.containsValue(cardNumber)) {
				cards.put(cardNumber, card);
				return card;
			}else {
				//Todas as cartas retiradas
				System.out.println("Deck vazio");
				break;
			}
		}
		return null;
	}

}
