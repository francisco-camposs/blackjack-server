package br.imd.ufrn.blackjackServer;

import java.util.List;

import br.imd.ufrn.blackjackServer.model.Card;
import br.imd.ufrn.blackjackServer.model.Dealer;
import br.imd.ufrn.blackjackServer.model.Deck;
import br.imd.ufrn.blackjackServer.model.Player;

public class Game {
	private Dealer dealer;
	private List<Player> players;
	private int[] apostas = {0,0,0,0};
	private Deck deck;
	
	public Game() {
		this.dealer = new Dealer();
		this.deck = new Deck();
	}
	
	public void addPlayer(Player player) {
		if(!verificarPlayers(player)) {
			players.add(player);			
		}else {
			
			//Mensagem pro cliente
			System.out.println("Player já adicioando, tente outro nome!");
		}
	}

	private boolean verificarPlayers(Player player) {
		for (Player p: players) {
			if(p.getName().equals(player.getName())) {
				return true;
			}
		}
		return false;			
	}
	
	//Hit
	public void pullCardFromDeck(Player player) {
		Card card = deck.pullCard();
		if(card!=null) {
			for(Player p: players) {
				if(player.getName().equals(p.getName())) {
					p.getHand().addCard(card);
					
					//Comunicar cliente
				}
			}
		}else {
			//Deck sem cartas
		}
	}
	
	//Bet
	public void toBet(Player player) {
		int[] teste = {1,2,3};
		teste[2];
	}
	
	//Sub
	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public List<Player> getPlayers() {
		return players;
	}
}
