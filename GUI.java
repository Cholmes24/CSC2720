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
	        setTitle("Tutorial");
	        setSize(960,960);
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
	        String[] columns = new String[] {
	            "Id", "Name", "Hourly Rate", "Part Time"
	        };
	        Object[][] data = new Object[][] {
	            {1, "John", 40.0, false },
	            {2, "Rambo", 70.0, false },
	            {3, "Zorro", 60.0, true },
	        };
	        JTable table = new JTable(data, columns);
	        this.add(new JScrollPane(table));
	        this.setTitle("Table Example");
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
	        this.pack();
	        this.setVisible(true);
	    }
	public static void main (String[] args){
		GUI gui=new GUI();
		new GUI();GUI t = new GUI();
        t.paint(null);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
	}
}

