public class RobotValues {
    private String robotName;
    private int battery;
    private int binLevel;
    private Job job;
    public RobotValues(String robotName, int battery, int binLevel, Job job){
        this.robotName = robotName;
        this.battery = battery;
        this.binLevel = binLevel;
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
}
