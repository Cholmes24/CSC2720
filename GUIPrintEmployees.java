import java.awt.*;
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
	}
