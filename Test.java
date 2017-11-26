package classes;


public class Test {
	public static void main(String [] arg) {
		new Job("DataBase",7.25);
			Employee a=new Employee ("Holmes","Christian","Database",123456789);
			System.out.println(a.getFullTitle());
			new Job("Antagonist",0.25);
			a=new Employee ("Kong","Donkey","Antagonist",000000000);
			System.out.println(a.getFullTitle());
			a=new Employee(1);
			a.setFirstName("Cameron");
			new Job("Hero",0.75);
			a=new Employee ("Mario","Mushroom","Hero",000000001,"1 Mushroom Kingdom", "123456789");
			System.out.println(a.getFullTitle());
			a.createEmergencyContact("My Guy","123 Mushroom Kingdom", "1234567891");
			a.createEmergencyContact("shy Guy","321 Mushroom Kingdom", "000000000");
			System.out.println(a.getContact());
			System.out.println(a.getEmergencyContact().getPrimaryContact());
			a=new Employee(1);
			a.createShift("2017-11-24", "08:00:00", "09:00:00");
			a.createShift("2017-11-25", "22:00:00", "15:00:00");
			a.createShift("2017-11-27", "12:00:00", "14:00:00");
			a=new Employee(2);
			a.createShift("2017-11-24", "18:00:00", "20:00:00");
			a.createShift("2017-11-26", "12:00:00", "14:00:00");
	}
}
//TODO Auto-generated method stub
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