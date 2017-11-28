package classes;
import java.util.Arrays;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ChoiceBox;

public class ManagerApplicationLauncher extends Application {

	Stage window;
	Scene Master, EmployeeCreator, EmployeeInstantiated, JobCreator, JobInstantiated, EmployeeRecords, EmployeeSchedule, EmployeeContacts,ShiftCreator,ContactCreator,JobCreatorPoPup,JobInstantiatedPopUp,ShiftInstantiated;
	Label firstNameVaid,lastNameValid, SSNValid=new Label(""), phoneValid,LastNameValid=new Label(""),FirstNameValid=new Label(""),PhoneValid=new Label(""), FirstNameValidES=new Label(""),LastNameValidES=new Label(""),EIDESValid=new Label("");
	TextField JobTitleField,PayrollField,LastNameField, FirstNameField, TitleField, SSNField, AddressField, PhoneField,JobTitleFieldPopUp,PayrollFieldPopUp,LastNameFieldES,FirstNameFieldES,TitleFieldES,EIDESField;
	String clockinh="";
	String clockinm="";
	String clockins="";
	String clockouth="";
	String clockoutm="";
	String clockouts="";
	String datey="";
	String datem="";
	String dated="";
	TableView<Employee> EmployeeRecord;
	TableView<EmployeeShifts>EmployeeInfo;
	TableView<EmergencyContact>Contacts;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		//Master-Labels and Buttons
		Label AddJobLab = new Label("Add a Job to the records");
		Button AddJobBut = new Button("Click to add Job");
		AddJobBut.setOnAction(e -> {
			window.setScene(JobCreator);
			window.setTitle("Job Creator");
		});
		Label AddEmployeeLab = new Label("Add an Employee to the records");
		Button AddEmployeeBut = new Button("Click to add Employee");
		AddEmployeeBut.setOnAction(e -> {
			if(doJobsExist()) {
				window.setScene(EmployeeCreator);
				window.setTitle("Employee Creator");
			} else {
				AlertBox.display("Error","Jobs Must Be Created First");
			}
		});
		Label RetrieveLab = new Label("Employee Records");
		Button RetrieveBut = new Button("Click to Retrieve Employee information");
		RetrieveBut.setOnAction(e -> {
			if(doJobsExist()) {
				EmployeeRecord.setItems(getInfo());
				window.setScene(EmployeeRecords);
				window.setTitle("Employee Information");
			} else {
				AlertBox.display("Error","Jobs Must Be Created First");
			}
		});
		Label ScheduleLab = new Label("Employee Schedule");
		Button ScheduleBut = new Button("Click to see Employee Schedule");
		ScheduleBut.setOnAction(e -> {
			if(doJobsExist()) {
				EmployeeInfo.setItems(getSchedule());
				window.setScene(EmployeeSchedule);
				window.setTitle("Employee Schedule");
			} else {
				AlertBox.display("Error","Jobs Must Be Created First");
			}
		});
		Label EmployeeContactLab = new Label("Employee Contacts");
		Button EmployeeContactBut = new Button("Just In Case");
		EmployeeContactBut.setOnAction(e ->{ 
			if(doJobsExist()) {
				Contacts.setItems(getEmergencyContacts());
				window.setScene(EmployeeContacts);
				window.setTitle("Emergency Contacts");
			} else {
				AlertBox.display("Error","Jobs Must Be Created First");
			}
		});
		Label AddEmployeeShiftLab = new Label("Add Employee Shifts");
		Button AddEmployeeShiftBut = new Button("Add an Employee Shift");
		AddEmployeeShiftBut.setOnAction(e ->{ 
			if(doJobsExist()) {
				window.setScene(ShiftCreator);
				window.setTitle("Add Employee Shifts");
			} else {
				AlertBox.display("Error","Jobs Must Be Created First");
			}
		});
		Label AddEmployeeContactLab = new Label("Add Employee Contacts");
		Button AddEmployeeContactBut = new Button("Add an Employee Contact");
		AddEmployeeContactBut.setOnAction(e ->{ 
			if(doJobsExist()) {
				window.setScene(ContactCreator);
				window.setTitle("Add Employee Contacts");
			} else {
				AlertBox.display("Error","Jobs Must Be Created First");
			}
		});

		//Master-LayOut
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(AddJobLab,AddJobBut,AddEmployeeLab,AddEmployeeBut,RetrieveLab,RetrieveBut,AddEmployeeShiftLab,AddEmployeeShiftBut,ScheduleLab,ScheduleBut,AddEmployeeContactLab,AddEmployeeContactBut,EmployeeContactLab,EmployeeContactBut);
		Master = new Scene(layout1, 400, 700);

		Button ReturnButton = new Button("Return to Main Menu");
		ReturnButton.setOnAction(e -> {
			window.setScene(Master);
			window.setTitle("Manager Application");
		}
				); 
		Label JobTitleLab = new Label("Job Title");
		JobTitleField = new TextField();
		Label PayrollLab = new Label("Wage");
		PayrollField = new TextField();
		Button Submit= new Button("Submit");
		Submit.setOnAction(e -> {
			if (notNull(JobTitleField,PayrollField)) {
				if(!doesThatJobExist(JobTitleField)) {
					String job = JobTitleField.getText();
					double payroll = Double.valueOf(PayrollField.getText()); 
					new Job(job, payroll);
					JobTitleField.clear();
					PayrollField.clear();
					window.setScene(JobInstantiated);
				}else {
					AlertBox.display("Error","INVALID!!! That Job Already Exist.");
				}
			}else {
				AlertBox.display("Error","INVALID!!! There Are Fields That Can't Be Empty");
			}
		});
		VBox layout = new VBox();
		layout.getChildren().addAll(JobTitleLab,JobTitleField,PayrollLab,PayrollField,Submit,ReturnButton);
		JobCreator= new Scene(layout,400,400);


		Button ReturnButton1 = new Button("Return to Main Menu");
		ReturnButton1.setOnAction(e -> {
			window.setScene(Master);
			window.setTitle("Manager Application");
		}); 
		Label JobCreated= new Label("The Job Information Has Been Stored");
		VBox layout2 =new VBox();
		layout2.getChildren().addAll(JobCreated,ReturnButton1);
		JobInstantiated = new Scene(layout2,400,400);

		//EmployeeCreator Buttons, Labels, and Textfields
		Button ReturnButton2 = new Button("Return to Main Menu");
		ReturnButton2.setOnAction(e -> {
			window.setScene(Master);
			window.setTitle("Manager Application");
		});
		Label RequiredFields = new Label("* Means Required");
		Label LastNameText = new Label("Last Name*:");
		LastNameField= new TextField();
		LastNameField.setOnKeyReleased(e -> {
			for(int i=0; i <LastNameField.getText().length();i++)
				if(!(((int) LastNameField.getText().charAt(i)>64 && (int)LastNameField.getText().charAt(i)<91) || ((int) LastNameField.getText().charAt(i)>96 && (int)LastNameField.getText().charAt(i)<123))) {
					if(!LastNameField.getText().equals(""))
						LastNameValid.setText("Must only contain letters ");
				}
				else
					LastNameValid.setText("seems good!");
		});
		Label FirstNameText = new Label("First Name*:");
		FirstNameField= new TextField();
		FirstNameField.setOnKeyReleased(e -> {
			for(int i=0; i <FirstNameField.getText().length();i++)
				if(!(((int) FirstNameField.getText().charAt(i)>64 && (int)FirstNameField.getText().charAt(i)<91) || ((int) FirstNameField.getText().charAt(i)>96 && (int)FirstNameField.getText().charAt(i)<123)) ) {
					if(!FirstNameField.getText().equals(""))
						FirstNameValid.setText("Must only contain letters ");
				}
				else
					FirstNameValid.setText("");
		});
		Label TitleText = new Label("Job Title*:");
		TitleField= new TextField();
		Label SSNText = new Label("Social Security Number*:");
		SSNField= new TextField();
		SSNField.setOnKeyReleased(e -> {

			if(SSNField.getText().length() != 9) {
				SSNValid.setText("Must Be 9 Digits");
			}
			else {
				SSNValid.setText("");
			}
			for(int i=0; i <SSNField.getText().length();i++) {
				if((int) SSNField.getText().charAt(i)<48||(int)SSNField.getText().charAt(i)>58) {
					SSNValid.setText("Must only contain numbers");

				}
			}
		});
		Label AddressText = new Label("Address:");
		AddressField= new TextField();
		Label PhoneText = new Label("Phone Number:");
		PhoneField= new TextField();
		PhoneField.setOnKeyReleased(e -> {

			if(PhoneField.getText().length()<9||PhoneField.getText().length()>12) {
				PhoneValid.setText("Must Be 10 Digits");
			}
			else {
				PhoneValid.setText("");
			}
			String temp = PhoneValid.getText();

			for(int i=0; i <PhoneField.getText().length();i++) {
				if((int) PhoneField.getText().charAt(i)<48||(int)PhoneField.getText().charAt(i)>58) {
					PhoneValid.setText("Must only contain numbers");
				}
				else
					PhoneValid.setText(temp);
			}

		});
		Button Submit1= new Button("Submit");
		Submit1.setOnAction(e -> {
			if (SSNValidityCheck(SSNField)&&PhoneValidityCheck(PhoneField)&&notNull(LastNameField,FirstNameField,TitleField,SSNField)) {
				if(doesThatJobExist(TitleField)) {
					String lastName = LastNameField.getText();
					String firstName = FirstNameField.getText(); 
					String title = TitleField.getText().toUpperCase();
					int ssn = Integer.valueOf(SSNField.getText());
					String address = AddressField.getText();
					String phone=null;
					if(!PhoneField.getText().isEmpty()) {
						phone = PhoneField.getText();
					}
					new Employee(lastName,firstName,title,ssn,address,phone);
					LastNameField.clear();
					FirstNameField.clear();
					TitleField.clear();
					SSNField.clear();
					AddressField.clear();
					PhoneField.clear();
					window.setScene(EmployeeInstantiated);
				}else {
					createJob("Error","INVALID!!! That Job Does Not Exist.\n Would You Like To Create This Job:\n"+TitleField.getText(),TitleField.getText());
				}
			}else {
				AlertBox.display("Error","INVALID!!! There Are Fields That Can't Be Empty");
			}
		});

		//EmployeeCreator LayOut
		VBox layout3 = new VBox();
		layout3.getChildren().addAll(LastNameText,LastNameField,LastNameValid,FirstNameText,FirstNameField,FirstNameValid,TitleText,TitleField,SSNText,SSNField,SSNValid,AddressText,AddressField,PhoneText,PhoneField,RequiredFields,Submit1,ReturnButton2);
		EmployeeCreator = new Scene(layout3, 400, 600);
		//EmployeeInstantiated Buttons And Labels 
		Button ReturnButton3 = new Button("Return to Main Menu");
		ReturnButton3.setOnAction(e ->{
			window.setScene(Master);
			window.setTitle("Manager Application");
		});
		Label Success= new Label("The Employee Information Has Been Stored");
		//EmployeeInstantiated LayOut
		VBox layout4 = new VBox();
		layout4.getChildren().addAll(ReturnButton3,Success);
		EmployeeInstantiated = new Scene(layout4, 600, 300);
		//Neel use this format for generating tables
		Button ReturnButton4 = new Button("Return to Main Menu");
		ReturnButton4.setOnAction(e -> {
			window.setScene(Master);
			window.setTitle("Manager Application");
		});
		Button ReturnButtonES = new Button("Return to Main Menu");
		ReturnButtonES.setOnAction(e -> {
			window.setScene(Master);
			window.setTitle("Manager Application");
		});
		Label RequiredFieldsES = new Label("* Means Required");
		Label EnterTime= new Label("Enter The Time The Shift Starts:");
		Label EnterTime1= new Label("Enter The Time The Shift Ends:");
		Label SEP=new Label(":");
		Label SEP1=new Label(":");
		Label SEP2=new Label(":");
		Label SEP3=new Label(":");
		Label SEP4=new Label("/");
		Label SEP5=new Label("/");
		Label LastNameTextES = new Label("Last Name:");
		Label FirstNameTextES = new Label("First Name:");
		
		ChoiceBox<Integer> ShiftHES = new ChoiceBox<>();
		for(int i=0;i<24;i++) {
			ShiftHES.getItems().add(i);
		}
		ShiftHES.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->{
			clockinh+=newValue;
		});
		ChoiceBox<Integer> ShiftMES = new ChoiceBox<>();
		for(int i=0;i<59;i++) {
			ShiftMES.getItems().add(i);
		}
		ShiftMES.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->{
			clockinm+=":"+newValue;
		});
		ChoiceBox<Integer> ShiftSES = new ChoiceBox<>();
		for(int i=0;i<59;i++) {
			ShiftSES.getItems().add(i);
		}
		ShiftSES.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->{
			clockins+=":"+newValue;
		});
		ChoiceBox<Integer> ShiftHOES = new ChoiceBox<>();
		for(int i=0;i<24;i++) {
			ShiftHOES.getItems().add(i);
		}
		ShiftHOES.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->{
			clockouth+=newValue;
		});
		ChoiceBox<Integer> ShiftMOES = new ChoiceBox<>();
		for(int i=0;i<59;i++) {
			ShiftMOES.getItems().add(i);
		}
		ShiftMOES.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->{
			clockoutm+=":"+newValue;
		});
		ChoiceBox<Integer> ShiftSOES = new ChoiceBox<>();
		for(int i=0;i<59;i++) {
			ShiftSOES.getItems().add(i);
		}
		ShiftSOES.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->{
			clockouts+=":"+newValue;
		});
		Label ShiftLabES= new Label("Shift/Date");
		ChoiceBox<Integer> year = new ChoiceBox<>();
		for(int i=new Date(0).getYear();i<new Date(0).getYear()+4;i++) {
			year.getItems().add(i);
		}
		year.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->{
			datey+=newValue+"";
		});
		ChoiceBox<Integer> month = new ChoiceBox<>();
		for(int i=1;i<13;i++) {
			month.getItems().add(i);
		}
		ChoiceBox<Integer> day = new ChoiceBox<>();
		day.getItems().add(0);
		month.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->{
			int d=new Date(newValue).getDay();
			for(int i=1;i<d;i++)
				day.getItems().add(i);
			datem+="-"+newValue;
		});
		day.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->{
			dated+="-"+newValue;
		});
		Label EIDESLab= new Label("Enter The Employee ID");
		EIDESField= new TextField();
		EIDESField.setOnKeyReleased(e -> {
			if(!EIDESField.getText().equals("")&&!EIDESField.getText().equals(null)) {
				if(containsOnlyNumbers(EIDESField)) {
					if(doesThatEIDExist(EIDESField)) {
						EIDESValid.setText("Valid!");
						LastNameTextES.setText("Last Name: "+new Employee(Integer.valueOf(EIDESField.getText())).getLastName());
						FirstNameTextES.setText("First Name: "+new Employee(Integer.valueOf(EIDESField.getText())).getFirstName());
					}else {
						EIDESValid.setText("That Employee ID Does Not Exist!");
						LastNameTextES.setText("Last Name: ");
						FirstNameTextES.setText("First Name: ");
					}
				}else {
					EIDESValid.setText("Must only contain numbers");
					LastNameTextES.setText("Last Name: ");
					FirstNameTextES.setText("First Name: ");
				}
			}else {
				EIDESValid.setText("");
				LastNameTextES.setText("Last Name: ");
				FirstNameTextES.setText("First Name: ");

			}
		});
		Button SubmitES= new Button("Submit");
		SubmitES.setOnAction(e -> {
			if(!EIDESField.getText().equals("")&&!EIDESField.getText().equals(null)) {
				if(containsOnlyNumbers(EIDESField)) {
					if(doesThatEIDExist(EIDESField)) {
						new EmployeeShifts(datey+datem+dated,clockinh+clockinm+clockins,clockouth+clockoutm+clockouts,Integer.valueOf(EIDESField.getText()),new Employee(Integer.valueOf(EIDESField.getText())).getLastName(),new Employee(Integer.valueOf(EIDESField.getText())).getFirstName());
						window.setScene(ShiftInstantiated);
						window.setTitle("Shift Creator");
					}else {
						EIDESValid.setText("That Employee ID Does Not Exist!");
						LastNameTextES.setText("Last Name: ");
						FirstNameTextES.setText("First Name: ");
					}
				}else {
					EIDESValid.setText("Must only contain numbers");
					LastNameTextES.setText("Last Name: ");
					FirstNameTextES.setText("First Name: ");
				}
			}else {
				EIDESValid.setText("");
				LastNameTextES.setText("Last Name: ");
				FirstNameTextES.setText("First Name: ");

			}
			
		});
		Label ShiftCreated= new Label("The Shift information has been stored");
		Button ReturnButtonESI = new Button("Return to Main Menu");
		ReturnButtonESI.setOnAction(e -> {
			window.setScene(Master);
			window.setTitle("Manager Application");
		});
		VBox layoutESI = new VBox();
		layoutESI.getChildren().addAll(ShiftCreated,ReturnButtonESI);
		ShiftInstantiated= new Scene(layoutESI, 600, 300);

		//EmployeeCreator LayOut
		HBox layoutTimeInES = new HBox();
		HBox layoutTimeOutES = new HBox();
		HBox layoutDate = new HBox();
		layoutTimeInES.getChildren().addAll(EnterTime,ShiftHES,SEP,ShiftMES,SEP1,ShiftSES);
		layoutTimeOutES.getChildren().addAll(EnterTime1,ShiftHOES,SEP2,ShiftMOES,SEP3,ShiftSOES);
		layoutDate.getChildren().addAll(day,SEP4,month,SEP5,year);
		VBox layoutES=new VBox();
		layoutES.getChildren().addAll(RequiredFieldsES,layoutTimeInES,layoutTimeOutES,ShiftLabES,layoutDate,EIDESLab,EIDESField,EIDESValid,LastNameTextES,FirstNameTextES,SubmitES,ReturnButtonES);
		layoutES.setSpacing(10);
		ShiftCreator = new Scene(layoutES, 400, 600);

		TableColumn<Employee, Integer> EIDColumn = new TableColumn<>("Employee ID");
		EIDColumn.setMinWidth(200);
		EIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		TableColumn<Employee, String> lastNameColumn = new TableColumn<>("Last Name");
		lastNameColumn.setMinWidth(200);
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		TableColumn<Employee, String> firstNameColumn = new TableColumn<>("First Name");
		firstNameColumn.setMinWidth(200);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		TableColumn<Employee, String> titleColumn = new TableColumn<>("Job Title");
		titleColumn.setMinWidth(200);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		TableColumn<Employee, Integer> SSNColumn = new TableColumn<>("SSN");
		SSNColumn.setMinWidth(200);
		SSNColumn.setCellValueFactory(new PropertyValueFactory<>("SSN"));
		TableColumn<Employee, String> addressColumn = new TableColumn<>("Address");
		addressColumn.setMinWidth(200);
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		TableColumn<Employee, String> phoneColumn = new TableColumn<>("Phone");
		phoneColumn.setMinWidth(200);
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		EmployeeRecord = new TableView<>();
		EmployeeRecord.setItems(getInfo());
		EmployeeRecord.getColumns().addAll(EIDColumn,lastNameColumn, firstNameColumn, titleColumn, SSNColumn, addressColumn, phoneColumn );

		VBox layout5 = new VBox();
		layout5.getChildren().addAll(EmployeeRecord,ReturnButton4);
		EmployeeRecords = new Scene(layout5);

		Button ReturnButton5 = new Button("Return to Main Menu");
		ReturnButton5.setOnAction(e -> {
			window.setScene(Master);
			window.setTitle("Manager Application");
		});

		TableColumn<EmployeeShifts, String> ESLastNameColumn = new TableColumn<>("Last Name");
		ESLastNameColumn.setMinWidth(100);
		ESLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		TableColumn<EmployeeShifts, String> ESFirstNameColumn = new TableColumn<>("First Name");
		ESFirstNameColumn.setMinWidth(100);
		ESFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		TableColumn<EmployeeShifts, Integer> DayColumn = new TableColumn<>("Day of the week");
		DayColumn.setMinWidth(150);
		DayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
		TableColumn<EmployeeShifts, String> timeColumn = new TableColumn<>("Time Worked");
		timeColumn.setMinWidth(125);
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("workedTime"));
		TableColumn<EmployeeShifts, String> shiftColumn = new TableColumn<>("Shift/Date");
		shiftColumn.setMinWidth(100);
		shiftColumn.setCellValueFactory(new PropertyValueFactory<>("shift"));
		TableColumn<EmployeeShifts, Double> clockInColumn = new TableColumn<>("Clock In");
		clockInColumn.setMinWidth(100);
		clockInColumn.setCellValueFactory(new PropertyValueFactory<>("clockIn"));
		TableColumn<EmployeeShifts, String> clockOutColumn = new TableColumn<>("Clock Out");
		clockOutColumn.setMinWidth(100);
		clockOutColumn.setCellValueFactory(new PropertyValueFactory<>("clockOut"));
		TableColumn<EmployeeShifts, Integer> ESEIDColumn = new TableColumn<>("EID");
		ESEIDColumn.setMinWidth(100);
		ESEIDColumn.setCellValueFactory(new PropertyValueFactory<>("EID"));
		TableColumn<EmployeeShifts, Double> earningsColumn = new TableColumn<>("Wage");
		earningsColumn.setMinWidth(150);
		earningsColumn.setCellValueFactory(new PropertyValueFactory<>("salaryEarnings"));
		TableColumn<EmployeeShifts, Double> totalEarningsColumn = new TableColumn<>("Total Earnings");
		totalEarningsColumn.setMinWidth(150);
		totalEarningsColumn.setCellValueFactory(new PropertyValueFactory<>("totalEarnings"));
		EmployeeInfo = new TableView<>();
		EmployeeInfo.setItems(getSchedule());
		EmployeeInfo.getColumns().addAll(ESLastNameColumn,ESFirstNameColumn,DayColumn,timeColumn,shiftColumn,clockInColumn,clockOutColumn,ESEIDColumn,earningsColumn,totalEarningsColumn);
		VBox layout6 = new VBox();
		layout6.getChildren().addAll(EmployeeInfo,ReturnButton5);
		EmployeeSchedule = new Scene(layout6);

		Button ReturnButton6 = new Button("Return to Main Menu");
		ReturnButton6.setOnAction(e ->{
			window.setScene(Master);
			window.setTitle("Manager Application");
		});

		TableColumn<EmergencyContact, String> contactIDColumn = new TableColumn<>("Contact ID");
		contactIDColumn.setMinWidth(200);
		contactIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		TableColumn<EmergencyContact, String> ECNameColumn = new TableColumn<>("Name");
		ECNameColumn.setMinWidth(100);
		ECNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<EmergencyContact, String> ECAddressColumn = new TableColumn<>("Address");
		ECAddressColumn.setMinWidth(100);
		ECAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		TableColumn<EmergencyContact, String> PhoneNumberColumn = new TableColumn<>("Phone Number");
		PhoneNumberColumn.setMinWidth(150);
		PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		TableColumn<EmergencyContact, Integer> ECEIDColumn = new TableColumn<>("Employee ID");
		ECEIDColumn.setMinWidth(125);
		ECEIDColumn.setCellValueFactory(new PropertyValueFactory<>("EID"));

		Contacts = new TableView<>();
		Contacts.setItems(getEmergencyContacts());
		Contacts.getColumns().addAll(contactIDColumn,ECNameColumn,ECAddressColumn,PhoneNumberColumn,ECEIDColumn);
		VBox layout7 = new VBox();
		layout7.getChildren().addAll(Contacts,ReturnButton6);
		EmployeeContacts = new Scene(layout7);
		//Neel Stop here
		//Display scene 1 at first
		window.setScene(Master);
		window.setTitle("Manager Application");
		window.show();
	}

	public ObservableList<Employee> getInfo(){
		ObservableList<Employee> EmployeeInfo = FXCollections.observableArrayList(Arrays.asList(new Employee().getAllEID()));
		return EmployeeInfo;
	}
	public ObservableList<EmployeeShifts> getSchedule(){
		ObservableList<EmployeeShifts> EmployeeSched = FXCollections.observableArrayList(Arrays.asList(new EmployeeShifts().getAllShifts()));
		return EmployeeSched;
	}
	public ObservableList<EmergencyContact> getEmergencyContacts(){
		ObservableList<EmergencyContact> EmployeeContact = FXCollections.observableArrayList(Arrays.asList(new EmergencyContact().getAllContacts()));
		return EmployeeContact;
	}
	public boolean doJobsExist() {
		return new Job().getAllJobs().length>0;
	}
	public boolean doesThatJobExist(TextField j) {
		Job [] a=new Job().getAllJobs();
		for(int i=0;i<a.length;i++) {
			if(a[i].getJob().equalsIgnoreCase((j.getText()))) {
				return true;
			}
		}
		return false;
	}
	public boolean doesThatEIDExist(TextField EID) {
		Employee [] a =new Employee().getAllEID();
		for(int i=0;i<a.length;i++) {
			if(a[i].getID()==Integer.valueOf(EID.getText())) {
				return true;
			}
		}
		return false;
	}
	public boolean containsOnlyNumbers(TextField a) {
		for(int i=0; i <a.getText().length();i++) {
			if((int) a.getText().charAt(i)<48||(int)a.getText().charAt(i)>58) {
				return false;
			}
		}
		return true;
	}
	public boolean notNull(TextField job, TextField payroll) {
		return !job.getText().isEmpty()&&!payroll.getText().isEmpty();
	}
	public boolean notNull(TextField ln, TextField fn, TextField t, TextField ssn) {
		return !ln.getText().isEmpty()&&!fn.getText().isEmpty()&&!t.getText().isEmpty()&&!ssn.getText().isEmpty();
	}
	public boolean SSNValidityCheck(TextField SSNField) {
		String text=SSNField.getText();
		for(int i=0; i <text.length();i++)
			if((int) text.charAt(i)<48||(int)text.charAt(i)>58) {
				AlertBox.display("Error","INVALID!!! There is a Character Is Not A Number");
				return false;
			}
		if(text.length()!=9) {
			AlertBox.display("Error","INVALID!!! A Social Security Number Needs 9 Numbers");
			return false;
		}
		Employee [] a = new Employee().getAllEID();
		for(int i=0;i<a.length;i++) {
			if(Integer.valueOf(text)==a[i].getSSN()) {
				AlertBox.display("Error","INVALID!!! Social Security Is Not Unique");
				return false;
			}
		}
		return true;
	}
	public boolean PhoneValidityCheck(TextField PhoneField) {
		String text=PhoneField.getText();
		if(!text.isEmpty()) {
			for(int i=0; i <text.length();i++)
				if((int) text.charAt(i)<48||(int)text.charAt(i)>58) {
					AlertBox.display("Error","INVALID!!! That Character Is Not A Number");
					return false;
				}
			if(text.length()<9||text.length()>12) {
				AlertBox.display("Error","INVALID!!! A Phone Number Needs 9-12 Numbers");
				return false;
			}
			Employee [] a = new Employee().getAllEID();
			for(int i=0;i<a.length;i++) {
				if(a[i].getPhone()!=null)
					if(text.equals(a[i].getPhone())) {
						AlertBox.display("Error","INVALID!!! Phone Number Is Not Unique");
						return false;
					}
			}
		}
		return true;
	}
	public void createJob(String title, String message, String jobTitle) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(400);

		Button ReturnButtonPopUp = new Button("Close");
		ReturnButtonPopUp.setOnAction(e -> window.close()); 
		Label JobTitleLabPopUp = new Label("Job Title");
		JobTitleFieldPopUp = new TextField();
		Label PayrollLabPopUp = new Label("Wage");
		PayrollFieldPopUp = new TextField();
		Button SubmitPopUp= new Button("Submit");
		SubmitPopUp.setOnAction(e -> {
			if (notNull(JobTitleFieldPopUp,PayrollFieldPopUp)) {
				if(!doesThatJobExist(JobTitleFieldPopUp)) {
					String job = JobTitleFieldPopUp.getText();
					double payroll = Double.valueOf(PayrollFieldPopUp.getText()); 
					new Job(job, payroll);
					JobTitleFieldPopUp.clear();
					PayrollFieldPopUp.clear();
					window.setScene(JobInstantiatedPopUp);
				}else {
					AlertBox.display("Error","INVALID!!! That Job Already Exist.");
				}
			}else {
				AlertBox.display("Error","INVALID!!! There Are Fields That Can't Be Empty");
			}
		});
		VBox layout = new VBox();
		layout.getChildren().addAll(JobTitleLabPopUp,JobTitleFieldPopUp,PayrollLabPopUp,PayrollFieldPopUp,SubmitPopUp,ReturnButtonPopUp);
		JobCreatorPoPup= new Scene(layout,400,400);

		Button ReturnButton1PopUp = new Button("Close");
		ReturnButton1PopUp.setOnAction(e -> window.close()); 
		Label JobCreatedPopUp= new Label("The Job Information Has Been Stored");
		VBox layout2 =new VBox();
		layout2.getChildren().addAll(JobCreatedPopUp,ReturnButton1PopUp);
		JobInstantiatedPopUp = new Scene(layout2,400,400);

		Label label = new Label();
		label.setText(message);
		Button createButton = new Button("Yes");
		createButton.setOnAction(e -> {
			JobTitleFieldPopUp.setText(jobTitle);
			window.setScene(JobCreatorPoPup);
			window.setTitle("Job Creator");
		});

		Button closeButton = new Button("No");
		closeButton.setOnAction(e -> window.close());

		VBox layout3 = new VBox(10);
		layout3.getChildren().addAll(label, createButton, closeButton);
		layout3.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout3);
		window.setScene(scene);
		window.showAndWait();
	}

}