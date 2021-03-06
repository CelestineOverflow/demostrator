package RMI_Implementation;

import java.io.Serializable;
import java.time.LocalTime;

public class Time implements Serializable {
    private final int hour;
    private final int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public Time() {
        LocalTime localTime = LocalTime.now();
        hour = localTime.getHour();
        minute = localTime.getMinute();
    }

    public void printTime() {
        System.out.printf("%d:%d\n", hour, minute);
    }

    public String getString() {
        return hour + ":" + minute;
    }
}