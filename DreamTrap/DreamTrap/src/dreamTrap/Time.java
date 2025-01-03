package dreamTrap;

public class Time {

	private int totalTimeElapsed;
	public Time() {
		this.totalTimeElapsed=0;
	}
	
	public void update() {totalTimeElapsed++;}

	public int getTotalTimeElapsed() {
		return totalTimeElapsed;
	}
	
	
}
