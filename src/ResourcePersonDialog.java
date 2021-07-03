import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
		
		 subStage.setTitle(isProffessionalBody?"Set Proffessional Body Resource Person":"Set Resource Person");
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
		  TextArea textForAddress = new TextArea();
		  textForAddress.setPrefColumnCount(20);
		  textForAddress.setPrefRowCount(2);
		  textForAddress.setWrapText(true);
		  textForAddress.setStyle("-fx-text-fill: red");
		  textForAddress.setFont(Font.font("Times", 20));
		  
		TextField resourcePersonName = new TextField();
		TextField phoneNumberText = new TextField();
		TextField addressText = new TextField();
		TextField proffessionalBodyNameText = new TextField();
		 pane.add(new Label("Resource Person Name:"), 0, 0);
		 pane.add(resourcePersonName, 1, 0);
		 pane.add(new Label("State Of Location:"), 0, 1);
		 pane.add(states, 1, 1);
		 pane.add(new Label("Phone Number:"), 0, 2);
		 pane.add(phoneNumberText, 1, 2);
		 pane.add(new Label("Address:"), 0, 3);
		 pane.add(textForAddress, 1, 3);
		 Button btAddResourcePerson = new Button("Add Resource Person");
		 if(isProffessionalBody) {
			 pane.add(new Label("Name of Proffessional Body:"), 0, 4);	 
			 pane.add(proffessionalBodyNameText, 1, 4);
			
			 pane.add(btAddResourcePerson, 1, 5);
		 }else {
			 pane.add(btAddResourcePerson, 1, 4); 
		 }
		 btAddResourcePerson.setOnAction((ActionEvent e)->{
			 
			String nameOfResourcePerson = resourcePersonName.getText();
			String phoneNumber = phoneNumberText.getText();
			String address = addressText.getText();
			String nameOfProffessionalBody =  isProffessionalBody?"":proffessionalBodyNameText.getText();
			String state = states.getValue();
			 
			 
			 personnelList.add(new ResourcePerson(nameOfResourcePerson, phoneNumber, address,state, nameOfProffessionalBody,isProffessionalBody, typeOfVisit));
			 main.isProffesionalBodyResourcePerson= false;
			 subStage.close();
		 });
		 
		 
		 GridPane.setHalignment(btAddResourcePerson, HPos.RIGHT);
		
		 
		 Scene scene = new Scene(pane);
		  // Set the stage title
		  subStage.setScene(scene); // Place the scene in the stage
		  subStage.show();
	
	
	
	}

	
	public ResourcePersonDialog(ProgrammesCostEstimates main,ObservableList<Object> personnelList, int index, ResourcePerson resourcePerson) {
String typeOfVisit = resourcePerson.getVisitationExercise();
		
		Stage subStage = new Stage();
		
		 subStage.setTitle(resourcePerson.isProffessionalBody()?"Set Proffessional Body Resource Person":"Set Resource Person");
        subStage.initModality(Modality.APPLICATION_MODAL);

		
		GridPane pane = new GridPane();
		 pane.setAlignment(Pos.CENTER);
		 pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		 pane.setHgap(5.5);
		 pane.setVgap(5.5);
		
		 ComboBox<String> states = new ComboBox<>();
		
		 states.setPrefWidth(200);
		 states.setValue(resourcePerson.getStateLocation());
		  ObservableList<String> items =FXCollections.observableArrayList(new ArrayList<String>(LocationUtils.statesWithLatLng.keySet()));
		  states.getItems().addAll(items);
		 // Place nodes in the pane
		  TextArea textForAddress = new TextArea();
		  textForAddress.setPrefColumnCount(20);
		  textForAddress.setPrefRowCount(2);
		  textForAddress.setWrapText(true);
		  textForAddress.setStyle("-fx-text-fill: red");
		  textForAddress.setFont(Font.font("Times", 20));
		  textForAddress.setText(resourcePerson.getAddress());
		  
		TextField resourcePersonName = new TextField(resourcePerson.getName());
		
		TextField phoneNumberText = new TextField(resourcePerson.getPhoneNumber());
		TextField addressText = new TextField();
		TextField proffessionalBodyNameText = new TextField(resourcePerson.getNameOfProffessionalBody());
		 pane.add(new Label("Resource Person Name:"), 0, 0);
		 pane.add(resourcePersonName, 1, 0);
		 pane.add(new Label("State Of Location:"), 0, 1);
		 pane.add(states, 1, 1);
		 pane.add(new Label("Phone Number:"), 0, 2);
		 pane.add(phoneNumberText, 1, 2);
		 pane.add(new Label("Address:"), 0, 3);
		 pane.add(textForAddress, 1, 3);
		 Button btAddResourcePerson = new Button("Add Resource Person");
		 if(resourcePerson.isProffessionalBody()) {
			 pane.add(new Label("Name of Proffessional Body:"), 0, 4);	 
			 pane.add(proffessionalBodyNameText, 1, 4);
			
			 pane.add(btAddResourcePerson, 1, 5);
		 }else {
			 pane.add(btAddResourcePerson, 1, 4); 
		 }
		 btAddResourcePerson.setOnAction((ActionEvent e)->{
			 
			 personnelList.remove(index);
		
			 String nameOfResourcePerson = resourcePersonName.getText();
			String phoneNumber = phoneNumberText.getText();
			String address = textForAddress.getText();
			String nameOfProffessionalBody =  resourcePerson.isProffessionalBody()?"":proffessionalBodyNameText.getText();
			String state = states.getValue();
			 
			 
			 personnelList.add(index,new ResourcePerson(nameOfResourcePerson, phoneNumber, address,state, nameOfProffessionalBody,resourcePerson.isProffessionalBody(), typeOfVisit));
			 main.isProffesionalBodyResourcePerson= false;
			 subStage.close();
		 });
		 
		 
		 GridPane.setHalignment(btAddResourcePerson, HPos.RIGHT);
		
		 
		 Scene scene = new Scene(pane);
		  // Set the stage title
		  subStage.setScene(scene); // Place the scene in the stage
		  subStage.show();	
		
		
	}
	

	

	
}
