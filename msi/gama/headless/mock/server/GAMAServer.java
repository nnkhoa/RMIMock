package msi.gama.headless.mock.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import msi.gama.headless.mock.client.GAMAClientInterface;

public class GAMAServer extends UnicastRemoteObject implements GAMAServerInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<GAMAClientInterface> GAMAClients;

	protected GAMAServer() throws RemoteException {
		GAMAClients = new ArrayList<GAMAClientInterface>();
		// TODO Auto-generated constructor stub
	}
	
	public int findByName(String clientName){
		int pos = 0;
		for (int i = 0; i < GAMAClients.size(); i  = i + 1){
			try {
				if (GAMAClients.get(i).getName().equals(clientName)){
					pos = i;
					break;
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return pos;
	}

	@Override
	public synchronized void registerClient(GAMAClientInterface Client) throws RemoteException {
		// TODO Auto-generated method stub
		this.GAMAClients.add(Client);
	}

	@Override
	public synchronized void getTurn(String clientName) throws RemoteException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		
		System.out.println(clientName + " has finished");
		
		int pos = this.findByName(clientName);
		
//		GAMAClients.get(pos).isTurn();
		if(pos == 0){
			GAMAClients.get(1).setTurn(true);
			System.out.println(GAMAClients.get(1).getName() + " is next");
		}else{
			GAMAClients.get(0).setTurn(true);
			System.out.println(GAMAClients.get(0).getName() + " is next");
		}
	}

	@Override
	public void checkStatus() throws RemoteException {
		// TODO Auto-generated method stub
		while(GAMAClients.size() != 2){
			System.out.println("Waiting for other machines to connect!");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!GAMAClients.get(0).isTurn()){
			GAMAClients.get(0).setTurn(true);
		}
		
	}
	

}
