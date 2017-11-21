package Constructs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Stack;
public class EmployeeShifts {
	private String shift;
	private int day;
	private String clockIn;
	private String clockOut;
	private Time workedTime;
	private int EID;
	private boolean working;
	private String sqlCommand;
	public EmployeeShifts(String shift,String clockIn, String clockOut, int EID ) {
		this.shift=shift;
		this.clockIn=clockIn;
		this.clockOut=clockOut;
		if(clockOut.compareTo(clockIn)<0) {
			this.clockOut="23:59:59";
			newShift(shift,clockOut);
		}
		this.EID=EID;
		timeWorked();
		sqlCommand="insert into Shifts (Day,Shift,Shift_Clock_In,Shift_Clock_Out,Hours_Worked,employee_Employee_ID) values(DAYOFWEEK('"+shift+"'),'"+shift+"','"+clockIn+":00','"+clockOut+":00','"+workedTime.toDecimal()+"',"+EID+");";
		Connection conn=null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Manager?useSSL=false","student","student");
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
			ResultSet reslt=stmnt.executeQuery("SELECT Day FROM Shifts WHERE employee_Employee_ID="+EID+" AND Shift="+shift+" AND Shift_Clock_In='"+clockIn+"';");
			if(reslt.next())
				day=reslt.getInt("Day");
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public EmployeeShifts(int day,int EID ) {
		this.day=day;
		this.EID=EID;
		Connection conn=null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Manager?useSSL=false","student","student");
			Statement stmnt = conn.createStatement();
			ResultSet reslt=stmnt.executeQuery("SELECT * FROM Shifts WHERE employee_Employee_ID="+EID+" AND WHERE Day="+day);
			if(reslt.next()) {
				this.shift=reslt.getString("Shift");
				this.clockIn=reslt.getString("Shift_Clock_In");
				this.clockOut=reslt.getString("Shift_Clock_Out");
				timeWorked();
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void newShift(String shift,String clockOut) {
		new EmployeeShifts("ADDDATE(("+shift+"), INTERVAL 1 DAY)","00:00:00",clockOut,EID);
	}
	public String getShift() {
		return "This Employee works on this Date: "+shift+" from "+clockIn+":00 to "+clockOut;
	}
	public String getShiftsOnThisDay(int day) {
		String all=null;
		Connection conn=null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Manager?useSSL=false","student","student");
			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT * FROM Shifts WHERE Shift="+day);
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
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Manager?useSSL=false","student","student");
			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT * FROM Shifts WHERE Shift="+day+" AND employee_Employee_ID="+EID);
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
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Manager?useSSL=false","student","student");
			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT * FROM Shifts WHERE employee_Employee_ID="+EID);
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
	public String getAllShifts() {
		String all=null;
		Connection conn=null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Manager?useSSL=false","student","student");
			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT Shift,Shift_Clock_In,Shift_Clock_Out FROM Shifts");
			all="";
			while(reslt.next()) {
				all+="\nDate: "+reslt.getString("Shift");
				all+="\nClock in time: "+reslt.getString("Shift_Clock_In");
				all+="\nClock out time: "+reslt.getString("Shift_Clock_Out");
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
	void setShift(String shift, String clockIn, String clockOut) {
		this.shift=shift;
		sqlCommand="UPDATE Shifts SET shift="+shift+", Shift_Clock_In="+clockIn+":00, Shift_Clock_Out"+clockOut+":00 WHERE employee_Employee_ID="+EID+" AND WHERE Day="+day;
	}
	void setScheduleForTheYear() {
		sqlCommand="CALL SetSchedule(DAYOFWEEK('"+shift+"'),'"+shift+"','"+clockIn+":00','"+clockOut+":00',"+EID+");";
		SQLInterface();
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


		int secDiff = (60*(outNums.pop() + 60*(outNums.pop()))) - (60*(inNums.pop() + 60*(inNums.pop())));
		System.out.println(secDiff);
		int hours = secDiff/3600;
		int mins = (secDiff%3600)/60;
		int secs = (secDiff%3600);


		workedTime=new Time(hours,mins,secs);
		System.out.println(hours+":"+mins+":"+secs+"\n"+workedTime.toDecimal());
	}
}


