package br.ufrn.imd.blackjack.remotes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import br.ufrn.imd.blackjack.model.Hand;
import br.ufrn.imd.blackjack.model.Player;
import br.ufrn.imd.blackjack.service.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameManager extends UnicastRemoteObject implements IGameServer {
		
	private Game game;
	private Map<Player, IGameClient> clients;
	private static final long serialVersionUID = 1L;
	
	public GameManager() throws RemoteException {
		super();
		this.game = new Game();
		this.clients = new HashMap<>();
		new RunGame().start();
		
		
		/**
		 
		while(!game.isIniciado()) {
			try {
				System.out.println("Esperando começar");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		System.out.println("Começou");
		
		while(game.isIniciado()) {
			
			
			
		}
		 */
	}
	
	@Override
	public void addPlayer(IGameClient client) throws RemoteException {
		System.out.println("Adicionando jogador");
		Player player = new Player(client.getName(), 3000, new Hand());
		if (game.addPlayer(player))
			clients.put(player, client);
		client.showMessage("Você foi cadastrado");
	}

	
	
	private class RunGame extends Thread {
		
		public void run() {
			System.out.println("TÃ¡ indo");
			Player playerToEx = null;
			
			int contador = 0;
			while(true) {
				try {
					System.out.println("Testando " + contador);
					Thread.sleep(5000);
					for (Player player: game.getPlayers()) {
						playerToEx = player;
					}
					
					if(contador > 3) break;
					contador++;
				
				} catch (InterruptedException e) {
					game.getPlayers().remove(playerToEx);
					e.printStackTrace();
				}
			}
			
			sendMessageAll("Començando o jogo, ");

			System.out.println("Começou");
			for(Map.Entry<Player, IGameClient> client: clients.entrySet()) {
				
				System.out.println(game.getDeck().pullCard());
				try {
					client.getKey().getHand().addCard(game.getDeck().pullCard());
					Thread.sleep(1000);
					client.getKey().getHand().addCard(game.getDeck().pullCard());
					Thread.sleep(1000);
					client.getValue().showMessage("Suas cartas são: " + client.getKey().getHand().toString() + " Total: " + client.getKey().getHand().getHandValue());
				} catch(RemoteException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
	
	private class Notify extends Thread {
		
		private String message;
		
		public Notify(String message) {
			this.message = message;
		}
		public void run() {
			System.out.println("Notify");
			if(clients.size() > 0) {
				
				System.out.println("Começando o jogo...");
				
				for(Map.Entry<Player, IGameClient> client: clients.entrySet()) {
					try {
						client.getValue().showMessage(message + client.getKey().getName());
					} catch(RemoteException e) {
						e.printStackTrace();
					}
				}
				game.setIniciado(true);
			}
		}
	}
	private void sendMessageAll(String string) {
		if(clients.size() > 0) {
			
			System.out.println("Começando o jogo...");
			
			for(Map.Entry<Player, IGameClient> client: clients.entrySet()) {
				try {
					client.getValue().showMessage(string + client.getKey().getName());
				} catch(RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
