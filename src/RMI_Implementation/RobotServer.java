package RMI_Implementation;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RobotServer extends UnicastRemoteObject implements Server{
    private Stats stats;

    public RobotServer(String robotName) throws RemoteException {
        super(0);
        stats = new Stats(robotName);
    }
    public static void main(String[] args) throws Exception{
        System.out.println("RMI server started");
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
        RobotServer robotServer = new RobotServer("Bender");
        Naming.rebind("//localhost/robotServer", robotServer);
        System.out.println("PeerServer bound in registry");
    }
    @Override
    public void sendInstruction(Job job) throws RemoteException {
        stats.setJob(job);
    }

    @Override
    public Stats getStats() throws RemoteException {
        return stats;
    }
}
