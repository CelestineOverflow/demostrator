package RMI_Implementation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
    public void sendInstruction(Job job) throws RemoteException;
    public Stats getStats() throws RemoteException;
}
