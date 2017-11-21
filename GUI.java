import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class GUI extends JFrame implements ActionListener{
	private JFrame frame;
	private JTextField numberField;
	private JLabel validLabel;
	private JButton verifyButton;
	 public GUI(){
	        setTitle("Schedule");
	        setSize(800,800);
	        setVisible(true);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	    }
	 public void paint(Graphics g){
	        g.setColor(Color.DARK_GRAY);
	        g.drawOval(480,480,200,200);
	        g.fillOval(480, 480, 200, 200);
	        g.setColor(Color.DARK_GRAY);
	        g.drawOval(1000,500,200,200);
	        g.fillOval(1000, 500, 200, 200);
	        g.setColor(Color.DARK_GRAY);
	        g.drawOval(850,650,200,200);
	        g.fillOval(850, 650, 200, 200);
	        g.setColor(Color.DARK_GRAY);
	        g.drawOval(240,240,200,200);
	        g.fillOval(240, 240, 200, 200);
	        String[] columns = new String[] {"Id", "Name", "Hourly Rate"};
	        Object[][] data = new Object[][] {{1, "Bob", 38.0, false },{2, "Lisa", 20.0, false },{3, "Johnny", 78.0, true }};
	        JTable table = new JTable(data, columns);
	        this.add(new JScrollPane(table));
	        this.setTitle("Table");
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
	        this.pack();
	        this.setVisible(true);
	    }
	public static void main (String[] args){
		GUI gui=new GUI();
		new GUI();
		GUI Gui = new GUI();
        t.paint(null);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }});
	}
}

