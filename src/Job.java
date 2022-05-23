public class Job {
    private String jobType;
    private int jobCompleteness;
    public Job(String jobType, int jobCompleteness){
        this.jobType = jobType;
        this.jobCompleteness = jobCompleteness;
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
