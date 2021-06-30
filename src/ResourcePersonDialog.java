import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResourcePersonDialog {

	
	public ResourcePersonDialog(ProgrammesCostEstimates main,boolean isProffessionalBody, ObservableList<Object> personnelList, ComboBox<String> visitationType) {
		
		String typeOfVisit = visitationType.getValue();
		
		Stage subStage = new Stage();
		
		 subStage.setTitle(isProffessionalBody?"Set ProffessionalBody Resource Person":"Set Resource Person");
        subStage.initModality(Modality.APPLICATION_MODAL);

		
		GridPane pane = new GridPane();
		 pane.setAlignment(Pos.CENTER);
		 pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		 pane.setHgap(5.5);
		 pane.setVgap(5.5);
		
		 ComboBox<String> states = new ComboBox<>();
		
		 states.setPrefWidth(200);
		 states.setValue("Abia");
		  ObservableList<String> items =FXCollections.observableArrayList(new ArrayList<String>(LocationUtils.statesWithLatLng.keySet()));
		  states.getItems().addAll(items);
		 // Place nodes in the pane
		  TextArea textForAddress = new TextArea("This is a text area");
		  textForAddress.setPrefColumnCount(20);
		  textForAddress.setPrefRowCount(2);
		  textForAddress.setWrapText(true);
		  textForAddress.setStyle("-fx-text-fill: red");
		  textForAddress.setFont(Font.font("Times", 20));
		  
		 pane.add(new Label("Resource Person Name:"), 0, 0);
		 pane.add(new TextField(), 1, 0);
		 pane.add(new Label("State Of Location:"), 0, 1);
		 pane.add(states, 1, 1);
		 pane.add(new Label("Phone Number:"), 0, 2);
		 pane.add(new TextField(), 1, 2);
		 pane.add(new Label("Address:"), 0, 3);
		 pane.add(textForAddress, 1, 3);
		 Button btAdd = new Button("Add Resource Person");
		 if(isProffessionalBody) {
			 pane.add(new Label("Name of Proffessional Body:"), 0, 4);	 
			 pane.add(new TextField(), 1, 4);
			
			 pane.add(btAdd, 1, 5);
		 }else {
			 pane.add(btAdd, 1, 4); 
		 }
		
		 
		 
		 GridPane.setHalignment(btAdd, HPos.RIGHT);
		
		 
		 Scene scene = new Scene(pane);
		  // Set the stage title
		  subStage.setScene(scene); // Place the scene in the stage
		  subStage.show();
	
	
	
	}

	

	

	
}
