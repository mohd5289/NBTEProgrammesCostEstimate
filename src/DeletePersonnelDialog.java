import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeletePersonnelDialog {

	
	public DeletePersonnelDialog(ObservableList<Object> personnelList,int index) {
		Object o = personnelList.get(index);
		Stage subStage = new Stage();
		
		 subStage.setTitle("Delete Personnel");
      subStage.initModality(Modality.APPLICATION_MODAL);
		
		String  typeOfPerson ="Personnel"; 
		
		if(o instanceof ResourcePerson) {
			typeOfPerson = "Resource Person";
		}
		else if(o instanceof NBTEStaff) {
			
			typeOfPerson = "NBTE Staff";
		}
		else if(o instanceof Driver) {
			typeOfPerson = "Driver";
		}
		
		VBox dialogContents = new VBox(5);
		dialogContents.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		
		Label warningLabel= new Label("Do you want to delete "+typeOfPerson+"?");
		
		HBox deleteButtonContainer = new HBox(15);
		
		deleteButtonContainer.setPadding(new Insets(5, 5, 5, 5));
		Button btnYes = new Button("Yes");
		Button btnNo = new Button("No");
	    deleteButtonContainer.setAlignment(Pos.CENTER);	
		deleteButtonContainer.getChildren().addAll(btnYes,btnNo);
  
	    dialogContents.getChildren().addAll(warningLabel,deleteButtonContainer);
	
	   btnYes.setOnAction((ActionEvent e)->{
		   
		 personnelList.remove(index);  
		 subStage.close();
	   });
	   
	   btnNo.setOnAction((ActionEvent e)->{
		   subStage.close();
	   });
	
	
	   Scene scene = new Scene(dialogContents);
		  // Set the stage title
		  subStage.setScene(scene); // Place the scene in the stage
		  subStage.show();
	
	
	}








}
