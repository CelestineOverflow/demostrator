import java.util.ArrayList;
import java.util.Random;

public class StatsWorker {
    private ArrayList<RobotValues> robotsValues;
    private Random random;
    public StatsWorker(){
        robotsValues = new ArrayList<RobotValues>();
    }
    public void generateValues(ArrayList<String> names){
        for(String name: names){
            generateValue(name);
        }
    }
    public void generateValue(String name){
        int battery = getRandInRange(0, 100);
        int binLevel = getRandInRange(0, 100);
        Job job = new Job("Empty", 0);
        robotsValues.add(new RobotValues(name, battery, binLevel, job));
    }
    public int getRandInRange(int from, int to){
        if(random == null){
            random = new Random();
        }
        return random.nextInt((to - from) + 1) + from;
    }
    public RobotValues getRobotValues(int selected){
        return robotsValues.get(selected);
    }
}
