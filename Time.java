package Constructs;

public class Time {
	private int hour;
	private int minutes;
	private int seconds;
	public Time (int h,int m, int s) {
		hour=h;
		minutes=m;
		seconds=s;
	}
	public String toString() {
		return  hour + ":" + minutes + ":" + seconds;
	}
}
