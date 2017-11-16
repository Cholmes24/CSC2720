//package Constructs;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class Test {
	public static void main(String [] arg) {
			Employee a=new Employee ("Holmes","Christian","Database",123456789);
			System.out.println(a.getFullTitle());
			a=new Employee ("Kong","Donkey","Antagonist",000000000);
			System.out.println(a.getFullTitle());
			a=new Employee(1);
			System.out.println(a.getFullTitle());
			

			
			System.out.println(a.clock());
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(a.clock());
			
			
	}
}
