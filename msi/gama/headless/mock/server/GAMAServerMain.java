package msi.gama.headless.mock.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GAMAServerMain {
	public static void main(String args[]) throws RemoteException{
		Registry registry = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
		registry.rebind("GamaServer", new GAMAServer());
	}
}
