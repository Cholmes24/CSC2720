package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Stack;
public class EmployeeShifts {
	private String lastName;
	private String firstName;
	private String shift;
	private int day;
	private String clockIn;
	private String clockOut;
	private Time workedTime;
	private int EID;
	private double salaryEarnings;
	private double totalEarnings;
	private boolean working;
	private String sqlCommand;
	public EmployeeShifts() {
	}
	public EmployeeShifts(int day,String shift, String clockIn,int EID ) {
		this.day=day;
		this.clockIn=clockIn;
		this.shift=shift;
		this.EID=EID;
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			ResultSet reslt=stmnt.executeQuery("SELECT * FROM Shifts WHERE employee_Employee_ID="+EID+" AND Day="+day+";");
			if(reslt.next()) {
				this.clockOut=reslt.getString("Shift_Clock_Out");
				this.lastName=reslt.getString("LN");
				this.firstName=reslt.getString("FN");
				this.salaryEarnings=reslt.getDouble("Salary");
				timeWorked();
				SalaryEarnings();
				TotalEarnings();
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public EmployeeShifts(String shift,String clockIn, String clockOut, int EID, String lastName, String firstName) {
		this.shift=shift;
		this.clockIn=clockIn;
		this.clockOut=clockOut;
		this.EID=EID;
		this.lastName=lastName;
		this.firstName=firstName;
		if(clockOut.compareTo(clockIn)<0) {
			newShift(shift,clockOut);
			this.clockOut="23:59:59";
		}
		timeWorked();
		SalaryEarnings();
		TotalEarnings();
		sqlCommand="insert into Shifts (Day,Shift,Shift_Clock_In,Shift_Clock_Out,Hours_Worked,employee_Employee_ID,LN,FN,Salary,Total_Earnings) values(DAYOFWEEK(\""+shift+"\"),\""+shift+"\",\""+clockIn+"\",\""+this.clockOut+"\","+workedTime.getTime()+","+EID+",\""+lastName+"\",\""+firstName+"\","+salaryEarnings+","+totalEarnings+");";
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
			ResultSet reslt=stmnt.executeQuery("SELECT Day FROM Shifts WHERE Shift=\""+shift+"\" AND Shift_Clock_In=\""+clockIn+"\" AND employee_Employee_ID="+EID+";");
			if(reslt.next())
				day=reslt.getInt("Day");
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public EmployeeShifts(String shift, String clockOut, int EID, String lastName, String firstName ) {
		this.shift=shift;
		this.clockIn="00:00:00";
		this.clockOut=clockOut;
		this.EID=EID;
		this.lastName=lastName;
		this.firstName=firstName;
		timeWorked();
		SalaryEarnings();
		System.out.print(salaryEarnings);
		TotalEarnings();
		sqlCommand="insert into Shifts (Day,Shift,Shift_Clock_In,Shift_Clock_Out,Hours_Worked,employee_Employee_ID,LN,FN,Salary,Total_Earnings) values(DAYOFWEEK("+shift+"),"+shift+",\""+clockIn+"\",\""+clockOut+"\","+workedTime.getTime()+","+EID+",\""+lastName+"\",\""+firstName+"\","+salaryEarnings+","+totalEarnings+");";
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
			ResultSet reslt=stmnt.executeQuery("SELECT Day FROM Shifts WHERE employee_Employee_ID="+EID+" AND Shift="+shift+" AND Shift_Clock_In=\""+clockIn+"\";");
			if(reslt.next())
				day=reslt.getInt("Day");
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void newShift(String shift,String clockOut) {
		new EmployeeShifts("ADDDATE(\""+shift+"\",  INTERVAL 1 DAY)",clockOut,EID,lastName,firstName);
	}
	public String getShift() {
		return shift;
	}
	public int getDay() {
		return day;
	}
	public String getClockIn() {
		return clockIn;
	}
	public String getClockOut() {
		return clockOut;
	} 
	public String getWorkedTime() {
		return workedTime.toString();
	}
	public int getEID() {
		return EID;
	}
	public String getLastName() {
		return lastName;
	} 
	public String getFirstName() {
		return firstName;
	}
	public double getSalaryEarnings() {
		return salaryEarnings;
	}
	public double getTotalEarnings() {
		return totalEarnings;
	}
	public void SalaryEarnings() {
		double pay=0.0;
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT Pay FROM Salary WHERE Title= (SELECT Job_Title FROM Employee WHERE Employee_ID="+EID+");");
			if(reslt.next()) {
				pay=reslt.getDouble("pay");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		salaryEarnings=(int)(pay*workedTime.getTime()*100+.5)/100.0;
	}
	public void TotalEarnings() {
		double pay=0.0;
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT Salary FROM Shifts WHERE employee_Employee_ID="+EID+";");
			while(reslt.next()) {
				pay+=reslt.getDouble("Salary");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		totalEarnings=pay;
	}
	public String getShiftsOnThisDay(int day) {
		String all=null;
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT * FROM Shifts WHERE Shift="+day+";");
			all="";
			while(reslt.next()) {
				all+="\nDate: "+reslt.getString("Name");
				all+="\nClock in time: "+reslt.getString("Address");
				all+="\nClock out time: "+reslt.getInt("Phone_Number");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		if(all!=null) {
			return all;
		}else {
			return "ERROR!!! Something Went Wrong";
		}
	}
	public String getShiftsOnThisDayForEmployee() {
		String all=null;
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT * FROM Shifts WHERE Shift="+day+" AND employee_Employee_ID="+EID+";");
			all="";
			while(reslt.next()) {
				all+="\nDate: "+reslt.getString("Name");
				all+="\nClock in time: "+reslt.getString("Address");
				all+="\nClock out time: "+reslt.getInt("Phone_Number");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		if(all!=null) {
			return all;
		}else {
			return "ERROR!!! Something Went Wrong";
		}
	}
	public String getShiftsForEmployee() {
		String all=null;
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT * FROM Shifts WHERE employee_Employee_ID="+EID+";");
			all="";
			while(reslt.next()) {
				all+="\nDate: "+reslt.getString("Shift");
				all+="\nClock in time: "+reslt.getString("Shift_Colck_In");
				all+="\nClock out time: "+reslt.getInt("Shift_Clock_Out");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		if(all!=null) {
			return all;
		}else {
			return "ERROR!!! Something Went Wrong";
		}
	}
	public EmployeeShifts [] getAllShifts() {
		Connection conn=null;
		EmployeeShifts a []=null;
		int i=0;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT COUNT(*) FROM Shifts;");
			if(reslt.next()) 
				a= new EmployeeShifts [reslt.getInt("COUNT(*)")];
			reslt = stmnt.executeQuery("SELECT * FROM Shifts;");
			while(reslt.next()) {
				a[i]= new EmployeeShifts(reslt.getInt("Day"),reslt.getString("Shift"),reslt.getString("Shift_Clock_In"),reslt.getInt("employee_Employee_ID"));
				i++;
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		return a;
	}
	public void setShift(String shift) {
		sqlCommand="UPDATE Shifts SET Shift=\""+shift+"\" WHERE Day="+day+" AND Shift=\""+this.shift+"\" AND Shift_Clock_In=\""+clockIn+"\" AND employee_Employee_ID=\""+EID+"\";";
		this.shift=shift;
		SQLInterface();
		setDay();
	}
	public void setDay() {
		sqlCommand="UPDATE Shifts SET Day=DAYOFWEEK("+shift+") WHERE Day="+day+" AND Shift=\""+shift+"\" AND Shift_Clock_In=\""+clockIn+"\" AND employee_Employee_ID=\""+EID+"\";";
		SQLInterface();
		Connection conn=null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Manager?useSSL=false","student","student");
			Statement stmnt = conn.createStatement();
			ResultSet reslt=stmnt.executeQuery("SELECT Day FROM Shifts WHERE Day="+day+" AND Shift=\""+shift+"\" AND Shift_Clock_In=\""+clockIn+"\" AND employee_Employee_ID=\""+EID+"\";");
			if(reslt.next()) {
				this.day=reslt.getInt("Day");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void setClockIn(String clockIn) {
		sqlCommand="UPDATE Shifts SET Shift_Clock_In=\""+clockIn+"\" WHERE Day="+day+" AND Shift=\""+shift+"\" AND Shift_Clock_In=\""+this.clockIn+"\" AND employee_Employee_ID=\""+EID+"\";";
		this.clockIn=clockIn;
		SQLInterface();
		setWorkedTime();
	}
	public void setClockOut(String clockOut) {
		sqlCommand="UPDATE Shifts SET Shift_Clock_Out=\""+clockOut+"\" WHERE Day="+day+" AND Shift=\""+shift+"\" AND Shift_Clock_In=\""+clockIn+"\" AND employee_Employee_ID=\""+EID+"\";";
		this.clockOut=clockOut;
		SQLInterface();
		setWorkedTime();
	}
	public void setWorkedTime() {
		timeWorked();
		sqlCommand="UPDATE Shifts SET Hour_Worked="+workedTime+" WHERE Day="+day+" AND Shift=\""+shift+"\" AND Shift_Clock_In=\""+clockIn+"\" AND employee_Employee_ID=\""+EID+"\";";
		SQLInterface();
	}
	public void setLastName(String lastName) {
		sqlCommand="UPDATE Shifts SET LN="+lastName+" WHERE employee_Employee_ID=\""+EID+"\";";
		this.lastName=lastName;
	}
	public void setFirstName(String firstName) {
		sqlCommand="UPDATE Shifts SET LN="+firstName+" WHERE employee_Employee_ID=\""+EID+"\";";
		this.firstName=firstName;
	}
	public void setSalaryEarnings() {
		SalaryEarnings();
		sqlCommand="UPDATE Shifts SET Salary="+salaryEarnings+" WHERE Day="+day+" AND Shift=\""+shift+"\" AND Shift_Clock_In=\""+clockIn+"\" AND employee_Employee_ID=\""+EID+"\";";
		SQLInterface();
	}
	public void setTotalEarnings() {
		TotalEarnings();
		sqlCommand="UPDATE Shifts SET Total_Earnings="+totalEarnings+" WHERE Day="+day+" AND Shift=\""+shift+"\" AND Shift_Clock_In=\""+clockIn+"\" AND employee_Employee_ID=\""+EID+"\";";
		SQLInterface();
	}
	public void setScheduleForTheYear() {
		sqlCommand="CALL SetSchedule(DAYOFWEEK(\""+shift+"\"),\""+shift+"\",\""+clockIn+"\",\""+clockOut+"\","+EID+");";
		SQLInterface();
	}
	public void SQLInterface() {
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public String clock () {
		DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		Calendar calobj = Calendar.getInstance();

		if (!working) {
			working = true;
			clockIn = df.format(calobj.getTime());
			return "They Start at:"+clockIn;
		}
		else {
			working = false;
			clockOut = df.format(calobj.getTime());
			System.out.println(workedTime.toString());
			return "They end at: "+clockOut;
		}
	}

	public void timeWorked() {
		String inTime = clockIn.replaceAll("[\\D]",  " ");
		String outTime = clockOut.replaceAll("[\\D]", " ");

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
		int secDiff;
		if(inNums.size() == 2 && outNums.size() == 2)
			secDiff = (60*(outNums.pop() + 60*(outNums.pop()))) - (60*(inNums.pop() + 60*(inNums.pop())));
		else if(inNums.size() == 3 && outNums.size() == 3)
			secDiff = (outNums.pop() + 60*(outNums.pop() + 60*(outNums.pop()))) - (inNums.pop() + 60*(inNums.pop() + 60*(inNums.pop())));
		else
			secDiff = (outNums.pop() + 60*(outNums.pop() + 60*(outNums.pop())));
		int hours = secDiff/3600;
		int mins = (secDiff%3600)/60;
		int secs = (secDiff%3660)%60;

		workedTime=new Time(hours,mins,secs);
	}
}


