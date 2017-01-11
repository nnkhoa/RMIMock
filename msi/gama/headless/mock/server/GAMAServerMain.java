package msi.gama.headless.mock.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GAMAServerMain {
	public static void main(String args[]) throws RemoteException{
		System.out.println("--------Info--------");
		try {
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			System.out.println("Host Address: "+ hostAddress);
			System.out.println("Server Host Name: " + System.getProperties().getProperty("java.rmi.server.hostname"));
			System.out.println("Host Name: "+ InetAddress.getLocalHost().getHostName());
			System.out.println("Host FQDN:  "+ InetAddress.getByName(hostAddress).getHostName());
			System.out.println("Local Host Name: " + System.getProperties().getProperty("java.rmi.server.useLocalHostName"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("--------------------");
		System.out.println("creating Registry...");
		Registry registry = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
		System.out.println("binding Server...");
		registry.rebind("GamaServer", new GAMAServer());
		System.out.println("Ready!");
	}
}
