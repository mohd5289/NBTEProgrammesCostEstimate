import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProffessionalBodyResourcePersonDialog {
	 public ProffessionalBodyResourcePersonDialog(ProgrammesCostEstimates main, ObservableList<Object> personnelList, String visit, Button enableSubmitBtn, ObservableList<SimpleIntegerProperty> divisionSizes) {
		    Stage subStage = new Stage();
		    subStage.setTitle("Set Proffessional Body Resource Person");
		    subStage.initModality(Modality.APPLICATION_MODAL);
		    GridPane pane = new GridPane();
		    pane.setAlignment(Pos.CENTER);
		    pane.setPadding(new Insets(11.5D, 12.5D, 13.5D, 14.5D));
		    pane.setHgap(5.5D);
		    pane.setVgap(5.5D);
		    TextArea textForAddress = new TextArea();
		    textForAddress.setPrefColumnCount(20);
		    textForAddress.setPrefRowCount(2);
		    textForAddress.setWrapText(true);
		    textForAddress.setStyle("-fx-text-fill: red");
		    textForAddress.setFont(Font.font("Times", 20.0D));
		    TextField proffessionalBodyRepName = new TextField();
		    TextField phoneNumberText = new TextField();
		    pane.add(new Label("Proffessional Body Rep Name:"), 0, 0);
		    pane.add(proffessionalBodyRepName, 1, 0);
		    pane.add(new Label("Address:"), 0, 1);
		    pane.add(textForAddress, 1, 1);
		    pane.add(new Label("Phone Number:"), 0, 2);
		    pane.add(phoneNumberText, 1, 2);
		    Button btAddResourcePerson = new Button("Add Proffesional Body Resource Person");
		    pane.add(btAddResourcePerson, 1, 3);
		    btAddResourcePerson.setOnAction(e -> {
		          String nameOfProffessionalBodyResourcePerson =proffessionalBodyRepName.getText();
		          String address = textForAddress.getText();
		          String phoneNumber = phoneNumberText.getText();
		          personnelList.add(new ProffessionalBodyResourcePerson(nameOfProffessionalBodyResourcePerson, address, phoneNumber, visit));
		          if (!ProgrammesCostEstimates.genCostEstimateBtnClicked)
		        	  enableSubmitBtn.setVisible(true); 
		          subStage.close();
		        });
		    GridPane.setHalignment(btAddResourcePerson, HPos.RIGHT);
		    
		    Scene scene = new Scene(pane);
		    subStage.setScene(scene);
		    subStage.show();
		  }
		  
		  public ProffessionalBodyResourcePersonDialog(ObservableList<Object> personnelList, int index, ProffessionalBodyResourcePerson proffessionalBodyResourcePerson) {
		    String typeOfVisit = proffessionalBodyResourcePerson.getVisit();
		    Stage subStage = new Stage();
		    subStage.setTitle("Set Proffessional Body Resource Person");
		    subStage.initModality(Modality.APPLICATION_MODAL);
		    GridPane pane = new GridPane();
		    pane.setAlignment(Pos.CENTER);
		    pane.setPadding(new Insets(11.5D, 12.5D, 13.5D, 14.5D));
		    pane.setHgap(5.5D);
		    pane.setVgap(5.5D);
		    TextArea textForAddress = new TextArea(proffessionalBodyResourcePerson.getAddress());
		    textForAddress.setPrefColumnCount(20);
		    textForAddress.setPrefRowCount(2);
		    textForAddress.setWrapText(true);
		    textForAddress.setStyle("-fx-text-fill: red");
		    textForAddress.setFont(Font.font("Times", 20.0D));
		    textForAddress.setText(proffessionalBodyResourcePerson.getAddress());
		    TextField proffessionalBodyRepName = new TextField(proffessionalBodyResourcePerson.getProffessionalBodyName());
		    TextField phoneNumberText = new TextField(proffessionalBodyResourcePerson.getPhoneNumber());
		    pane.add(new Label("Proffessional Body Rep Name:"), 0, 0);
		    pane.add(proffessionalBodyRepName, 1, 0);
		    pane.add(new Label("Address:"), 0, 1);
		    pane.add(textForAddress, 1, 1);
		    pane.add(new Label("Phone Number:"), 0, 2);
		    pane.add(phoneNumberText, 1, 2);
		    Button btAddResourcePerson = new Button("Add Proffesional Body Resource Person");
		    pane.add(btAddResourcePerson, 1, 3);
		    btAddResourcePerson.setOnAction(e -> {
		    	personnelList.remove(index);
		          String nameOfProffessionalBodyResourcePerson = proffessionalBodyRepName.getText();
		          String address = textForAddress.getText();
		          String phoneNumber = phoneNumberText.getText();
		          personnelList.add(index, new ProffessionalBodyResourcePerson(nameOfProffessionalBodyResourcePerson, address, phoneNumber, typeOfVisit));
		          subStage.close();
		        });
		    GridPane.setHalignment(btAddResourcePerson, HPos.RIGHT);
		    Scene scene = new Scene(pane);
		    subStage.setScene(scene);
		    subStage.show();
		  }
}
