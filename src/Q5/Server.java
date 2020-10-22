package Q5;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends ConnectionImplement {

    public Server() throws RemoteException {
        super();
    }

    public static void main(String args[]) {
        try {
            // Instantiating the connection class 
            ConnectionImplement obj = new ConnectionImplement();
            // exporting the remote object to the stub 
            ConnectInterface stub = (ConnectInterface) UnicastRemoteObject.exportObject(obj, 0);
            // Creating and Binding the remote object (stub) in the registry 
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Hello", stub);
            System.out.println("Server is ready….");
        } catch (AlreadyBoundException | RemoteException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
