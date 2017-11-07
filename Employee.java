package Constructs;
import java.sql.*;
public class Employee {
	private int ID;
	private String firstName;
	private String lastName;
	private String title;
	private int ssn;
	private String clockIn;
	private String clockOut;
	private String sqlCommand;
	public Employee(int ID) {
		this.ID=ID;
		this.lastName=lastName;
		this.firstName=firstName;
		this.title=title;
		this.ssn=ssn;
		sqlCommand="insert into Employee (Last_Name,First_Name,Job_Title,SSN) values("+lastName+","+firstName+","+title+","+ssn+")";
		try {
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost//:3306/Restauraunt","root","Science!");
			Statement Stmnt = conn.createStatement();
			ResultSet reslt = Stmnt.executeQuery("SELECT * FROM Employee WHERE EMPLOYEE_ID="+ID);
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public Employee(int ID,String lastName,String firstName,String title,int ssn) {
		this.ID=ID;
		this.lastName=lastName;
		this.firstName=firstName;
		this.title=title;
		this.ssn=ssn;
		sqlCommand="insert into Employee (Last_Name,First_Name,Job_Title,SSN) values("+lastName+","+firstName+","+title+","+ssn+")";
		try {
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost//:3306/Restauraunt","root","Science!");
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public Employee(int ID,String lastName,String firstName,String title,int ssn,String clockIn,String clockOut) {
		this.ID=ID;
		this.lastName=lastName;
		this.firstName=firstName;
		this.title=title;
		this.ssn=ssn;
		this.clockIn=clockIn;
		this.clockOut=clockOut;
		sqlCommand="insert into Employee (Last_Name,First_Name,Job_Title,SSN,Shift_Clock_In,Shift_Clock_Out) values("+lastName+","+firstName+","+title+","+ssn+","+clockIn+","+clockOut+")";
		try {
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost//:3306/Restauraunt","root","Science!");
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public int getID() {
		return ID;
	}
	public String getFullTitle() {
		return firstName+" "+lastName+" "+title;
	}
	public String getHours() {
		return "The employee works from "+clockIn+" to "+clockOut;
	}


	// TODO Auto-generated method stub
	//
	/*try {

			Statement Stmnt = conn.createStatement();
			ResultSet reslt = Stmnt.executeQuery("select * from employee");
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	 */

}
