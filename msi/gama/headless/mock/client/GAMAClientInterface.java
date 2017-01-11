package msi.gama.headless.mock.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GAMAClientInterface extends Remote {
	boolean isTurn() throws RemoteException;
	void isMyTurnNow() throws RemoteException;
	String getName() throws RemoteException;
	void setTurn(boolean myTurn) throws RemoteException;
//	void sendToServer() throws RemoteException;
}
