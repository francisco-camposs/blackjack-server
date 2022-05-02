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
	}
	
	@Override
	public void addPlayer(IGameClient client) throws RemoteException {
		System.out.println("Adicionando jogador");
		Player player = new Player(client.getName(), 3000, new Hand());
		if (game.addPlayer(player))
			clients.put(player, client);
		client.showMessage("Você foi cadastrado");
	}

	@Override
	public void getCommand(String name, String command) throws RemoteException{

		System.out.println("Name: " + name + "   Command: " + command);
		
		for(Map.Entry<Player, IGameClient> client: clients.entrySet()) {
			
			if(client.getKey().getName().equals(name)) {
				
				String command_split[] = command.split(" ");
				
				switch(command_split[0]) {
				case "1": // pull card
					try {
						client.getKey().getHand().addCard(game.getDeck().pullCard());
						Thread.sleep(100);
						client.getValue().showMessage("Suas cartas são: " + client.getKey().getHand().toString() + " Total: " + client.getKey().getHand().getHandValue());
					} catch(RemoteException | InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case "2": // to Bet
					
					int valor = Integer.parseInt(command_split[1]);
					if (valor > 0) {
						game.toBet(client.getKey(), valor);
						client.getValue().showMessage("Sua aposta foi realizada");
					}
					break;
				case "3": // to Stand
					game.toStand(client.getKey());
					break;
				default:
					break;
				}
			}
			if(game.handPlayerValue(client.getKey()) > 21) {
				game.setVinteUm(client.getKey());
				game.toStand(client.getKey());
			}
			if(game.isPlayingRound(client.getKey())) {
				//Espera outros jogadores
				
				if(game.roundPlayersOver()) {
					//Encerra rodada, o delear joga com cada jogador.
				}
			}
			
		}
	}
	
	private class RunGame extends Thread {
		
		public void run() {
			System.out.println("TÃ¡ indo");
			Player playerToEx = null;
			
			//Esperando jogadores
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
			
			sendMessageAll("Començando o jogo");

			
			//Distribuindo cartas para os jogadores
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
						client.getValue().showMessage(message + ", " + client.getKey().getName());
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
					client.getValue().showMessage(string + ", " + client.getKey().getName());
				} catch(RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
