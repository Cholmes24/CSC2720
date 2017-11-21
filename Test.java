package Constructs;


public class Test {
	public static void main(String [] arg) {
			Employee a=new Employee ("Holmes","Christian","Database",123456789);
			System.out.println(a.getFullTitle());
			a=new Employee ("Kong","Donkey","Antagonist",000000000);
			System.out.println(a.getFullTitle());
			a=new Employee(1);
			a.setFirstName("Cameron");
			a=new Employee ("Mario","Mushroom","Hero",000000001,"1 Mushroom Kingdom", 123456789);
			System.out.println(a.getFullTitle());
			a.createEmergencyContact("My Guy","123 Mushroom Kingdom", 123456789);
			a.createEmergencyContact("shy Guy","321 Mushroom Kingdom", 000000000);
			System.out.println(a.getContact());
			System.out.println(a.getEmergencyContact().getPrimaryContact());
			System.out.println(a.getEmergencyContact().getAllContacts());
			
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