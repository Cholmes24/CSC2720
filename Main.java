package Constructs;
import java.util.ArrayList;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class Main extends Application {

	Stage window;
	Scene Master, EmployeeCreator, EmployeeInstantiated, EmployeeRecords, EmployeeSchedule, EmployeePayroll;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;

		Label AddEmployeeLab = new Label("Add an Employee to the records");
		Button AddEmployeeBut = new Button("Click to add Employee");
		AddEmployeeBut.setOnAction(e -> window.setScene(EmployeeCreator));
		Label RetrieveLab = new Label("Employee Records");
		Button RetrieveBut = new Button("Click to Retrieve Employee information");
		RetrieveBut.setOnAction(e -> window.setScene(EmployeeRecords));
		Label ScheduleLab = new Label("Employee Schedule");
		Button ScheduleBut = new Button("Click to see Employee Schedule");
		RetrieveBut.setOnAction(e -> window.setScene(EmployeeSchedule));
		Label PayrollLab = new Label("Employee Payroll");
		Button PayrollBut = new Button("Click to to see payroll");
		PayrollBut.setOnAction(e -> window.setScene(EmployeePayroll));

		//Layout 1 - children laid out in vertical column
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(AddEmployeeLab,AddEmployeeBut,RetrieveLab,RetrieveBut,ScheduleLab,ScheduleBut,PayrollLab,PayrollBut);
		Master = new Scene(layout1, 400, 400);

		Button returnButton = new Button("Return to Main Menu");
		returnButton.setOnAction(e -> window.setScene(Master));
		//Button 2
		Label LastNameText = new Label("Last Name:");
		TextField LastNameField= new TextField();
		Label FirstNameText = new Label("First Name:");
		TextField FirstNameField= new TextField();
		Label TitleText = new Label("JobTitle:");
		TextField TitleField= new TextField();
		Label SSNText = new Label("Social Security Number:");
		TextField SSNField= new TextField();
		Label AddressText = new Label("Address:");
		TextField AddressField= new TextField();
		Label PhoneText = new Label("Phone Number:");
		TextField PhoneField= new TextField();
		Button Submit= new Button("Submit");
		Submit.setOnAction(e -> {
			if (SSNValidityCheck(SSNField)&&PhoneValidityCheck(PhoneField)) {
				String lastName = LastNameField.getText();
				String firstName = FirstNameField.getText(); 
				String title = TitleField.getText();
				int ssn = Integer.valueOf(SSNField.getText());
				String address = AddressField.getText();
				int phone=0;
				if(!PhoneField.getText().isEmpty()) {
					phone = Integer.valueOf(PhoneField.getText());
				}
				new Employee(lastName,firstName,title,ssn,address,phone);
				window.setScene(EmployeeInstantiated);
			}
		});

		//Layout 2
		VBox layout2 = new VBox();
		layout2.getChildren().addAll(LastNameText,LastNameField,FirstNameText,FirstNameField,TitleText,TitleField,SSNText,SSNField,AddressText,AddressField,PhoneText,PhoneField,Submit,returnButton);
		EmployeeCreator = new Scene(layout2, 600, 400);
		VBox layout3 = new VBox();
		layout3.getChildren().add(returnButton);
		EmployeeInstantiated = new Scene(layout3, 600, 300);

		//Display scene 1 at first
		window.setScene(Master);
		window.setTitle("Manager Application");
		window.show();
	}
	public boolean SSNValidityCheck(TextField SSNField) {
		System.out.println(SSNField.getText());
		String text=SSNField.getText();
		for(int i=0; i <text.length();i++)
			if((int) text.charAt(i)<48||(int)text.charAt(i)>58) {
				AlertBox.display("Error","INVALID!!! That Character Is Not A Number");
				return false;
			}
		if(text.length()!=9) {
			AlertBox.display("Error","INVALID!!! A Social Security Number Needs 9 Numbers");
			return false;
		}
		Employee a= new Employee();
		int [] b=a.getAllEID();
		ArrayList <Employee> c= new ArrayList <Employee>();
		for(int i=0;i<b.length;i++) {
			c.add(new Employee(b[i]));
			if(Integer.valueOf(text)==c.get(i).getSSN()) {
				AlertBox.display("Error","INVALID!!! Social Security Is Not Unique");
				return false;
			}
		}
		return true;
	}
	public boolean PhoneValidityCheck(TextField number) {
		System.out.println(number.getText());
		String text=number.getText();
		if(!number.getText().isEmpty()) {
			for(int i=0; i <text.length();i++)
				if((int) text.charAt(i)<48||(int)text.charAt(i)>58) {
					AlertBox.display("Error","INVALID!!! That Character Is Not A Number");
					return false;
				}
			if(text.length()!=10) {
				AlertBox.display("Error","INVALID!!! A Phone Number Needs 10-11 Numbers");
				return false;
			}
			Employee a= new Employee();
			int [] b=a.getAllEID();
			ArrayList <Employee> c= new ArrayList <Employee>();
			for(int i=0;i<b.length;i++) {
				c.add(new Employee(b[i]));
				if(c.get(i).getPhone()!=0)
					if(Integer.valueOf(text)==c.get(i).getPhone()) {
						AlertBox.display("Error","INVALID!!! Phone Number Is Not Unique");
						return false;
					}
			}
		}
		return true;
	}


}