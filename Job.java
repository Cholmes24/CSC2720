package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Job {
	private String job;
	private double pay;
	private String sqlCommand;
	public Job() {
	}
	public Job(String job) {
		this.job=job;
		sqlCommand="SELECT Pay FROM Salary WHERE Title=\""+job+"\";";
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery(sqlCommand);
			if(reslt.next()) {
			this.pay=reslt.getDouble("Pay");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public Job(String job, double pay) {
		this.job=job;
		this.pay=pay;
		sqlCommand="INSERT INTO Salary (Title,Pay) values(\""+job+"\","+pay+");";
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
	public String getJob() {
		return job;
	}
	public double getPay() {
		return pay;
	}
	Job [] getAllJobs() {
		Connection conn=null;
		Job [] a=null;
		int i=0;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT COUNT(Title) FROM Salary;");
			if(reslt.next()) {
				a=new Job [reslt.getInt("COUNT(Title)")];
				reslt = stmnt.executeQuery("SELECT Title FROM Salary;");
				while(reslt.next()) {
					a [i]=new Job (reslt.getString("Title"));
					i++;
				}
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		return a;
	}
	public void setJob(String job) {
		sqlCommand="UPDATE Salary SET Title=\""+job+"WHERE Title=\""+this.job+"\";";
		this.job=job;
	}
	public void setPay(double pay) {
		this.pay=pay;
		sqlCommand="UPDATE Salary SET pay=\""+pay+"WHERE Title=\""+job+"\";";
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
}
