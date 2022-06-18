package RMI_Implementation;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class InstructionClient {//Client
    private static InstructionClient instance;
    private final Server robotServer;

    private InstructionClient() throws Exception {
        robotServer = (Server) Naming.lookup("//localhost/robotServer");
    }

    public static InstructionClient getInstance() throws Exception {
        if (instance == null) {
            instance = new InstructionClient();
        }
        return instance;
    }

    public Stats getStats() throws RemoteException {
        Stats stats = robotServer.getStats();
        return stats;
    }

    public void sendJob(Job job) throws RemoteException {
        robotServer.sendInstruction(job);
    }
}
