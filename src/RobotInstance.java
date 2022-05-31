public class RobotInstance {
    private String robotName;
    private int battery;
    private int binLevel;
    private Job job;
    public RobotInstance(String robotName, int battery, int binLevel, Job job){
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
    public void SetJob(Job job){
        this.job = job;
    }
}
