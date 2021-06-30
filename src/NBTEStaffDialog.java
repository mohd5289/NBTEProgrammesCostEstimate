import java.util.ArrayList;
import java.util.Arrays;

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

public class NBTEStaffDialog {

	public NBTEStaffDialog(ProgrammesCostEstimates main, ObservableList<Object> personnelList, ComboBox<String> visitationType) {
		
		// TODO Auto-generated constructor stub
	
String typeOfVisit = visitationType.getValue();
		
		Stage subStage = new Stage();
		
		 subStage.setTitle("Add NBTE Staff");
        subStage.initModality(Modality.APPLICATION_MODAL);

		
		GridPane pane = new GridPane();
		 pane.setAlignment(Pos.CENTER);
		 pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		 pane.setHgap(5.5);
		 pane.setVgap(5.5);
		
		 ComboBox<String> ranks = new ComboBox<>();
		
		 ranks.setPrefWidth(200);
		 ranks.setValue("Senior Staff");
		  ObservableList<String> items =FXCollections.observableArrayList(new ArrayList<String>(
		            Arrays.asList("Senior Staff","Junior Staff")));
		  ranks.getItems().addAll(items);
		 // Place nodes in the pane
		  
		  
		 pane.add(new Label("NBTE Staff Name:"), 0, 0);
		 pane.add(new TextField(), 1, 0);
		 pane.add(new Label("Rank of Officer:"), 0, 1);
		 pane.add(ranks, 1, 1);
		 
		 
		 Button btAdd = new Button("Add NBTE Staff");
		
			 pane.add(btAdd, 1, 2); 
		 
		
		 
		 
		 GridPane.setHalignment(btAdd, HPos.RIGHT);
		
		 
		 Scene scene = new Scene(pane);
		  // Set the stage title
		  subStage.setScene(scene); // Place the scene in the stage
		  subStage.show();
	
	}





}
