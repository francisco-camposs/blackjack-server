package br.ufrn.imd.blackjack;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import br.ufrn.imd.blackjack.remotes.GameManager;
import br.ufrn.imd.blackjack.remotes.IGameServer;

public class BlackjackServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		System.setProperty("java.rmi.server.hostname","127.0.0.1");
		
		IGameServer manager = new GameManager();
		
		LocateRegistry.createRegistry(1098);
		Naming.rebind("rmi://127.0.0.1:1098/blackjack-server", manager);
		
	}

}
