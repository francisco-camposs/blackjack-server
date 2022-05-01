package br.ufrn.imd.blackjack.service;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.blackjack.model.Card;
import br.ufrn.imd.blackjack.model.Dealer;
import br.ufrn.imd.blackjack.model.Deck;
import br.ufrn.imd.blackjack.model.Player;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Game {
	
	private boolean iniciado = false;
	private Dealer dealer;
	private Deck deck;
	private List<Player> players;
	private int[] bets = {0,0,0,0};
	private boolean[] ready = {false, false, false, false};
	
	public Game() {
		this.players = new ArrayList<>();
		this.dealer = new Dealer();
		this.deck = new Deck();
	}
	
	public Boolean addPlayer(Player player) {
		if(!verificarPlayers(player)) {
			players.add(player);
			return true;
		}else {
			return false;
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
	public void toBet(Player player, int aposta) {
		bets[players.indexOf(player)] = aposta;
	}
	
	//Stand
	public void toStand(Player player) {
		ready[players.indexOf(player)] = true;
	}
	
	//See hand
	public void getHand(Player player) {
		for(Player p: players) {
			if(player.getName().equals(p.getName())) {
				String handString = p.getHand().toString();
				
				//Comunicar cliente
			}
		}
	}
	
	//See hand value
	public void handPlayerValue(Player player) {
		for(Player p: players) {
			if(player.getName().equals(p.getName())) {
				int value = p.getHand().getHandValue();
				
				//Comunicar cliente
			}
		}
	}
	
	public void handVerify(Player player) {
		for(Player p: players) {
			if(player.getName().equals(p.getName())) {
				//String handString = p.getHand().toString();
				
				if(p.getHand().getHandValue() > 21) {
					//Lose
				}else {
					//Wait decision
				}
				
				//Comunicar cliente
			}
		}
	}
	
	public void winner(Player player) {
		if((int) player.getHand().getHandValue() > dealer.getHand().getHandValue()) {
			//Player win
		}else if((int) player.getHand().getHandValue() == dealer.getHand().getHandValue()) {
			//draw
		}else {
			//dealer
		}
	}
	
	public void newRound() {
		ready[0] = false;
		ready[1] = false;
		ready[2] = false;
		ready[3] = false;
		bets[0] = 0;
		bets[1] = 0;
		bets[2] = 0;
		bets[3] = 0;
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
