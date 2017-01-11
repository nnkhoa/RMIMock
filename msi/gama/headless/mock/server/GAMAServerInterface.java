package msi.gama.headless.mock.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import msi.gama.headless.mock.client.GAMAClientInterface;

public interface GAMAServerInterface extends Remote{
	void registerClient (GAMAClientInterface Client) throws RemoteException;
	
	void getTurn(String clientName) throws RemoteException;
	
	void checkStatus() throws RemoteException;
}
