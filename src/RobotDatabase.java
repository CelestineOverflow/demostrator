import RMI_Implementation.Job;
import RMI_Implementation.Time;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

public class RobotDatabase {
    private ArrayList<RobotInstance> robotInstances;
    private Random random;
    
    public RobotDatabase(){
        robotInstances = new ArrayList<RobotInstance>();
    }
    public void generateValues(ArrayList<String> names) throws RemoteException {
        for(String name: names){
            generateValue(name);
        }
    }
    public void generateValue(String name) throws RemoteException {
        int battery = getRandInRange(0, 100);
        int binLevel = getRandInRange(0, 100);
        Job job = new Job("Empty", 0);
        robotInstances.add(new RobotInstance(name, battery, binLevel, job));
    }
    public int getRandInRange(int from, int to){
        if(random == null){
            random = new Random();
        }
        return random.nextInt((to - from) + 1) + from;
    }
    public RobotInstance getRobotValues(int selected){
        return robotInstances.get(selected);
    }
}
