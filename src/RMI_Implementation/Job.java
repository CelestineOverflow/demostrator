package RMI_Implementation;

import java.io.Serializable;

public class Job implements Serializable {
    private final Time scheduleTime;
    private String jobType;
    private int jobCompleteness;

    public Job(String jobType, int jobCompleteness, Time scheduleTime) {
        this.jobType = jobType;
        this.jobCompleteness = jobCompleteness;
        this.scheduleTime = scheduleTime;
    }

    public Job(String jobType, int jobCompleteness) {
        this.jobType = jobType;
        this.jobCompleteness = jobCompleteness;
        this.scheduleTime = new Time();
    }

    public Time getScheduleTime() {
        return scheduleTime;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public int getJobCompleteness() {
        return jobCompleteness;
    }

    public void setJobCompleteness(int jobCompleteness) {
        this.jobCompleteness = jobCompleteness;
    }
}
