import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
public class ProgrammesCostEstimates extends Application {
	 
	String resourcePersonName= "";
	String resourcePersonPhoneNumber="";
	String resourcePersonStateOfLocation="";
	String resourcePersonAddress="";
	//boolean isProffesionalBodyResourcePerson = true;
	boolean isSeniorStaff = true;
	String nameOfProffessionalBody= "";
	String programme;
	 String divisionName;
	 String nameOfOfficer="";
	 String driverAmount="";
	 String fuelAmount;
	 XWPFDocument doc = new XWPFDocument();
	 String costEstimatesFileName= System.getProperty("user.home") + "/Desktop";
	 ArrayList<String> addedDivisions= new ArrayList<String>();
	 boolean waitingForCostEstimateFileToBeCreated = true;
	
	 public static String institutionLocation; 
	 
	 
	 public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	
		
		
		VBox background = new VBox(10);
	
	Label nameOfInstitution =  new Label("Institution Name:");
   TextField institutionField = new TextField("Kaduna Polytechnic");	
	//nameOfInstitution.setAlignment(Pos.CENTER);
     HBox institution = new HBox(9);
	institution.getChildren().addAll(nameOfInstitution,institutionField);
	institution.setPadding(new Insets(5, 5, 5, 5));
	
	
	
	
	HBox selectLocation = new HBox(5);
   
	 ComboBox<String> cbo = new ComboBox<>();
	 //HBox paneForComboBox = new HBox(5);
	
	 
	 selectLocation.getChildren().addAll(new Label("Select a Location: "),cbo); 
	 selectLocation.setPadding(new Insets(5, 5, 5, 5));
	  cbo.setPrefWidth(400);
	  cbo.setValue("Kaduna");
	  ObservableList<String> items =FXCollections.observableArrayList(new ArrayList<String>(LocationUtils.statesWithLatLng.keySet()));
	  cbo.getItems().addAll(items);
	  institutionLocation= cbo.getValue();
	 
	  HBox divisionSelectionPanel = new HBox(5);
	   divisionSelectionPanel.setPadding(new Insets(5, 5, 5, 5));  
	  ComboBox<String> divisionSelection = new ComboBox<>();
	  divisionSelection.setPrefWidth(100);
	  divisionSelection.setValue("STD");
	  divisionName = divisionSelection.getValue(); 
	  
	  ObservableList<String> divisions =FXCollections.observableArrayList( new ArrayList<String>(
	            Arrays.asList("STD", "ENTD", "ETD", "MSSD", "AACD", "PSD")));
	  
	  divisionSelection.getItems().addAll(divisions);  
	  Button addDivision = new Button("Add Division");
	//Handle Add division button
	 
	  
	  
	  //newDivision.set
	  //newDivision.getChildren().addAll(programmeLabel,);
	  
	 
	//  Button addProgrammeButton = new  Button("Add Programme");
		  
		 
		 
		addDivision.setOnAction((ActionEvent e)->{
	  			  //createDivisionLabel();
			
			 Button addProgrammeButton = new  Button("Add Programme");
		
			
			 
			 
			 HBox createProgramButtons = new HBox(3);	  
			  ComboBox<String> diplomaSelection = new ComboBox<>();
			  		  diplomaSelection.setPrefWidth(100);
			  		  diplomaSelection.setValue("ND");
			  		  ObservableList<String> diplomas =FXCollections.observableArrayList(new ArrayList<String>(
			  		            Arrays.asList("ND","HND")));
			  		  diplomaSelection.getItems().addAll(diplomas);
			  
			  		 ComboBox<String> visitationType = new ComboBox<>();
			  		 visitationType.setPrefWidth(150);
			  		 visitationType.setValue("Resource Inspection");
			  		ObservableList<String> visitations =FXCollections.observableArrayList(new ArrayList<String>(
		  		            Arrays.asList("Resource Inspection", "Accreditation", "Verification")));
			  		visitationType.getItems().addAll(visitations);  
			  		
			  		  TextField programmeName =  new TextField("e.g Public Admin"); 
			  		  
			  createProgramButtons.getChildren().addAll(new Label("Programme:"),
					                                         diplomaSelection,
					                                         visitationType,
					                                        programmeName,
					                                      addProgrammeButton);
			  
			  
			  
			  
			  VBox newProgramme= new VBox(5);
				  BackgroundFill bf2 = new BackgroundFill(Color.GRAY, new CornerRadii(1), null);
				  newProgramme.setBackground(new Background(bf2));
					newProgramme.setPadding(new Insets(5, 5, 5, 5));
			  //createDivision();
			newProgramme.getChildren().addAll(createProgramButtons);	  
			 divisionName = divisionSelection.getValue(); 
			
			 HBox divisionLabelContainer = new HBox(230);
			 BackgroundFill bf5 = new BackgroundFill(Color.WHITE, new CornerRadii(1), null);
			 divisionLabelContainer.setBackground(new Background(bf5));
			 Button writeToDocBtn = new Button("Write To Document");
			 
			 writeToDocBtn.setStyle("-fx-background-color: Blue; -fx-text-fill: Black;");
//			 writeToDocBtn.setVisible(false);
	
			 if (waitingForCostEstimateFileToBeCreated) {
				// Random rand = new Random();
				
				   
				    Date date = new Date(); 
				    long l = date.getTime();
				 costEstimatesFileName+= "/Cost Estimate for "+institutionField.getText()+" "+l+".docx";
				 XWPFParagraph title = doc.createParagraph();
				  
				 title.setAlignment(ParagraphAlignment.CENTER);
				  
				  XWPFRun titleRun = title.createRun();
					String docTitleText = "COST ESTIMATE FOR THE VISIT TO "+institutionField.getText()+","+institutionLocation+" STATE";
				  titleRun.setText(docTitleText);
				  titleRun.setBold(true);
				  titleRun.setFontFamily("Times New Roman");
				  
				  titleRun.setFontSize(11);
				  XWPFTable table = doc.createTable();
				  
				  createTableHeaders(table);
				  
					FileOutputStream out;
					try {
						out = new FileOutputStream(new File(costEstimatesFileName));
						doc.write(out);
						out.close();
						System.out.print(costEstimatesFileName+" successfully created");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				
				  
				  waitingForCostEstimateFileToBeCreated= false;
			 }
			 
			 
			 writeToDocBtn.setOnAction((ActionEvent f)->{
				    
			  //institutionField
			 
			 });
			 
			 Label divisionLabel = new Label(divisionName);
				  divisionLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,  20));
				  divisionLabel.setAlignment(Pos.CENTER);
				  BackgroundFill bf1 = new BackgroundFill(Color.WHITE, new CornerRadii(1), null);
				  divisionLabelContainer.getChildren().addAll(divisionLabel,writeToDocBtn );
				  
				  VBox newDivision = new VBox(5);
				  
					 // Label divisionLabel = new Label(divisionName);
					  divisionLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,  20));
					  divisionLabel.setAlignment(Pos.CENTER);
					  //BackgroundFill bf1 = new BackgroundFill(Color.WHITE, new CornerRadii(1), null);
					  newDivision.setBackground(new Background(bf1));
					  newDivision.setPadding(new Insets(5, 5, 5, 5));
				
					  handleButtonClick(addProgrammeButton ,newDivision,visitationType);
					  
				  		newDivision.getChildren().addAll(divisionLabelContainer,newProgramme);	 
			  			 	
	  			            
	  		       //  programme = diplomaSelection.getValue()+" "+programmeName.getText();
	  		  
	  		  //  Label programmeLabel = new Label(programme);
	  		    //     VBox newDivision = new VBox(5);
	  				 
	  		//    programmeLabel.setAlignment(Pos.CENTER);
	  			//	  BackgroundFill bf1 = new BackgroundFill(Color.WHITE, new CornerRadii(1), null);
	  				// oneDivision.setAlignment(Pos.CENTER);
	  				//  newDivision.setBackground(new Background(bf1));
	  				  //newDivision.getChildren().addAll(programmeLabel,); 
			// VBox copy = (VBox) PipedDeepCopy.copy(newDivision);
			
	  		  	if(!addedDivisions.contains(divisionSelection.getValue())) {
	   		
	   		background.getChildren().add(newDivision);
	   		addedDivisions.add(divisionSelection.getValue());
	  		  	}    
	  			  
	  			
	  });
	 
	  Button genCostEstimateBtn = new Button("Generate Cost Estimate");
	  
	  genCostEstimateBtn.setStyle("-fx-background-color: Green; -fx-text-fill: Black;");
	  //genCostEstimateBtn.setStyle("-fx-text-fill: Black");
	 
	  genCostEstimateBtn.setOnAction((ActionEvent e)->{
		 // genCostEstimateBtn.setDisable(false);	  
	  });
divisionSelectionPanel.getChildren().addAll(new Label("Select Division: "),
		                                            divisionSelection,
		                                            addDivision,
		                                            genCostEstimateBtn );
	  
	  
     VBox institutionPanel = new VBox();
     institutionPanel.setAlignment(Pos.CENTER);
     BackgroundFill bf = new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1), null);
     institutionPanel.setBackground(new Background(bf));
     institutionPanel.getChildren().addAll(institution,selectLocation,divisionSelectionPanel);
     institutionPanel.setPadding(new Insets(5, 5, 5, 5));  
	  
	
	
	//StackPane pane = new StackPane();
	//pane.getChildren().add(new Button("OK"));
		//Button btOK = new Button("OK");
	
   
	ScrollPane scrollPane = new ScrollPane(background);
    scrollPane.setPannable(true);
   scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
   scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
   
   
		background.getChildren().add(institutionPanel); 
	    background.setPadding(new Insets(5, 5, 5, 5));
	    background.setStyle("-fx-background-color: #383838");
	    background.prefWidthProperty().bind(scrollPane.widthProperty().divide(1.03));
	    background.prefHeightProperty().bind(scrollPane.heightProperty());
	    
	    Scene scene = new Scene(scrollPane, 750, 650);
		scene.getStylesheets().add("Viper.css");
		 primaryStage.setTitle("Programmes Cost Estimate Generator"); // Set the stage title
		 primaryStage.setScene(scene); // Place the scene in the stage
	  	
		 primaryStage.show();
	
	}

     private void  writeRemainingTableHeaders(XWPFTableRow tableRow, ArrayList<String> headers){
    	 
    	 for(int i =0;i< headers.size();i++) {
    		 
    		 tableRow.addNewTableCell().setText(headers.get(i));
   		  tableRow.getCell(i+1).getParagraphs().get(0).getRuns().get(0).setBold(true);
    	 }
    	 
     }

	private void createTableHeaders(XWPFTable table) {
		// TODO Auto-generated method stub
		 XWPFTableRow tableRowOne = table.getRow(0);
		 //tableRowOne.addNewTableCell().setText();
		 tableRowOne.getCell(0).setText("DATE");
		  tableRowOne.getCell(0).getParagraphs().get(0).getRuns().get(0).setBold(true);
		  
  ArrayList<String>headers = new ArrayList<String>(Arrays.asList("INSTITUTION", "PROGRAMME", "DIVISION", "PARTICIPANTS", "TRANSPORT", "HONORARIUM","TOTAL"));
		  
  writeRemainingTableHeaders(tableRowOne,headers);
	
	
	
	}



	private void handleButtonClick(Button addProgrammeButton, VBox newDivision, ComboBox<String> visitationType) {
		// TODO Auto-generated method stub
		
		
		addProgrammeButton.setOnAction((ActionEvent f)->{
			// isProffesionalBodyResourcePerson= true;
		 String visit = visitationType.getValue();	
			HBox addPersonnel = new HBox(5);
			
				
				
				ObservableList<Object> personnelList = FXCollections.observableArrayList();
				
	
				ListView<Object> personnel = new ListView<>();
				personnel.setItems(personnelList);
				personnel.setMinHeight(320);
							
					
			//personnel.getSelectionModel()
			//.getSelectedIndex();	
					 handlePersonnelButtonClicks(addPersonnel,personnelList,visit);	
				
		 newDivision.getChildren().addAll(addPersonnel,personnel);
		
		 
		 
		 HBox crudControlsContainer = new HBox(5);
		 crudControlsContainer.setPadding(new Insets(15, 15, 15, 15));
		 crudControlsContainer.setAlignment(Pos.BASELINE_RIGHT);
		 
		 Button editButton = new Button("Edit");
		 Button deleteButton = new Button("Delete");
		
		 editButton.setStyle("-fx-background-color: Blue; -fx-text-fill: Black;");
		 deleteButton.setStyle("-fx-background-color: Red; -fx-text-fill: Black;");
		 
		 
		 personnel.setOnMouseClicked(event ->{
			 int selectedIndex = personnel.getSelectionModel().getSelectedIndex();
		 Object o = personnelList.get(selectedIndex);
			 editButton.setOnAction((ActionEvent g)->{
				 if(o instanceof ResourcePerson) {
					 ResourcePerson resourcePerson = (ResourcePerson)o;
					 new ResourcePersonDialog(personnelList,selectedIndex,resourcePerson);
				 }
				 else if(o instanceof NBTEStaff) {
					 NBTEStaff nbteStaff = (NBTEStaff)o;
					 
					 new NBTEStaffDialog(personnelList,selectedIndex,nbteStaff);
				 }
				 else if(o instanceof Driver) {
					 Driver driver =(Driver)o;
					 
					 new DriverDialog(personnelList,selectedIndex,driver);
				 }
				 
			 });
			 deleteButton.setOnAction((ActionEvent g)->{
				 new DeletePersonnelDialog(personnelList,selectedIndex);
			 });
		 
		 });
		 crudControlsContainer.getChildren().addAll(editButton,deleteButton);
		 
		 newDivision.getChildren().add(crudControlsContainer)	; 
		 
		 
		 
		 });	
	}



	private void handlePersonnelButtonClicks(HBox addPersonnel, ObservableList<Object> personnelList, String visit) {
		// TODO Auto-generated method stub
		
	   Button addResourcePersonButton = new Button("Add Resource Person");
		
	   Button addOfficerButton = new Button("Add Officer"); 
	  Button  addDriverButton = new Button("Add Driver");
	
	  addResourcePersonButton.setOnAction((ActionEvent e)->{
		  
		  
		  new ResourcePersonTypeDialog(this,personnelList,visit);
		//  new ResourcePersonDialog(this,  isProffesionalBodyResourcePerson,personnelList, visitationType);
		  
		  
		  
		  
	  });
	  
	  addOfficerButton.setOnAction((ActionEvent e)->{new NBTEStaffDialog(this,personnelList,visit);});
	  
	  addDriverButton.setOnAction((ActionEvent e)->{ new DriverDialog(this, personnelList);});
	  
	  addPersonnel.getChildren().addAll(addResourcePersonButton
                ,addOfficerButton,
                addDriverButton);
	
	
	}


  }


