package br.ufrn.imd.blackjack.remotes;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.ufrn.imd.blackjack.exceptions.InputTimeoutException;

public interface IGameClient extends Remote {
	
	public String getName() throws RemoteException;

	public void showMessage(String message) throws RemoteException;
	
	public String getCommand() throws InputTimeoutException, RemoteException;
	
}
