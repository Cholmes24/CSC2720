//package Constructs;
import java.sql.*;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Stack;
public class Employee {
	private int ID;
	private String firstName;
	private String lastName;
	private String title;
	private int ssn;
	private String clockIn;
	private String clockOut;
	private String sqlCommand;
	private boolean working;
	public Employee(int ID) {
		this.ID=ID;
		working = false;
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
		working = false;
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
	
	public String clock () {
		DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		
		if (!working) {
			working = true;
			clockIn = df.format(calobj.getTime());
			return clockIn;
		}
		else {
			working = false;
			clockOut = df.format(calobj.getTime());
			System.out.println(timeWorked());
			return clockOut;
		}
	}
	public String timeWorked() {
		String inTime = clockIn.replaceAll("[\\D]",  " ");
		String outTime = clockOut.replaceAll("[\\D]", " ");
		System.out.println(inTime);
		System.out.println(outTime);
		System.out.println();
		
		Stack<Integer> inNums = new Stack<Integer>();
		Stack<Integer> outNums = new Stack<Integer>();
		int index = 0;
		for (int i = 0; i < inTime.length(); i++) {
			if(inTime.charAt(i) == ' ') {
				inNums.push(Integer.parseInt(inTime.substring(index, i)));
				index = i+1;
			}
		}
		inNums.push(Integer.parseInt(inTime.substring(index, inTime.length())));
		
		index = 0;
		for (int i = 0; i < outTime.length(); i++) {
			if(outTime.charAt(i) == ' ') {
				outNums.push(Integer.parseInt(outTime.substring(index, i)));
				index = i+1;
			}
		}
		outNums.push(Integer.parseInt(outTime.substring(index, outTime.length())));
		
		
		int secDiff = (outNums.pop() + 60*(outNums.pop() + 60*(outNums.pop()))) - (inNums.pop() + 60*(inNums.pop() + 60*(inNums.pop())));
		System.out.println(secDiff);
		int hours = secDiff/3600;
		int mins = (secDiff%3600)/60;
		int secs = (secDiff%3660);
		
		return "Time worked today: " + hours + ":" + mins + ":" + secs;
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
