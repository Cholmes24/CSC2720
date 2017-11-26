package classes;
import java.sql.*;
public class MYConnection {
	private static String driver= "com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://127.0.0.1:3306/Manager?useSSL=false";
	private static String username= "student";
	private static String password= "student";
	public static String getDriver() {
		return driver;
	}
	public static String getUrl() {
		return url;
	}
	public static String getUsername() {
		return username;
	}
	public static String getPassword() {
		return password;
	}
	
}
