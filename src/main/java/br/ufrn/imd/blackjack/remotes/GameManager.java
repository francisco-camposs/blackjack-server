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
	}

	
	
	private class RunGame extends Thread {
		
		public void run() {
			System.out.println("Tá indo");
			Player playerToEx = null;
			while(true) {
				try {
					System.out.println("Testando");
					Thread.sleep(3000);
					for (Player player: game.getPlayers()) {
						playerToEx = player;
					}
				
				} catch (InterruptedException e) {
					game.getPlayers().remove(playerToEx);
					e.printStackTrace();
				}
			}
		}
	}

}
