package msi.gama.headless.mock.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.TimeUnit;

import msi.gama.headless.mock.server.GAMAServerInterface;

public class GAMAClient extends UnicastRemoteObject implements GAMAClientInterface, Runnable {

	/**
	 * 
	 */
	
	private String name;
	private GAMAServerInterface GAMAServer;
	private static final long serialVersionUID = 1L;
	private boolean myTurn;

	protected GAMAClient(String name, GAMAServerInterface GAMAServer) throws RemoteException {
		this.setName(name);
		this.GAMAServer = GAMAServer;
		GAMAServer.registerClient(this);
		this.myTurn = false;
	}

	
	public void run(){
		try {
			GAMAServer.checkStatus();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){
			try {
				if (isTurn()){
					this.isMyTurnNow();
					TimeUnit.SECONDS.sleep(5);
					this.setTurn(!myTurn);
					GAMAServer.getTurn(this.getName());
				}else{
					System.out.println("In waiting");
					TimeUnit.SECONDS.sleep(5);
				}
			} catch (RemoteException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void isMyTurnNow() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("It's my turn now!");
	}

	public String getName() throws RemoteException{
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isTurn() throws RemoteException {
		// TODO Auto-generated method stub
		return myTurn;
	}

	public void setTurn(boolean myTurn) throws RemoteException{
		this.myTurn = myTurn;
	}

}
