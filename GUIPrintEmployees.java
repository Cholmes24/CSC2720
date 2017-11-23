import java.awt.Label;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.application.*;
public class GUIPrintEmployees extends Application{
	Stage window;
	Scene scene1,scene2;
	Button button;
	public static void main(String[] args){
		launch(args);
	}
	public void start(Stage primaryStage){
		window=primaryStage;
		Label label1=new Label("GUI");
		Button button1=new Button("Click to go to Schedule");
		button1.setOnAction(e->window.setScene(scene2));
		VBox layout1=new VBox(20);
		layout1.getChildren().addAll(label1,button1);
		scene1=new Scene(layout1,200,200);
		Button button2=new Button("Click to go back to Print Employees");
		button2.setOnAction(e->window.setScene(scene1));
		StackPane layout2=new StackPane();
		layout2.getChildren().add(button2);
		scene2=new Scene(layout2,600,300);
		window.setScene(scene1);
		window.setTitle("Title Here");
		window.show();
	}
	public void handle(ActionEvent event){
		if(event.getSource()==button){
			System.out.println("Enter employee name: ");
			String name=JOptionPane.showInputDialog(null,"Enter employee name: ","Input",JOptionPane.QUESTION_MESSAGE);
			System.out.println("Enter job information: ");
			String position=JOptionPane.showInputDialog(null,"Enter employee name: ","Input",JOptionPane.QUESTION_MESSAGE);
			System.out.println("Enter numbers of hours worked in a week: ");
			String hours=JOptionPane.showInputDialog(null, "Enter numbers of hours worked in a week: ","Input",JOptionPane.QUESTION_MESSAGE);
			int Hours=Integer.parseInt(hours);
			System.out.println("Enter hourly pay rate: ");
			String rate=JOptionPane.showInputDialog(null, "Enter Hourly Pay Rate: ","Input",JOptionPane.QUESTION_MESSAGE);
			double Rate=Double.parseDouble(rate);
			String output=("\nEmployee Name: "+name+"\nHours Worked: "+hours+"\nPay Rate: $"+rate);
		}
		
		
	}
}

