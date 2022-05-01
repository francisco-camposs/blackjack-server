package br.ufrn.imd.blackjackServer.remotes;

import java.rmi.RemoteException;

import br.ufrn.imd.blackjackServer.model.Player;
import br.ufrn.imd.blackjackServer.service.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameManager implements IGameRemote {
	
	private Game game;
	
	@Override
	public Boolean addPlayer(Player player) throws RemoteException {
		return game.addPlayer(player);
	}



	public GameManager() {
		this.game = new Game();
	}

}
