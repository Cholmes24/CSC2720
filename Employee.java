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
		sqlCommand="insert into Employee (Last_Name,First_Name,Job_Title,SSN) values('"+lastName+"','"+firstName+"','"+title+"',"+ssn+");";
		Connection conn=null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Restauraunt?useSSL=false","student","student");
			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT * FROM Employee WHERE EMPLOYEE_ID="+ID);
			if(reslt.next()) {
				this.lastName=reslt.getString("Last_Name");
				this.firstName=reslt.getString("First_Name");
				this.title=reslt.getString("Job_Title");
				this.ssn=reslt.getInt("SSN");
				this.clockIn=reslt.getString("Shift_Clock_In");
				this.clockOut=reslt.getString("Shift_Clock_In");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public Employee(String lastName,String firstName,String title,int ssn) {
		this.lastName=lastName;
		this.firstName=firstName;
		this.title=title;
		this.ssn=ssn;
		sqlCommand="insert into Employee (Last_Name,First_Name,Job_Title,SSN) values('"+lastName+"','"+firstName+"','"+title+"',"+ssn+");";
		Connection conn=null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Restauraunt?useSSL=false","student","student");
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
			ResultSet reslt = stmnt.executeQuery("SELECT Employee_ID FROM Employee WHERE SSN="+ssn);
			if(reslt.next()) {
				this.ID=reslt.getInt("Employee_ID");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public Employee(String lastName,String firstName,String title,int ssn,String clockIn,String clockOut) {
		this.lastName=lastName;
		this.firstName=firstName;
		this.title=title;
		this.ssn=ssn;
		this.clockIn=clockIn;
		this.clockOut=clockOut;
		sqlCommand="insert into Employee (Last_Name,First_Name,Job_Title,SSN,Shift_Clock_In,Shift_Clock_Out) values('"+lastName+"','"+firstName+"','"+title+"',"+ssn+",'"+clockIn+"','"+clockOut+"');";
		Connection conn=null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Restauraunt?useSSL=false","student","student");
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
			ResultSet reslt = stmnt.executeQuery("SELECT Employee_ID FROM Employee WHERE SSN="+ssn);
			if(reslt.next()) {
				this.ID=reslt.getInt("Employee_ID");
			}
			conn.close();
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
