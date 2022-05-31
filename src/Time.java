import java.time.LocalTime;

public class Time {
    private int hour;
    private int minute;
    public Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }
    public Time(){
        LocalTime localTime = LocalTime.now();
        hour = localTime.getHour();
        minute = localTime.getMinute();
    }

    public void printTime(){
        System.out.printf("%d:%d\n", hour, minute);
    }

    public String getString() {
        return String.valueOf(hour) + ":" + String.valueOf(minute);
    }
}