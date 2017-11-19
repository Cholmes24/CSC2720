import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
public class GUIPrintEmployees extends JFrame implements ActionListener {
	    private ArrayList<Employee> model;
	    private GUIPrintEmployees parentview;
	    private Container content;
	    private JPanel form;
	    private JLabel heading;
	    private ButtonGroup employeetype;
	    private JPanel titlepanel, departmentpanel;
	    private JRadioButton worker;
	    private JRadioButton manager;
	    private JTextField name, title, salary,  department;
	    private JButton create;
	    public GUIPrintEmployee(ArrayList<Employee> model, EmployeeListView parentview) {
	        model = model;
	        parentview = parentview;
	        GUI();
	        Name();
	        Title();
	        Salary();
	        Button();
	    }
	    public void GUI() {
	        content = getContentPane();
	        content.setLayout(new FlowLayout());
	        setTitle("Add Employees");
	        setSize(500, 500);
	        setVisible(true);
	        setSize(200, 100);
	        form = new JPanel();
	        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
	        content.add(form);
	    }
	    public void Name() {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	        panel.add(new JLabel("Name:", JLabel.LEFT));
	        name = new JTextField();
	        name.setColumns(10);
	        panel.add(name);
	        form.add(panel);
	    }
	    public void Title() {
	        titlepanel = new JPanel();
	        titlepanel.setLayout(new BoxLayout(titlepanel, BoxLayout.X_AXIS));
	        titlepanel.add(new JLabel("Title:", JLabel.LEFT));
	        title = new JTextField();
	        title.setColumns(15);
	        titlepanel.add(title);
	        form.add(titlepanel);
	    }
	    public void Salary() {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	        panel.add(new JLabel("Salary:", JLabel.LEFT));
	        salary = new JTextField();
	        salary.setColumns(15);
	        panel.add(salary);
	        form.add(panel); 
	    }
	    public void Button() {
	        create = new JButton("Create employee");
	        create.addActionListener(this);
	        form.add(create);
	    }
	    
public void Employee() {
	        try {
	            Employee New;
	            String name = name.getText();
	            double salary = Double.parseDouble(salary.getText() );
	            String selected = employeetype.getSelection().getActionCommand();
	            if (selected == "worker") {
	                String department = department.getText();
	                New = new Worker(name,  salary, department);
	            }
	            model.add(New);
	            Painter.repaint(parentview);
	            heading.setText("Employee was added.");
	        }
	        catch (Exception ex) {
	            heading.setText("Employee was not added to list. ");
	        }
	    }
	}
