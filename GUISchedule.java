import java.awt.Label;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class GUISchedule extends Application{
	Stage window;
    TableView<Employee> table;
    Button button;
    Scene scene1, scene2;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
    	window=primaryStage;
		Label Label1=new Label("GUI");
		Button Button1=new Button("Click to go to PrintEmployees");
		Button1.setOnAction(e->window.setScene(scene2));
		VBox Layout1=new VBox(20);
		Layout1.getChildren().addAll(Label1,Button1);
		scene1=new Scene(Layout1,200,200);
		Button Button2=new Button("Click to go back to Schedule");
		Button2.setOnAction(e->window.setScene(scene1));
		StackPane Layout2=new StackPane();
		Layout2.getChildren().add(Button2);
		scene2=new Scene(Layout2,600,300);
		window.setScene(scene1);
		window.setTitle("Click here");
		window.show();
    	window = primaryStage;
        window.setTitle("Employee Schedule");
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Employee, Double> hoursColumn = new TableColumn<>("Hours");
        hoursColumn.setMinWidth(100);
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("Hours"));
        table = new TableView<>();
        table.setItems(getInfo());
        table.getColumns().addAll(nameColumn, hoursColumn);
        VBox Box = new VBox();
        Box.getChildren().addAll(table);
        Scene scene1 = new Scene(Box);
        window.setScene(scene1);
        window.show();
    }
    public ObservableList<Employee> getInfo(){
        ObservableList<Employee> products = FXCollections.observableArrayList();
        products.add(new Employee("John", "11-7","12-5","11-7","12-5","11-7","8-9","8-9"));
        products.add(new Employee("John", "8-9","12-5","11-7","8-9","12-5","11-7","8-9"));
        products.add(new Employee("John", "11-7","12-5","11-7","12-5","8-9","11-7","12-5"));
        return products;
    }
}

	

