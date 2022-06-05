package RMI_Implementation;

import java.io.Serializable;
import java.util.Random;

public class Stats implements Serializable {
    private String robotName;
    private int battery;
    private int binLevel;
    private Job job;
    private Random random;
    public Stats(String robotName) {
        this.robotName = robotName;
        random = new Random();
        battery = random.nextInt(101);
        binLevel = random.nextInt(101);
        job = new Job("Empty", 0);
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getRobotName() {
        return robotName;
    }

    public int getBattery() {
        return battery;
    }

    public int getBinLevel() {
        return binLevel;
    }

    public Job getJob() {
        return job;
    }

    public static void printData(Stats stats) {
        System.out.printf("|Robot Name = %s\n", stats.getRobotName());
        System.out.printf("|battery = %d%%\n", stats.getBattery());
        System.out.printf("|bin level = %d%%\n", stats.binLevel);
        System.out.printf("|job = %s  Completed = %d%%\n", stats.getJob().getJobType(), stats.getJob().getJobCompleteness());
        System.out.printf("\n---end--of--stats---\n");
    }
}
