package br.ufrn.imd.blackjackServer.remotes;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.ufrn.imd.blackjackServer.model.Player;

public interface IGameRemote extends Remote {
	
	public Boolean addPlayer(Player player) throws RemoteException;
	
}
