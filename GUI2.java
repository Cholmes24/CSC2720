import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI2 {
	private JFrame frame;
	private JTextField numberField;
	private JLabel validLabel;
	private JButton verifyButton;
	public GUI2(){
		numberField=new JTextField(16);
		validLabel=new JLabel("not verified");
		verifyButton=new JButton("Verify Employee Number");
		frame=new JFrame("Employee number verifier");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(350,100));
		frame.setLayout(new FlowLayout());
		frame.add(numberField);
		frame.add(verifyButton);
		frame.add(validLabel);
		frame.setVisible(true);
	}
	public boolean Confirm(String text){
		int sum=0;
		for(int i=text.length()-1;i>=0;i--){
			int digit=Integer.parseInt(text.substring(i, i+1));
			if(i%2==0){
				digit*=2;
			}
			sum+=(digit/10)+(digit%10);
		}
		return sum%10==0&&text.startsWith("4");
	}
	public void actionPerformed(ActionEvent event){
		String text=numberField.getText();
		if(Confirm(text)){
			validLabel.setText("Valid number!");
		}else{
			validLabel.setText("Invalid number!");
		}
	}
	public static void main (String[] args){
		GUI2 gui=new GUI2();							
	}
}
