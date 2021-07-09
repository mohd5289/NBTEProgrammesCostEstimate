import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DriverDialog {

	public DriverDialog(ProgrammesCostEstimates main, ObservableList<Object> personnelList, Button enableSubmitBtn, ObservableList<SimpleIntegerProperty> divisionSizes) {
		// TODO Auto-generated constructor stub
		Stage subStage = new Stage();
		
	 subStage.setTitle("Set Driver Estimate");
       subStage.initModality(Modality.APPLICATION_MODAL);
		
		GridPane pane = new GridPane();
		 pane.setAlignment(Pos.CENTER);
		 pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		 pane.setHgap(5.5);
		 pane.setVgap(5.5);
		
		 TextField amountText = new TextField();
		
		 TextField fuelPriceText = new TextField();
		 // Place nodes in the pane
		 pane.add(new Label("Amount:"), 0, 0);
		 pane.add(amountText, 1, 0);
		 pane.add(new Label("Fuel:"), 0, 1);
		 pane.add(fuelPriceText, 1, 1);
		 Button btAddDriver = new Button("Add Driver");
		 btAddDriver.setOnAction((ActionEvent e)->{
			 
			 String amount = amountText.getText();
			 String fuelPrice = fuelPriceText.getText();
			 personnelList.add(new Driver(amount, fuelPrice));
		        	if(ProgrammesCostEstimates.genCostEstimateBtnClicked==false) {
					enableSubmitBtn.setVisible(true);
		        	}
					
			 subStage.close();
		 });
		 
		 pane.add(btAddDriver, 1, 2);
		 GridPane.setHalignment(btAddDriver, HPos.RIGHT);
		
		 
		 
		 // Create a scene and place it in the stage
		 Scene scene = new Scene(pane);
		// primaryStage.setTitle("ShowGridPane"); // Set the stage title
		 subStage.setScene(scene); // Place the scene in the stage
		 subStage.show();
	}

  public DriverDialog( ObservableList<Object> personnelList,int index,Driver driver) {
	  
	  Stage subStage = new Stage();
		
		 subStage.setTitle("Set Driver Estimate");
	       subStage.initModality(Modality.APPLICATION_MODAL);
			
			
	       GridPane pane = new GridPane();
			 pane.setAlignment(Pos.CENTER);
			 pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
			 pane.setHgap(5.5);
			 pane.setVgap(5.5);
			
			 TextField amountText = new TextField();
			
			 TextField fuelPriceText = new TextField();
			
			 amountText.setText(driver.getAmount());
			 fuelPriceText.setText(driver.getFuel());
			 // Place nodes in the pane
			 pane.add(new Label("Amount:"), 0, 0);
			 pane.add(amountText, 1, 0);
			 pane.add(new Label("Fuel:"), 0, 1);
			 pane.add(fuelPriceText, 1, 1);
			 Button btAddDriver = new Button("Add Driver");
			 btAddDriver.setOnAction((ActionEvent e)->{
				 personnelList.remove(driver);
				 
				 String amount = amountText.getText();
				String fuelPrice = fuelPriceText.getText();
				
				personnelList.add(index,new Driver(amount, fuelPrice));
				
				 subStage.close();
			 });
			 
			 pane.add(btAddDriver, 1, 2);
			 GridPane.setHalignment(btAddDriver, HPos.RIGHT);
			
			 
			 
			 // Create a scene and place it in the stage
			 Scene scene = new Scene(pane);
			// primaryStage.setTitle("ShowGridPane"); // Set the stage title
			 subStage.setScene(scene); // Place the scene in the stage
			 subStage.show();
	  
	  
	  
	  
  }

  public void counter(int count){
	  
	  
		count++;

	}
}
