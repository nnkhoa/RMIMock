package msi.gama.headless.mock.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import msi.gama.headless.mock.server.GAMAServerInterface;

public class GAMAClientMain {
	public static void main(String args[]) throws MalformedURLException, RemoteException, NotBoundException{
		String GAMAServerURL = "rmi://127.0.0.1:"+args[1]+"/GamaServer";
		GAMAServerInterface gamaServer = (GAMAServerInterface) Naming.lookup(GAMAServerURL);
		
		new Thread(new GAMAClient(args[0], gamaServer)).start();
	}
}
