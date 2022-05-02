package br.ufrn.imd.blackjack.remotes;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IGameServer extends Remote {
	
	public void addPlayer(IGameClient player) throws RemoteException;
	
	public void getCommand(String name, String command) throws RemoteException;
	
}
