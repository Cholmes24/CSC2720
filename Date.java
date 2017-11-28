package Source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Date {
	private int year=2017;
	private int month=11;
	private int day=27;
		public Date (int m) {
			Connection conn=null;
			this.month=m;
			try {
				Class.forName(MYConnection.getDriver());
				conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());
				Statement stmnt = conn.createStatement();
				ResultSet reslt = stmnt.executeQuery("Select YEAR(SYSDATE());");
				if(reslt.next()) {
					year=reslt.getInt("YEAR(SYSDATE())");
				}
				reslt = stmnt.executeQuery("Select MONTH(SYSDATE());");
				if(reslt.next()) {
					month=reslt.getInt("MONTH(SYSDATE())");
				}
				reslt = stmnt.executeQuery("Select DAYOFMONTH(LAST_DAY(STR_TO_DATE(\""+year+","+m+",1\", \"%Y,%m,%d\")));");
				if(reslt.next()) {
					day=reslt.getInt("DAYOFMONTH(LAST_DAY(STR_TO_DATE(\""+year+","+m+",1\", \"%Y,%m,%d\")))");
				}
				conn.close();
			}catch (Exception exc) {
				exc.printStackTrace();
			}
	}
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
}
