import java.awt.*;
import javax.swing.*;
public class GUIPrintEmployees{
	private JLabel Info;
	public static void main(String[] args){
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.setSize(new Dimension(300,150));
		frame.setTitle("Employee Info");
		GUIPrintEmployees GUI=new GUIPrintEmployees();
		int choice=JOptionPane.showConfirmDialog(null, "Employee info");
		if(choice==JOptionPane.YES_OPTION){
			JOptionPane.showMessageDialog(null, GUISchedule);
		}
	}
	public GUIPrintEmployees(){
		Info=new JLabel();
		Info.setText("Name");
		String name=JOptionPane.showInputDialog(null,"Enter employee name: ","Input",JOptionPane.QUESTION_MESSAGE);
		Info.setText("Job Position");
		String position=JOptionPane.showInputDialog(null, "Enter job information: ", "Input", JOptionPane.QUESTION_MESSAGE);
		Info.setText("Hours");
		String hours=JOptionPane.showInputDialog(null, "Enter numbers of hours worked in a week: ","Input",JOptionPane.QUESTION_MESSAGE);
		int Hours=Integer.parseInt(hours);
		Info.setText("Salary");
		String rate=JOptionPane.showInputDialog(null, "Enter Hourly Pay Rate: ","Input",JOptionPane.QUESTION_MESSAGE);
		double Rate=Double.parseDouble(rate);
		String output=("\nEmployee Name: "+name+"\nEmployees Position"+position+"\nHours Worked: "+hours+"\nPay Rate: $"+rate);
	}
}
/*import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
public class GUIPrintEmployees extends JFrame implements ActionListener {
	    private ArrayList<Employee> list;
	    private GUIPrintEmployees view;
	    private Container content;
	    private JPanel Title;
	    private JRadioButton employee;
	    private JTextField name, title, salary;
	    private JButton create;
	    private JPanel Design;
	    private JLabel header;
	    private ButtonGroup employeetype;
	    public void Employee() {
	        try {
	            Employee New;
	            String name = name.getText();
	            double salary = Double.parseDouble(salary.getText() );
	            String selected = employeetype.getSelection().getActionCommand();
	            if (selected == "employee") {
	                String department = department.getText();
	                New = new Employee(name,salary,department);
	            }
	            list.add(New);
	            Painter.repaint(view);
	            heading.setText("Employee was added.");
	        }
	        catch (Exception ex) {
	            heading.setText("Employee was not added to list. ");
	        }
	    } 
	    public void Button() {
	        create = new JButton("Create employee");
	        create.addActionListener(this);
	        Design.add(create);
	    }
	    public GUIPrintEmployee(ArrayList<Employee> model, EmployeeListView view) {
	        model=model;
	        view=view;
	        GUI();
	        Name();
	        Title();
	        Salary();
	        Button();
	    }
	    public void GUI() {
	        content=getContentPane();
	        content.setLayout(new FlowLayout());
	        setTitle("Add Employees");
	        setSize(500, 500);
	        setVisible(true);
	        setSize(200, 100);
	        Design=new JPanel();
	        Design.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
	        content.add(form);
	    }
	    public void Name() {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	        panel.add(new JLabel("Name:b", JLabel.LEFT));
	        name = new JTextField();
	        name.setColumns(10);
	        panel.add(name);
	        Design.add(panel);
	    }
	    public void Title() {
	        Title=new JPanel();
	        Title.setLayout(new BoxLayout(titlepanel, BoxLayout.X_AXIS));
	        Title.add(new JLabel("Title: ", JLabel.LEFT));
	        title=new JTextField();
	        title.setColumns(10);
	        Title.add(title);
	        Design.add(Title);
	    }
	    public void Salary() {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	        panel.add(new JLabel("Salary: ", JLabel.LEFT));
	        salary = new JTextField();
	        salary.setColumns(10);
	        panel.add(salary);
	        Design.add(panel); 
	    }
	}*/
