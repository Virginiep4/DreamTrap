package dreamTrap;

public class Time {

    private int totalTimeElapsed;
    private static Time timer;
    public Time() {
        timer=this;
        this.totalTimeElapsed=0;
    }

    public void update() {totalTimeElapsed++;}

    public int getTotalTimeElapsed() {
        return totalTimeElapsed;
    }
    public static Time getInstance() {
        return timer;
    }


}