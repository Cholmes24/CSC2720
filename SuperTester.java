package Constructs;
import java.util.ArrayList;
public class SuperTester {
	public static void main(String [] args) {
		Employee a= new Employee();
		int [] b=a.getAllEID();
		ArrayList <Employee> c= new ArrayList <Employee>();
		for(int i=0;i<b.length;i++) {
		c.add(new Employee(b[i]));
		}
		GUIPrintEmployees d=new GUIPrintEmployees(c);
	}
	public static void createEmployee(String lastName,String firstName,String title,int ssn, String address, int phone) {
		Employee a=new Employee (lastName,firstName,title,ssn,address,phone);
	}

}
