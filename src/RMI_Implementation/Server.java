package RMI_Implementation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
    void sendInstruction(Job job) throws RemoteException;
    Stats getStats() throws RemoteException;
}
