package Q5;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ConnectInterface extends Remote {
    public void insert(int id, String name, String surname, int age, int cell, String degree) throws RemoteException;
}
