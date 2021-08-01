import java.util.ArrayList;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
	 
	
	//boolean isProffesionalBodyResourcePerson = true;
	
	 String divisionName="";
	 
	 XWPFDocument doc = new XWPFDocument();
	 
	 String costEstimatesFileName= System.getProperty("user.home") + "/Desktop";
	 ArrayList<String> addedDivisions= new ArrayList<String>();
	 boolean waitingForCostEstimateFileToBeCreated = true;
	static boolean genCostEstimateBtnClicked= false;
	 boolean firstDivisionClicked= false;
	public static int grandTotalEstimate=0;
	ObservableList<Object> divisionAggregatedPersonnelList = FXCollections.observableArrayList();
	 public static String institutionLocation; 
	 Button genCostEstimateBtn = new Button("Generate Cost Estimate");
	 //public SimpleIntegerProperty grandTotalEstimateListener = new SimpleIntegerProperty();	
	 public SimpleIntegerProperty binder = new SimpleIntegerProperty();
	AggregatedObservableArrayList<Object> aggregatedWrapper = new AggregatedObservableArrayList<Object>();
	 ObservableList<SimpleIntegerProperty> divisionSizes = FXCollections.observableArrayList();
     ObservableList<ArrayList<String>>diplomasAndProgrammes= FXCollections.observableArrayList(); 
     XWPFTableRow tableRowOne;
     Desktop desktop = Desktop.getDesktop();
	 
	 public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}
	
	

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException  {
		// TODO Auto-generated method stub
	
		genCostEstimateBtn.setVisible(false);
		
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
		  institutionLocation= cbo.getValue();
		
			 Button addProgrammeButton = new  Button("Add Programme");
		
			// ObservableList<?> divisionPersonel = new ObservableList<?>() ;
				if(!addedDivisions.contains(divisionSelection.getValue())) {
			      aggregatedWrapper = new AggregatedObservableArrayList<>();
			  divisionAggregatedPersonnelList = aggregatedWrapper.getAggregatedList(); 
			  divisionSizes = FXCollections.observableArrayList();
			     diplomasAndProgrammes= FXCollections.observableArrayList(); 
				}
		       
		        		//new ArrayList<ArrayList<String>>();
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
			  		
			  		  TextField programmeName =  new TextField("Public Admin"); 
			  		  
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
			 divisionSelection.setDisable(true);
			 HBox divisionLabelContainer = new HBox(230);
			 BackgroundFill bf5 = new BackgroundFill(Color.WHITE, new CornerRadii(1), null);
			 divisionLabelContainer.setBackground(new Background(bf5));
			 Button submitToDocBtn = new Button("Submit To Document");
			 
			 submitToDocBtn.setStyle("-fx-background-color: Blue; -fx-text-fill: Black;");
//			 writeToDocBtn.setVisible(false);
			boolean enableSubmitButton = false;
			  if (waitingForCostEstimateFileToBeCreated) {
					// Random rand = new Random();
					
					    Date date = new Date(); 
					    long l = date.getTime();
					 costEstimatesFileName+= "/Cost Estimate for "+institutionField.getText()+" "+l+".docx";
					  String institutionTableName=institutionField.getText()+","+institutionLocation+" State";
					 
						String docTitleText = "COST ESTIMATE FOR THE VISIT TO "+institutionField.getText()+","+institutionLocation+" STATE";
						  XWPFParagraph title = doc.createParagraph();
						  XWPFRun titleRun = title.createRun();
						  title.setAlignment(ParagraphAlignment.CENTER);
						
						 titleRun.setText(docTitleText);
					  titleRun.setBold(true);
					  titleRun.setFontFamily("Times New Roman");
					  
					  titleRun.setFontSize(11);
					 
						
						 XWPFTable table= doc.createTable(); 
						 
						 ArrayList<String>headers = new ArrayList<String>(Arrays.asList("DATE","INSTITUTION", "PROGRAMME", "DIVISION", "PARTICIPANTS", "TRANSPORT", "HONORARIUM","TOTAL"));
						 XWPFTableRow headingRow = table.getRow(0);
						// XWPFTableRow normalRow = table.getRow(0);
						 addRemainingTableCells(headingRow,headers);
						
            tableRowOne=		doc.getTableArray(0).createRow();
					 	  
					  waitingForCostEstimateFileToBeCreated= false;
				 }	 
			 
				
				tableRowOne.getCell(0).setText("col one, row two");
			      tableRowOne.getCell(1).setText("col two, row two");
			      tableRowOne.getCell(2).setText("col three, row two");
			      tableRowOne.getCell(3).setText("col one, row two");
			      tableRowOne.getCell(4).setText("col two, row two");
			      tableRowOne.getCell(5).setText("col three, row two");
			      tableRowOne.getCell(6).setText("col one, row two");
			      tableRowOne.getCell(7).setText("col two, row two");
			 
			 submitToDocBtn.setVisible(false);
			 
			 submitToDocBtn.setOnAction((ActionEvent f)->{
					int divisionTotalEstimate = 0;
				 XWPFTable table = doc.getTableArray(0);
				 
				
				//XWPFTableRow tableRowOne = table.getRow(1);
				
				//new XWPFDocument()
				
				 CTVMerge vmerge = CTVMerge.Factory.newInstance();
				 vmerge.setVal(STMerge.RESTART);
				 for(int i = 0,k =0;i<  divisionSizes.size(); i++) {
					 
					 if(i==0) {
						
						 if(firstDivisionClicked) {
							 setMergeContinue(0,tableRowOne);
							setMergeContinue(1,tableRowOne); 		
							 writeDivisionNameToCell(tableRowOne,divisionName);
						 }else {
						
					setBlankCell(0,tableRowOne); 
					setMergeRestart(0,tableRowOne);
					setMergeRestart(1,tableRowOne);
						 writeInstitutionNameToCell( tableRowOne, institutionField);
						 writeDivisionNameToCell(tableRowOne,divisionName);
					 }
						 setMergeRestart(3,tableRowOne);
					 }
					 for (int j= 0;j < divisionSizes.get(i).getValue(); j++) {
						 ArrayList<String> programmesSpecifications= diplomasAndProgrammes.get(i); 
						 Object o = divisionAggregatedPersonnelList.get(k);        					
						 if(i==0 && j==0) {
						 //  XWPFTableRow newTableRow=table.createRow();
						   setBlankCell(2,tableRowOne);
						   setMergeRestart(2,tableRowOne);
						   writeProgrammeNameToCell(tableRowOne,programmesSpecifications.get(0),programmesSpecifications.get(1),programmesSpecifications.get(2));
						   checkAndWritePersonnelToCells(o,tableRowOne, table,j,k,divisionTotalEstimate);
						  }else if(i!=0 && j==0) {
							 XWPFTableRow newTableRow = table.createRow();
							 setNewRow(newTableRow);
							   setMergeRestart(2,newTableRow);
							   writeProgrammeNameToCell(newTableRow,programmesSpecifications.get(0),programmesSpecifications.get(1),programmesSpecifications.get(2));
							   checkAndWritePersonnelToCells(o,newTableRow, table,j,k,divisionTotalEstimate);  
						  }
						   else {  
							checkAndWritePersonnelToCells(o,setNewRow(table.createRow()), table,j,k,divisionTotalEstimate);  
						  }
						 k++;  
					 
					 
					 }   }
				 
			  //institutionField
				// grandTotalEstimate+=divisionTotalEstimate;
				// System.out.print(divisionTotalEstimate);
				// System.out.print(grandTotalEstimate);
				 
				 divisionSelection.setDisable(false);
				 firstDivisionClicked= true;	 
				 submitToDocBtn.setVisible(false);
				 genCostEstimateBtn.setVisible(true);
			 });
			 
			 Label divisionLabel = new Label(divisionName);
				  divisionLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,  20));
				  divisionLabel.setAlignment(Pos.CENTER);
				  BackgroundFill bf1 = new BackgroundFill(Color.WHITE, new CornerRadii(1), null);
				  divisionLabelContainer.getChildren().addAll(divisionLabel,submitToDocBtn );
				  
				  VBox newDivision = new VBox(5);
				  
					 // Label divisionLabel = new Label(divisionName);
					  divisionLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,  20));
					  divisionLabel.setAlignment(Pos.CENTER);
					  //BackgroundFill bf1 = new BackgroundFill(Color.WHITE, new CornerRadii(1), null);
					  newDivision.setBackground(new Background(bf1));
					  newDivision.setPadding(new Insets(5, 5, 5, 5));
				
					  handleButtonClick(addProgrammeButton ,newDivision,visitationType,divisionSizes,aggregatedWrapper
							  ,diplomaSelection,programmeName,diplomasAndProgrammes,submitToDocBtn);
					  if(diplomasAndProgrammes.size()==0) {
						  submitToDocBtn.setVisible(false);
					  }
				  		newDivision.getChildren().addAll(divisionLabelContainer,newProgramme);	 
			  			 	
	  			            
	  		      
			
	  		  	if(!addedDivisions.contains(divisionSelection.getValue())) {
	   		
	   		background.getChildren().add(newDivision);
	   		addedDivisions.add(divisionSelection.getValue());
	  		  	}    
	  			  
	  			
	  });
	 
	 
	  genCostEstimateBtn.setVisible(false);	
	  genCostEstimateBtn.setStyle("-fx-background-color: Green; -fx-text-fill: Black;");
	  //genCostEstimateBtn.setStyle("-fx-text-fill: Black");
	 
	  genCostEstimateBtn.setOnAction((ActionEvent e)->{
		 // genCostEstimateBtn.setDisable(false);	
		 XWPFTable table = doc.getTableArray(0);
		 XWPFTableRow totalEstimateTableRow =  table.createRow();
		 setNewRow(totalEstimateTableRow);
		
		 for(int i=0;i<4; i++ ) {
		 setMergeRestart(i, totalEstimateTableRow);
		 }
		 XWPFParagraph totalParagraph = new XWPFDocument().createParagraph();
		 XWPFRun runTotalParagraph =  totalParagraph.createRun();
		 
		 runTotalParagraph.setBold(true);
		 runTotalParagraph.setText("TOTAL");
		 totalEstimateTableRow.getCell(1).setParagraph(totalParagraph);
		 
		 XWPFParagraph totalEstimateParagraph = new XWPFDocument().createParagraph();
		 XWPFRun runTotalEstimateParagraph =  totalEstimateParagraph.createRun();
		 
		 runTotalEstimateParagraph.setBold(true);
		 runTotalEstimateParagraph.setText(String.valueOf(grandTotalEstimate));
		 totalEstimateTableRow.getCell(7).setParagraph(totalEstimateParagraph);
		 
		 System.out.println(grandTotalEstimate);
		  
		File costEstimateFile =   new File(costEstimatesFileName);
		  FileOutputStream out;
		  try {
				out = new FileOutputStream(costEstimateFile);	
				doc.write(out);
				out.close();
				System.out.print(costEstimatesFileName+" successfully created");
				desktop.open(costEstimateFile);
		  } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		  genCostEstimateBtn.setVisible(false);
		  genCostEstimateBtnClicked= true;	 
	 
	  
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
		 primaryStage.setTitle("Programmes Cost Estimate Generator (Beta Test Version)"); // Set the stage title
		 primaryStage.setScene(scene); // Place the scene in the stage
	  	
		 primaryStage.show();
	
	}

     private void setBlankCell(int i, XWPFTableRow tableRowOne) {
		// TODO Auto-generated method stub
	XWPFParagraph paragraph = new XWPFDocument().createParagraph();
    	 paragraph.createRun().setText("",0);
    	 tableRowOne.getCell(i).setParagraph(paragraph);
	
	}



	private void writeDivisionNameToCell(XWPFTableRow tableRowOne, String divisionSelection) {
		// TODO Auto-generated method stub
    	// tableRowOne.getCell(3).addParagraph();
		XWPFParagraph paragraph= new XWPFDocument().createParagraph();
    	 XWPFRun run = paragraph.createRun();
    	 run.setBold(true);
    	 run.setText(divisionSelection);
    	 tableRowOne.getCell(3).setParagraph(paragraph);
	}



	private void writeProgrammeNameToCell(XWPFTableRow tableRowOne, String diploma, String programme,
			String visit) {
		// TODO Auto-generated method stub
    	// XWPFParagraph paragraph= tableRowOne.getCell(2).addParagraph();
		XWPFParagraph paragraph= new XWPFDocument().createParagraph();
    	 
		XWPFRun run  = paragraph.createRun();
		
		XWPFRun visitRun  = paragraph.createRun();      
		run.setText(diploma);
	          run.addBreak();
	          run.setText(programme);
	          run.addBreak();
	          visitRun.setBold(true);
	          visitRun.setText("("+visit+")");
	          tableRowOne.getCell(2).setParagraph(paragraph);
     }

	@SuppressWarnings("rawtypes")
	private void handleButtonClick(Button addProgrammeButton, VBox newDivision, ComboBox<String> visitationType, ObservableList<SimpleIntegerProperty> divisionSizes, AggregatedObservableArrayList<Object> aggregatedWrapper, ComboBox<String> diplomaSelection, TextField programmeName,
			ObservableList<ArrayList<String>> diplomasAndProgrammes, Button submitToDocBtn ) {
		// TODO Auto-generated method stub
		
		
		addProgrammeButton.setOnAction((ActionEvent f)->{
			// isProffesionalBodyResourcePerson= true;
			
			
			String diploma = diplomaSelection.getValue();
			
			String programme = programmeName.getText();
			//String diplomaAndProgramme= diploma+" "+programme;
			String visit = visitationType.getValue();
		ArrayList<String> programmesListSpecification = new ArrayList<String>(Arrays.asList(diploma, programme,visit));
			
			diplomasAndProgrammes.add(programmesListSpecification);
				
			HBox addPersonnel = new HBox(5);
			
				
				
				ObservableList<Object> personnelList = FXCollections.observableArrayList();
				//divisionSizes.add(personnelList.size());
	
				ListView<Object> personnel = new ListView<>();
				personnel.setItems(personnelList);
				personnel.setMinHeight(320);
				aggregatedWrapper.appendList(personnelList);
				
				
				  
				  
				  personnelList.addListener(new ListChangeListener() {
					    

						@Override
						public void onChanged(Change c) {
							 SimpleIntegerProperty listSize= new SimpleIntegerProperty(personnelList.size());
							binder.bind(listSize);
							// TODO Auto-generated method stub
					  
					  }
					});
				 
				  divisionSizes.add(binder);
				  int indexOfBinder= divisionSizes.indexOf(binder);
				   ArrayList<String> diplomaAndProgramme= diplomasAndProgrammes.get(indexOfBinder);
				  binder.addListener((ObservableValue<? extends Number> obs, Number oldValue, Number newValue) -> {
					   // System.out.print("Calm down");
						
						if(newValue.intValue()==0) {
							diplomasAndProgrammes.remove(diplomaAndProgramme);
					    	divisionSizes.remove(binder);
					    }if(divisionSizes.size()==0){
					    	submitToDocBtn.setVisible(false);
					    }
					   else if(!divisionSizes.contains(binder) &&newValue.intValue()==1){
					    	divisionSizes.add(binder);
					    	diplomasAndProgrammes.add(diplomaAndProgramme);
					   }
						
					    
					    }); 		  
				  
			//personnel.getSelectionModel()
			//.getSelectedIndex(); 	
					 handlePersonnelButtonClicks(addPersonnel,personnelList,visit,submitToDocBtn,divisionSizes);	
				
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
				 else if(o instanceof ProffessionalBodyResourcePerson) {
					 ProffessionalBodyResourcePerson  proffessionalBodyResourcePerson = ( ProffessionalBodyResourcePerson)o;
					 
					 new ProffessionalBodyResourcePersonDialog(personnelList,selectedIndex,proffessionalBodyResourcePerson);
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
		 
			//System.out.print(divisionSizes.get(0));
		 
		 crudControlsContainer.getChildren().addAll(editButton,deleteButton);
		 
		 newDivision.getChildren().add(crudControlsContainer)	; 
		
		// if(personnelList.isEmpty()) {
			// submitToDocBtn.setVisible(false);
		//	 personnelList.remove(index);
		//	 diplomasAndProgrammes.remove(index);
			 
		 //}
		
	
		});	
	
	}



	private void handlePersonnelButtonClicks(HBox addPersonnel, ObservableList<Object> personnelList, String visit, Button submitToDocBtn, ObservableList<SimpleIntegerProperty> divisionSizes) {
		// TODO Auto-generated method stub
	  int count = 0;	
	   Button addResourcePersonButton = new Button("Add Resource Person");
		
	   Button addOfficerButton = new Button("Add Officer"); 
	  Button  addDriverButton = new Button("Add Driver");
	
	  addResourcePersonButton.setOnAction((ActionEvent e)->{
		  
		  
		  new ResourcePersonTypeDialog(this,personnelList,visit,submitToDocBtn,divisionSizes);
		//  new ResourcePersonDialog(this,  isProffesionalBodyResourcePerson,personnelList, visitationType);
		 // counter(count);
		  
		  
		  
	  });
	  
	  addOfficerButton.setOnAction((ActionEvent e)->{new NBTEStaffDialog(this,personnelList,visit,submitToDocBtn,divisionSizes);});
	  
	  addDriverButton.setOnAction((ActionEvent e)->{ new DriverDialog(this, personnelList,submitToDocBtn,divisionSizes);});
	  
	  
	  addPersonnel.getChildren().addAll(addResourcePersonButton
                ,addOfficerButton,
                addDriverButton);
	
		
	}
	
	private void writeDriverToCell(XWPFTable table,XWPFTableRow tableRow, Driver driver, int j,int k) {
		mergeEmptyCells(tableRow,j,k, new XWPFDocument().createParagraph());
		
		//XWPFParagraph paragraphDriver=tableRow.getCell(7).addParagraph();
		XWPFParagraph paragraphDriver= new XWPFDocument().createParagraph();
		XWPFRun runDriver=	paragraphDriver.createRun();
		runDriver.setText("Driver");
		tableRow.getCell(4).setParagraph(paragraphDriver);
		//tableRow.getCell(7).setParagraph(driver.getAmount());
		
		//XWPFParagraph paragraphDriverAmount=tableRow.getCell(4).addParagraph();
		XWPFParagraph paragraphDriverAmount=new XWPFDocument().createParagraph();
		XWPFRun runDriverAmount=	 paragraphDriverAmount.createRun();
		runDriverAmount.setText(driver.getAmount());
		tableRow.getCell(7).setParagraph(paragraphDriverAmount);
		runDriverAmount.setText(driver.getAmount());
		
		XWPFTableRow tableRow1 =setNewRow(table.createRow());
		
		setBlankCell( 0, tableRow1);
		setMergeContinue(0,tableRow1);
		
		setBlankCell( 1, tableRow1);
		setMergeContinue(1,tableRow1);
		
		setBlankCell(2, tableRow1);
		setMergeContinue(2,tableRow1);
		
		setBlankCell( 3, tableRow1);
		setMergeContinue(3,tableRow1);
		
		//XWPFParagraph paragraphFuel = tableRow1.getCell(4).addParagraph();
		//XWPFParagraph paragraphFuelAmount =  tableRow1.getCell(7).addParagraph();
		XWPFParagraph paragraphFuel = new XWPFDocument().createParagraph();
		XWPFParagraph paragraphFuelAmount =new XWPFDocument().createParagraph();
		
		
		
		 XWPFRun runFuel = paragraphFuel.createRun();
		runFuel.setText("Fuel");
		 tableRow1.getCell(4).setParagraph(paragraphFuel);
		 
		XWPFRun runFuelAmount = paragraphFuelAmount.createRun();
		runFuelAmount.setText(driver.getFuel());
		tableRow1.getCell(7).setParagraph(paragraphFuelAmount);
	
	
	
	
	
	}
	
private void mergeEmptyCells(XWPFTableRow tableRow,int j,int k,XWPFParagraph paragraph) {
	CTVMerge vmergeContinue = CTVMerge.Factory.newInstance();
	vmergeContinue.setVal(STMerge.CONTINUE);
	
	if(j!=0) {
		setMergeContinue(2,tableRow);
		
	}
	if(k!=0) {
		setMergeContinue(0,tableRow);
		setMergeContinue(1,tableRow);
		setMergeContinue(3,tableRow);
		
	}
	
}
	
	
	private void writeNBTEStaffToCell(XWPFTableRow tableRow, NBTEStaff nbteStaff, int j,int k) {
		
		mergeEmptyCells(tableRow,j,k, new XWPFDocument().createParagraph());
		//XWPFParagraph paragraph=tableRow.getCell(4).addParagraph();
		XWPFParagraph paragraph= new XWPFDocument().createParagraph();
		
		XWPFRun titleRun=	paragraph.createRun();
		XWPFRun nameRun=   paragraph.createRun();
		titleRun.setBold(true);
		titleRun.setText("NBTE Staff");
		titleRun.addBreak();
		nameRun.setText(nbteStaff.getName());
		nameRun.setBold(false);
		tableRow.getCell(4).setParagraph(paragraph);
	
	
		XWPFParagraph paragraphTransport = new XWPFDocument().createParagraph();
		XWPFRun transportRun=	paragraphTransport.createRun();
		transportRun.setText( String.valueOf(nbteStaff.getTransport()));
		
		tableRow.getCell(5).setParagraph(paragraphTransport);
		
		XWPFParagraph paragraphHonoriarum = new XWPFDocument().createParagraph();
		XWPFRun honoriarumRun=	paragraphHonoriarum.createRun();
	     
		honoriarumRun.setText( String.valueOf(nbteStaff.getHonorarium()));
		tableRow.getCell(6).setParagraph(paragraphHonoriarum);
	
		XWPFParagraph paragraphTotalEstimate = new XWPFDocument().createParagraph();
		XWPFRun totalEstimateRun=	paragraphTotalEstimate.createRun();
	     
		 totalEstimateRun.setText( String.valueOf(nbteStaff.getTotalEstimate()));
		tableRow.getCell(7).setParagraph(paragraphTotalEstimate);
	}
	
	
	private void writeProffessionalBodyResourcePersonToCell(XWPFTableRow tableRow, ResourcePerson resourcePerson, int j,int k) {
		mergeEmptyCells(tableRow,j,k,new XWPFDocument().createParagraph());
		
	//	XWPFParagraph paragraph=tableRow.getCell(4).addParagraph();
		XWPFParagraph paragraph = new XWPFDocument().createParagraph();
		
		XWPFRun run=	paragraph.createRun();
		
		
		run.setText(resourcePerson.getNameOfProffessionalBody());
		run.addBreak();
		run.setText(resourcePerson.getStateLocation());
		run.addBreak();
		run.setText(resourcePerson.getPhoneNumber());
	
		tableRow.getCell(4).setParagraph(paragraph);
	
		XWPFParagraph paragraphTransport = new XWPFDocument().createParagraph();
		XWPFRun transportRun=	paragraphTransport.createRun();
	     
		transportRun.setText( String.valueOf(resourcePerson.getTransport()));
		tableRow.getCell(5).setParagraph(paragraphTransport);
	
		XWPFParagraph paragraphHonoriarum = new XWPFDocument().createParagraph();
		XWPFRun honoriarumRun=	paragraphHonoriarum.createRun();
	     
		honoriarumRun.setText( String.valueOf(resourcePerson.getHonorarium()));
		tableRow.getCell(6).setParagraph(paragraphHonoriarum);
	
		XWPFParagraph paragraphTotalEstimate = new XWPFDocument().createParagraph();
		XWPFRun totalEstimateRun=	paragraphTotalEstimate.createRun();
	     
		 totalEstimateRun.setText( String.valueOf(resourcePerson.getTotalEstimate()));
		tableRow.getCell(7).setParagraph(paragraphTotalEstimate);
	}
	
	private void writeResourcePersonNameToCell(XWPFTableRow tableRow, ResourcePerson resourcePerson,int j,int k) {
		//XWPFParagraph paragraph=tableRow.getCell(4).addParagraph();
		mergeEmptyCells(tableRow,j,k, new XWPFDocument().createParagraph());
		XWPFParagraph paragraph= new XWPFDocument().createParagraph();
		XWPFRun run=	paragraph.createRun();	
		run.setText(resourcePerson.getName());
		run.addBreak();
		run.setText(resourcePerson.getAddress());
		run.addBreak();
		run.setText(resourcePerson.getPhoneNumber());
		tableRow.getCell(4).setParagraph(paragraph);
		
		XWPFParagraph paragraphTransport = new XWPFDocument().createParagraph();
		XWPFRun transportRun=	paragraphTransport.createRun();
		transportRun.setText( String.valueOf(resourcePerson.getTransport()));
		
		tableRow.getCell(5).setParagraph(paragraphTransport);
		
		XWPFParagraph paragraphHonoriarum = new XWPFDocument().createParagraph();
		XWPFRun honoriarumRun=	paragraphHonoriarum.createRun();
	     
		honoriarumRun.setText( String.valueOf(resourcePerson.getHonorarium()));
		tableRow.getCell(6).setParagraph(paragraphHonoriarum);
	
		XWPFParagraph paragraphTotalEstimate = new XWPFDocument().createParagraph();
		XWPFRun totalEstimateRun=	paragraphTotalEstimate.createRun();
	     
		 totalEstimateRun.setText( String.valueOf(resourcePerson.getTotalEstimate()));
		tableRow.getCell(7).setParagraph(paragraphTotalEstimate);
	}
private void writeInstitutionNameToCell(XWPFTableRow tableRow, TextField institutionField) {
//	XWPFParagraph paragraph=tableRow.getCell(1).addParagraph();
	//((XWPFTable) table).createRow()
	 XWPFDocument document = new XWPFDocument();
	 XWPFParagraph paragraph =document.createParagraph();
	XWPFRun run=	paragraph.createRun();
				  run.setText(institutionField.getText()+",");
				  run.addBreak();
				  run.setText(institutionLocation);
				  run.addBreak();
				  run.setText("State");
				  tableRow.getCell(1).setParagraph(paragraph);
}
private void writeProffessionalBodyResourcePersonToCell(XWPFTableRow tableRow, ProffessionalBodyResourcePerson proffessionalBodyResourcePerson, int j, int k) {
    mergeEmptyCells(tableRow, j, k, (new XWPFDocument()).createParagraph());
    XWPFParagraph paragraph = (new XWPFDocument()).createParagraph();
    XWPFRun run = paragraph.createRun();
    run.setText(proffessionalBodyResourcePerson.getProffessionalBodyName());
    run.addBreak();
    run.setText(proffessionalBodyResourcePerson.getAddress());
    run.addBreak();
    run.setText(proffessionalBodyResourcePerson.getPhoneNumber());
    tableRow.getCell(4).setParagraph(paragraph);
    XWPFParagraph paragraphTransport = (new XWPFDocument()).createParagraph();
    XWPFRun transportRun = paragraphTransport.createRun();
    transportRun.setText(String.valueOf(proffessionalBodyResourcePerson.getTransport()));
    tableRow.getCell(5).setParagraph(paragraphTransport);
    XWPFParagraph paragraphHonoriarum = (new XWPFDocument()).createParagraph();
    XWPFRun honoriarumRun = paragraphHonoriarum.createRun();
    honoriarumRun.setText(String.valueOf(proffessionalBodyResourcePerson.getHonorarium()));
    tableRow.getCell(6).setParagraph(paragraphHonoriarum);
    XWPFParagraph paragraphTotalEstimate = (new XWPFDocument()).createParagraph();
    XWPFRun totalEstimateRun = paragraphTotalEstimate.createRun();
    totalEstimateRun.setText(String.valueOf(proffessionalBodyResourcePerson.getTotalEstimate()));
    tableRow.getCell(7).setParagraph(paragraphTotalEstimate);
  }

private void checkAndWritePersonnelToCells(Object o,XWPFTableRow tableRow, XWPFTable table, int j,int k, int divisionTotalEstimate) {
	if(o instanceof ResourcePerson) {						   
		   ResourcePerson resourcePerson= (ResourcePerson)o;
		   divisionTotalEstimate+= resourcePerson.getTotalEstimate();
		   System.out.print(divisionTotalEstimate);
	}
		   else if (o instanceof ProffessionalBodyResourcePerson) {
			   ProffessionalBodyResourcePerson proffessionalBodyResourcePerson = (ProffessionalBodyResourcePerson)o;
			   divisionTotalEstimate+= proffessionalBodyResourcePerson.getTotalEstimate();
			      writeProffessionalBodyResourcePersonToCell(tableRow, proffessionalBodyResourcePerson, j, k);
			    }
		else if(o instanceof NBTEStaff) {
			 NBTEStaff nbteStaff = (NBTEStaff)o;
			writeNBTEStaffToCell(tableRow, nbteStaff,j,k);
			divisionTotalEstimate+= nbteStaff.getTotalEstimate();
		}
		else if(o instanceof Driver) {
			 Driver driver =(Driver)o;
			 writeDriverToCell(table, tableRow, driver,j,k);
			 divisionTotalEstimate+= driver.getTotalEstimate();
		}
	
	grandTotalEstimate+=divisionTotalEstimate;
	System.out.println(grandTotalEstimate);
	//grandTotalEstimateListener = new SimpleIntegerProperty(grandTotalEstimate);	

//	grandTotalEstimateListener.addListener((ObservableValue<? extends Number> obs, Number oldValue, Number newValue) -> {
		   // System.out.print("Calm down");
	//		if(newValue.intValue()==0) {
		//		genCostEstimateBtn.setVisible(false);	
		//    }   
			
		  //  });       

}	



private void addRemainingTableCells(XWPFTableRow tableRow,ArrayList<String>headers) {
	
	 tableRow.getCell(0).setText("DATE");
	  tableRow.getCell(0).getParagraphs().get(0).getRuns().get(0).setBold(true);
	for(int i =1 ;i< headers.size() ;i++) {
		
		tableRow.addNewTableCell().setText(headers.get(i));
		tableRow.getCell(i).getParagraphs().get(0).getRuns().get(0).setBold(true);
	}
	
	
}
private void setMergeRestart(int i, XWPFTableRow tableRow) {
	CTVMerge vmerge = CTVMerge.Factory.newInstance();
	vmerge.setVal(STMerge.RESTART);
	if (tableRow.getCell(i).getCTTc().getTcPr()==null) tableRow.getCell(i).getCTTc().addNewTcPr();
	
	tableRow.getCell(i).getCTTc().getTcPr().setVMerge(vmerge);
	
}
private void setMergeContinue(int i, XWPFTableRow tableRow) {
	CTVMerge vmerge = CTVMerge.Factory.newInstance();
	vmerge.setVal(STMerge.CONTINUE);
	if (tableRow.getCell(i).getCTTc().getTcPr()==null) tableRow.getCell(i).getCTTc().addNewTcPr();
	
	tableRow.getCell(i).getCTTc().getTcPr().setVMerge(vmerge);
	
}
private XWPFTableRow setNewRow(XWPFTableRow tableRow) {
	//XWPFTableRow row;

	for(int i =0;i< 8; i++) {
		
	setBlankCell(i,tableRow);	
	}
	
		return tableRow;
}
}



