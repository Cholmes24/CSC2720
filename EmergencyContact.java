package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmergencyContact {
	private String name;
	private String address;
	private String phone;
	private int ID;
	private int EID;
	private String sqlCommand;
	public EmergencyContact() {
	}
	public EmergencyContact(int ID) {
		this.ID=ID;
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());
			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT * FROM Emergency_Contact WHERE Emergency_Contact_ID="+ID+";");
			if(reslt.next()) {
				this.name=reslt.getString("Name");
				this.address=reslt.getString("Address");
				this.phone=reslt.getString("Phone_Number");
				this.EID=reslt.getInt("employee_Employee_ID");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public EmergencyContact(String name, String address, String num, int EID ) {
		this.name=name;
		this.address=address;
		this.phone=num;
		this.EID=EID;
		sqlCommand="INSERT INTO Emergency_Contact (Name,Address,Phone_Number,employee_Employee_ID) values(\""+name+"\",\""+address+"\",\""+num+"\","+EID+");";
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sqlCommand);
			ResultSet reslt = stmnt.executeQuery("SELECT Emergency_Contact_ID FROM Emergency_Contact WHERE Phone_Number=\""+phone+"\";");
			if(reslt.next()) {
				this.ID=reslt.getInt("Emergency_Contact_ID");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public String getRecentContact() {
		return "Name: "+name+"\nADDRESS: "+address+"\nPhone Number: "+phone;
	}
	public String getPrimaryContact() {
		String phone=null;
		String name=null;
		String address=null;
		Connection conn=null;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT * FROM Emergency_Contact WHERE Emergency_Contact_ID="+1+";");
			if(reslt.next()) {
				phone=reslt.getString("Phone_Number");
				name=reslt.getString("Name");
				address=reslt.getString("Address");
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		if(phone.equals(null)||name.equals(null)||address.equals(null)) {
			return"Emergency Contact Incomplete";
		}else {
			return "Name: "+name+"\nADDRESS: "+address+"\nPHONE NUMBER: "+phone;
		}
	}
	public EmergencyContact [] getAllContacts() {
		Connection conn=null;
		EmergencyContact [] a = null;
		int i=0;
		try {
			Class.forName(MYConnection.getDriver());
			conn = DriverManager.getConnection(MYConnection.getUrl(),MYConnection.getUsername(),MYConnection.getPassword());			Statement stmnt = conn.createStatement();
			ResultSet reslt = stmnt.executeQuery("SELECT MAX(Emergency_Contact_ID) FROM Emergency_Contact;");
			if(reslt.next()) 
				a= new EmergencyContact [reslt.getInt("MAX(Emergency_Contact_ID)")];
			reslt = stmnt.executeQuery("SELECT * FROM Emergency_Contact ;");
			while(reslt.next()) {
				a[i]= new EmergencyContact(reslt.getInt("Emergency_Contact_ID"));
				i++;
			}
			conn.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
			return a;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public int getID() {
		return ID;
	}
	public int getEID() {
		return EID;
	}
	public void setName(String name) {
		this.name=name;
		this.sqlCommand="UPDATE Emergency_Contact SET Name=\""+name+"\" WHERE Emergency_Contact_ID="+ID+";";
		SQLInterface();
	}
	public void setAddress(String address) {
		this.address=address;
		this.sqlCommand="UPDATE Emergency_Contact SET Address=\""+address+"\" WHERE Emergency_Contact_ID="+ID+";";
		SQLInterface();
	}
	public void setPhone(String phone) {
		this.phone=phone;
		this.sqlCommand="UPDATE Emergency_Contact SET Phone_Number=\""+phone+"\" WHERE Emergency_Contact_ID="+ID+";";
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
}
