package classes;

public class Time {
	private int hour;
	private int minutes;
	private int seconds;
	private double time;
	public Time (int h,int m, int s) {
		hour=h;
		minutes=m;
		seconds=s;
		time=hour+(int)((minutes/60.0+seconds/3600.0)*100+.5)/100.0;
	}
	public String toString() {
		return  hour + ":" + minutes + ":" + seconds;
	}
	public double getTime() {
		return  time;
	}
}
