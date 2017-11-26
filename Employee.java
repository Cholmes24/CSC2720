package classes;
import java.sql.*;
public class Employee {
	private int ID;
	private String firstName;
	private String lastName;
	private String title;
	private int ssn;
	private String address;
	private String phone;
	private EmergencyContact a;
	private EmployeeShifts b;
	private String sqlCommand;
	public Employee() {
	}
	public Employee(int ID) {
		this.ID=ID;
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());
			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT * FROM Employee WHERE EMPLOYEE_ID="+ID+";");
			if(reslt.next()) {
				this.lastName=reslt.getString("Last_Name");
				this.firstName=reslt.getString("First_Name");
				this.title=reslt.getString("Job_Title");
				this.ssn=reslt.getInt("SSN");
				this.address=reslt.getString("Address");
				this.phone=reslt.getString("Phone_Number");
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
		sqlCommand="INSERT INTO Employee (Last_Name,First_Name,Job_Title,SSN) values(\""+lastName+"\",\""+firstName+"\",\""+title+"\","+ssn+");";
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
			ResultSet reslt = stmnt.executeQuery("SELECT Employee_ID FROM Employee WHERE SSN="+ssn+";");
			if(reslt.next()) {
				this.ID=reslt.getInt("Employee_ID");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public Employee(String lastName,String firstName,String title,int ssn, String address, String phone) {
		this.lastName=lastName;
		this.firstName=firstName;
		this.title=title;
		this.ssn=ssn;
		this.address=address;
		this.phone=phone;
		sqlCommand="INSERT INTO Employee (Last_Name,First_Name,Job_Title,SSN,Address,Phone_Number) values(\""+lastName+"\",\""+firstName+"\",\""+title+"\","+ssn+",\""+address+"\",\""+phone+"\");";
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
			ResultSet reslt = stmnt.executeQuery("SELECT Employee_ID FROM Employee WHERE SSN="+ssn+";");
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
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getTitle() {
		return title;
	}
	public int getSSN() {
		return ssn;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public String getContact() {
		return "Name: "+firstName+" "+lastName+"\nPHONE NUMBER: "+phone+"\nADDRESS: "+address;
	}
	public String getFullTitle() {
		return firstName+" "+lastName+" is a:"+title;
	}
	public Employee [] getAllEID() {
		Connection conn=null;
		Employee [] a=null;
		int i=0;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());
			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT MAX(Employee_ID) FROM EMPLOYEE;");
			if(reslt.next()) {
				a=new Employee [reslt.getInt("MAX(Employee_ID)")];
				reslt = stmnt.executeQuery("SELECT Employee_ID FROM EMPLOYEE;");
				while(reslt.next()) {
					a [i]=new Employee (reslt.getInt("Employee_ID"));
					i++;
				}
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		return a;
	}
	public void setLastName(String name) {
		this.lastName=name;
		this.sqlCommand="UPDATE Employee SET Last_Name=\""+lastName+"\" WHERE Employee_ID="+ID+";";
		SQLInterface();
		if(b!=null)
		b.setLastName(name);
	}
	public void setFirstName(String name) {
		this.firstName=name;
		this.sqlCommand="UPDATE Employee SET First_Name=\""+firstName+"\" WHERE Employee_ID="+ID+";";
		SQLInterface();
		if(b!=null)
		b.setFirstName(name);
	}
	public void setJob_Title(String t) {
		this.title=t;
		this.sqlCommand="UPDATE Employee SET Job_Title=\""+title+"\" WHERE Employee_ID="+ID+";";
		SQLInterface();
	}
	public void setSSN(int n) {
		this.ssn=n;
		this.sqlCommand="UPDATE Employee SET SSN="+ssn+" WHERE Employee_ID="+ID+";";
		SQLInterface();
	}
	public void setAddress(String a) {
		this.address=a;
		this.sqlCommand="UPDATE Employee SET Address=\""+address+"\" WHERE Employee_ID="+ID+";";
		SQLInterface();
	}
	public void setPhone(String n) {
		this.phone=n;
		this.sqlCommand="UPDATE Employee SET Phone_Number=\""+phone+"\" WHERE Employee_ID="+ID+";";
		SQLInterface();
	}
	public void createEmergencyContact(String name,String address,String num) {
		a=new EmergencyContact(name,address,num,ID);
	}
	public EmergencyContact getEmergencyContact() {
		return a;
	}
	public EmergencyContact accessEmergencyContact(int contactID) {
		a=new EmergencyContact();
		return a;
	}
	public void createShift(String shift,String clockIn, String clockOut) {
		b=new EmployeeShifts(shift,clockIn,clockOut,ID,lastName,firstName);
	}
	public EmployeeShifts getShifts() {
		return b;
	}
	public EmployeeShifts accessShifts(int day) {
		b=new EmployeeShifts();
		return b;
	}
	public void SQLInterface() {
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
