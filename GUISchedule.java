import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class GUISchedule extends JFrame{
	JTable table;
	String[] column={"Name","Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	String[][] data={{"John", "11-7","12-5","11-7","12-5","11-7","8-9","8-9"},
			{"John", "8-9","12-5","11-7","8-9","12-5","11-7","8-9"},
			{"John", "11-7","12-5","11-7","12-5","8-9","11-7","12-5"}};
	public GUISchedule(){
		table=new JTable(data,column);
		table.setBounds(100, 100, 300, 300);
		JScrollPane tables=new JScrollPane(table);
		this.add(tables);
		this.setSize(150,300);
		this.setVisible(true);
	}
	public static void main(String[] args){
		new GUISchedule();
		int choice=JOptionPane.showConfirmDialog(null, "Employee info");
		if(choice==JOptionPane.YES_OPTION){
			JOptionPane.showMessageDialog(null, GUIPrintEmployees);
		}
	}
	}
	

