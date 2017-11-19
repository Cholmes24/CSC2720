import javax.swing.JOptionPane;
public class GUIPayroll {
public static void main(String[] args){
	String name=JOptionPane.showInputDialog(null,"Enter employee name: ","Input",JOptionPane.QUESTION_MESSAGE);
	String hours=JOptionPane.showInputDialog(null, "Enter numbers of hours worked in a week: ","Input",JOptionPane.QUESTION_MESSAGE);
	int Hours=Integer.parseInt(hours);
	String rate=JOptionPane.showInputDialog(null, "Enter Hourly Pay Rate: ","Input",JOptionPane.QUESTION_MESSAGE);
	double Rate=Double.parseDouble(rate);
	String output=("\nEmployee Name: "+name+"\nHours Worked: "+hours+"\nPay Rate: $"+rate);
	JOptionPane.showMessageDialog(null, output,"Employee Payroll Info",1);
	String name1=JOptionPane.showInputDialog(null,"Enter employee name: ","Input",JOptionPane.QUESTION_MESSAGE);
	String hours1=JOptionPane.showInputDialog(null, "Enter numbers of hours worked in a week: ","Input",JOptionPane.QUESTION_MESSAGE);
	int Hours1=Integer.parseInt(hours);
	String rate1=JOptionPane.showInputDialog(null, "Enter Hourly Pay Rate: ","Input",JOptionPane.QUESTION_MESSAGE);
	double Rate1=Double.parseDouble(rate);
	String output1=("\nEmployee Name: "+name+"\nHours Worked: "+hours+"\nPay Rate: $"+rate);
	JOptionPane.showMessageDialog(null, output,"Employee Payroll Info",1);
	String name2=JOptionPane.showInputDialog(null,"Enter employee name: ","Input",JOptionPane.QUESTION_MESSAGE);
	String hours2=JOptionPane.showInputDialog(null, "Enter numbers of hours worked in a week: ","Input",JOptionPane.QUESTION_MESSAGE);
	int Hours2=Integer.parseInt(hours);
	String rate2=JOptionPane.showInputDialog(null, "Enter Hourly Pay Rate: ","Input",JOptionPane.QUESTION_MESSAGE);
	double Rate2=Double.parseDouble(rate);
	String output2=("\nEmployee Name: "+name+"\nHours Worked: "+hours+"\nPay Rate: $"+rate);
	JOptionPane.showMessageDialog(null, output,"Employee Payroll Information",1);
}
}
