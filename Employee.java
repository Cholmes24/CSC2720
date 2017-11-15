package Constructs;
import java.sql.*;
public class Employee {
	private int ID;
	private String firstName;
	private String lastName;
	private String title;
	private int ssn;
	private String address;
	private int phone;
	private EmergencyContact a;
	private String sqlCommand;
	public Employee(int ID) {
		this.ID=ID;
		Connection conn=null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Manager?useSSL=false","student","student");
			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT * FROM Employee WHERE EMPLOYEE_ID="+ID);
			if(reslt.next()) {
				this.lastName=reslt.getString("Last_Name");
				this.firstName=reslt.getString("First_Name");
				this.title=reslt.getString("Job_Title");
				this.ssn=reslt.getInt("SSN");
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
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Manager?useSSL=false","student","student");
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
	public Employee(String lastName,String firstName,String title,int ssn, String address, int phone) {
		this.lastName=lastName;
		this.firstName=firstName;
		this.title=title;
		this.ssn=ssn;
		this.address=address;
		this.phone=phone;
		sqlCommand="insert into Employee (Last_Name,First_Name,Job_Title,SSN,Address,Phone_Number) values('"+lastName+"','"+firstName+"','"+title+"',"+ssn+",'"+address+"',"+phone+");";
		Connection conn=null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Manager?useSSL=false","student","student");
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
	public int getSSN() {
		return ssn;
	}
	public String getContact() {
		return "Name: "+firstName+" "+lastName+"\nPHONE NUMBER: "+phone+"\nADDRESS: "+address;
	}
	public String getFullTitle() {
		return firstName+" "+lastName+" is a:"+title;
	}
	public void setLastName(String name) {
		this.lastName=name;
		this.sqlCommand="UPDATE Employee SET Last_Name="+lastName+"WHERE Employee_ID="+ID;
		SQLInterface();
	}
	public void setFirstName(String name) {
		this.firstName=name;
		this.sqlCommand="UPDATE Employee SET First_Name='"+firstName+"' WHERE Employee_ID="+ID;
		SQLInterface();
	}
	public void setJob_Title(String t) {
		this.title=t;
		this.sqlCommand="UPDATE Employee SET Job_Title='"+title+"' WHERE Employee_ID="+ID;
		SQLInterface();
	}
	public void setSSN(int n) {
		this.ssn=n;
		this.sqlCommand="UPDATE Employee SET SSN="+ssn+"WHERE Employee_ID="+ID;
		SQLInterface();
	}
	public void setAdress(String a) {
		this.address=a;
		this.sqlCommand="UPDATE Employee SET Address="+address+"WHERE Employee_ID="+ID;
		SQLInterface();
	}
	public void setPhone(int n) {
		this.phone=n;
		this.sqlCommand="UPDATE Employee SET Phone_Number="+phone+"WHERE Employee_ID="+ID;
		SQLInterface();
	}
	public void createContact(String name,String address, int num) {
		EmergencyContact a=new EmergencyContact(name,address,num,ID);
	}
	public void SQLInterface() {
		Connection conn=null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Manager?useSSL=false","student","student");
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	// TODO Auto-generated method stub
	//
	/*SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE table $table_name; 
SET FOREIGN_KEY_CHECKS = 1;
	 * try {

			Statement Stmnt = conn.createStatement();
			ResultSet reslt = Stmnt.executeQuery("select * from employee");
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	 */

}
