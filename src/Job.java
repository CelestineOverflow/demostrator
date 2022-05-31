public class Job {
    private String jobType;
    private int jobCompleteness;
    private Time scheduleTime;
    public Job(String jobType, int jobCompleteness, Time scheduleTime){
        this.jobType = jobType;
        this.jobCompleteness = jobCompleteness;
        this.scheduleTime = scheduleTime;
    }
    public Job(String jobType, int jobCompleteness){
        this.jobType = jobType;
        this.jobCompleteness = jobCompleteness;
        this.scheduleTime = new Time();
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
