package RMI_Implementation;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class InstructionSender {//Client
    private static InstructionSender instance;
    private Server robotServer;
    private InstructionSender() throws Exception{
        robotServer = (Server) Naming.lookup("//localhost/robotServer");
    }

    public void getStats() throws RemoteException {
        Stats stats = robotServer.getStats();
        Stats.printData(stats);
    }

    public static InstructionSender getInstance() throws Exception{
        if(instance == null){
            instance = new InstructionSender();
        }
        return instance;
    }

    public void sendJob(Job job) throws RemoteException {
        robotServer.sendInstruction(job);
    }
}
