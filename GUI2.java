import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class GUI2 {
	private JFrame Frame;
	private JTextField number;
	private JLabel valid;
	private JButton verify;
	public GUI2(){
		number=new JTextField(16);
		valid=new JLabel("not verified");
		verify=new JButton("Verify Employee Number");
		Frame=new JFrame("Employee number verifier");
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setSize(new Dimension(350,100));
		Frame.setLayout(new FlowLayout());
		Frame.add(number);
		Frame.add(verify);
		Frame.add(valid);
		Frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent Event){
		String word=number.getText();
		if(check(word)){
			valid.setText("This is a valid number!");
		}else{
			valid.setText("This is a invalid number!");
		}
	}
	public boolean check(String word){
		int add=0;
		int i,digit;
		for(i=word.length()-1;i>-1;i--){
			digit=Integer.parseInt(word.substring(i, i+1));
			if(i%2==0){
				(digit*=2);
			}
			add+=((digit/10)+(digit%10));
		}
		return add%10==0 && word.startsWith("4");
	}
	public static void main (String[] args){
		GUI2 gui=new GUI2();							
	}
}
