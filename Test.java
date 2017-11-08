package Constructs;


public class Test {
	public static void main(String [] arg) {
			Employee a=new Employee ("Holmes","Christian","Database",123456789);
			System.out.println(a.getFullTitle());
			a=new Employee ("Kong","Donkey","Antagonist",000000000);
			System.out.println(a.getFullTitle());
			a=new Employee(1);
			System.out.println(a.getFullTitle());
	}
}
