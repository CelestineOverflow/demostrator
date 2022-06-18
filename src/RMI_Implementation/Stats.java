package RMI_Implementation;

import java.io.Serializable;
import java.util.Random;

public class Stats implements Serializable {
    private final String robotName;
    private final int battery;
    private final int binLevel;
    private Job job;
    private final Random random;

    public Stats(String robotName) {
        this.robotName = robotName;
        random = new Random();
        battery = random.nextInt(101);
        binLevel = random.nextInt(101);
        job = new Job("Empty", 0);
    }

    public static void printData(Stats stats) {
        System.out.printf("|Robot Name = %s\n", stats.getRobotName());
        System.out.printf("|battery = %d%%\n", stats.getBattery());
        System.out.printf("|bin level = %d%%\n", stats.binLevel);
        System.out.printf("|job = %s  Completed = %d%% Schedule = %s\n", stats.getJob().getJobType(), stats.getJob().getJobCompleteness(), stats.getJob().getScheduleTime().getString());
        System.out.printf("\n---end--of--stats---\n");
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

    public void setJob(Job job) {
        this.job = job;
    }
}
