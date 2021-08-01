import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResourcePersonTypeDialog {

	
	
	public ResourcePersonTypeDialog(ProgrammesCostEstimates main, ObservableList<Object> personnelList, String visit,Button enableSubmitBtn, ObservableList<SimpleIntegerProperty> divisionSizes) {
		
		Stage subStage = new Stage();
		
		 subStage.setTitle("Set Resource Person Type");
       subStage.initModality(Modality.APPLICATION_MODAL);

		
		GridPane pane = new GridPane();
		 pane.setAlignment(Pos.CENTER);
		 pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		 pane.setHgap(5.5);
		 pane.setVgap(5.5);
		
		 ComboBox<String> ranks = new ComboBox<>();
		
		 ranks.setPrefWidth(200);
		 ranks.setValue("Proffessional Body");
		  ObservableList<String> items =FXCollections.observableArrayList(new ArrayList<String>(
		            Arrays.asList("Proffessional Body","Non Proffessional Body")));
		  ranks.getItems().addAll(items);
		 // Place nodes in the pane
		  
		// TextField nameOfOfficer = new TextField(nbteStaff.getName());
		  
		 pane.add(new Label("Select Resource Person Type:"), 0, 0);
		 pane.add(ranks, 1, 0);
		
		 Button btOk = new Button("OK");
		
		 btOk.setOnAction((ActionEvent e) ->{
			 String rank = ranks.getValue();
			 
			 if (rank == "Proffessional Body") {
		          new ProffessionalBodyResourcePersonDialog(main, personnelList, visit,enableSubmitBtn,divisionSizes);
	          } else {
	        	  new ResourcePersonDialog(main,false, personnelList, visit,enableSubmitBtn,divisionSizes);
	          }
			// new ResourcePersonDialog(main,(rank=="Proffessional Body")?true:false, personnelList, visit,enableSubmitBtn,divisionSizes);
			
			 subStage.close();
		 });
		 
			 pane.add(btOk, 1, 1); 
		 
		
		 
		 
		 GridPane.setHalignment(btOk, HPos.RIGHT);
		
		 
		 Scene scene = new Scene(pane);
		  // Set the stage title
		  subStage.setScene(scene); // Place the scene in the stage
		  subStage.show();
		
	}
	
	
}
