package Constructs;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI2 {
	JFrame frame;
	JTextField numberField;
	JLabel validLabel;
	JButton verifyButton;
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
		verifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				String text=numberField.getText();
				if(Confirm(Integer.valueOf(text))){
					validLabel.setText("Valid number!");
				}else{
					validLabel.setText("Invalid number!");
				}
			}
		});
	}
	public boolean Confirm(int text){
		Employee a= new Employee();
		int [] b=a.getAllEID();
		for(int i=0;i<b.length;i++) {
			if(b[i]==text) {
				return true;
			}
		}
		return false;
	}
	public static void main (String[] args){
		GUI2 gui=new GUI2();	
	}
}