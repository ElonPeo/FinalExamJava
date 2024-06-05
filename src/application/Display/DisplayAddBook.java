package application.Display;

import application.BasicClass.Book;
import application.BasicClass.EBook;
import application.BasicClass.Library;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.RadioButton;  
import javafx.scene.control.ToggleGroup;  
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TextFormatter;





public class DisplayAddBook {

    
	private int isPressed = 0;
    private TextField titleTextField; 
    private TextField authorTextField;
    private TextField yearPublishTextField;
    private TextField fileFormatTextField;
    private TextField fileSizeTextField;
    public VBox addBook(String databaseFilePath) {
        VBox addBookVvBox = new VBox();
        addBookVvBox.setAlignment(Pos.CENTER);
        Button addBookButton = new Button("Add Book");
        addBookVvBox.getChildren().addAll(addBookButton);
        
    	titleTextField = new TextField();
    	authorTextField = new TextField();
    	yearPublishTextField = new TextField();
        yearPublishTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        
    	fileFormatTextField = new TextField();
    	fileSizeTextField = new TextField();
    	fileSizeTextField.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
    	
    	
    	ToggleGroup group = new ToggleGroup();  
        RadioButton typeBookButton = new RadioButton("Book");  
        RadioButton typeEBookButton = new RadioButton("EBook");  
        typeBookButton.setToggleGroup(group);  
        typeEBookButton.setToggleGroup(group);  
    	HBox abcdeghBox = new HBox(10);
    	abcdeghBox.getChildren().addAll(typeBookButton,typeEBookButton);
    	typeBookButton.setSelected(true);

    	
    	
    	Label typeBookLabel = new Label("Type Book: ");
    	Label titleBookLabel = new Label("Enter the book title: ");
    	Label authorBookLabel = new Label("Enter the book author: ");
    	Label yearPublishLabel = new Label("Enter year publish(integer): ");
    	Label fileFormatLabel = new Label("Enter year file format: ");
    	Label fileSizeLabel = new Label("Enter file size(double): ");
    	
    	VBox abcd =new VBox();
    	abcd.setPrefSize(10, 10);
    	VBox abcd1 =new VBox();
    	abcd1.setPrefSize(20, 20);
    	VBox abcd2 =new VBox();
    	abcd2.setPrefSize(20, 0);
    	
    	VBox labelVBox = new VBox(20);
    	labelVBox.getChildren().addAll(abcd,typeBookLabel,titleBookLabel,authorBookLabel,yearPublishLabel);
    	VBox textFieldVBox = new VBox(10);
    	textFieldVBox.getChildren().addAll(abcd1,abcdeghBox,abcd2,titleTextField,authorTextField,yearPublishTextField);
    	
    	VBox abcdeg = new VBox();
    	abcdeg.setPrefSize(20, 20);
    	
    	HBox addBookHBox = new HBox(10);
    	addBookHBox.getChildren().addAll(abcdeg,labelVBox,textFieldVBox);
    	
    	HBox centerBox = new HBox();
    	centerBox.setAlignment(Pos.CENTER);
    	centerBox.getChildren().addAll(addBookHBox);
    	
    	//*********************************titleBox**************************
    	Text titleTextAddBook = new Text("Add Book"); 
	      titleTextAddBook.setStyle(
	              "-fx-font-family: Arial; " +
	              "-fx-font-size: 20px; " +
	              "-fx-font-weight: bold; "
	      );
	      VBox titleBox = new VBox(0);
	      titleBox.setPrefWidth(850);
	      titleBox.setPrefHeight(60);
	      titleBox.setAlignment(Pos.CENTER);
	      titleBox.getChildren().add(titleTextAddBook);
	      titleBox.setStyle("-fx-background-color: rgb(200, 100, 250);");
	    
	    	typeBookButton.setOnAction(e -> {
	            if (typeBookButton.isSelected()) {
	            	isPressed = 0;
	            	labelVBox.getChildren().clear(); 
	            	labelVBox.getChildren().addAll(abcd,typeBookLabel,titleBookLabel,authorBookLabel,yearPublishLabel); 
	            	textFieldVBox.getChildren().clear(); 
	            	textFieldVBox.getChildren().addAll(abcd1,abcdeghBox,abcd2,titleTextField,authorTextField,yearPublishTextField); 
	            }
	        });
	        
	        typeEBookButton.setOnAction(e -> {
	            if (typeEBookButton.isSelected()) {
	            	isPressed = 1;
	            	labelVBox.getChildren().clear(); 
	            	labelVBox.getChildren().addAll(abcd,typeBookLabel,titleBookLabel,authorBookLabel,yearPublishLabel,fileSizeLabel,fileFormatLabel); 
	            	textFieldVBox.getChildren().clear(); 
	            	textFieldVBox.getChildren().addAll(abcd1,abcdeghBox,abcd2,titleTextField,authorTextField,yearPublishTextField,fileSizeTextField,fileFormatTextField); 
	            }
	        });
     
	        

	        addBookButton.setOnAction(e -> {
	        	
	        	
	        	if(isPressed == 0) {
		        	try {
		        		Book newBook = new Book( titleTextField.getText(), authorTextField.getText(),  Integer.parseInt(yearPublishTextField.getText()));
			        	Library library = new Library(databaseFilePath);
			          	  library.addBook(newBook);
			            } catch (Exception ex) {
			                System.err.println("Error adding book: " + ex.getMessage());
			                ex.printStackTrace(); 
			            }
	        	} else {
		        	try {
			        	EBook newEBook = new EBook(titleTextField.getText(), authorTextField.getText(),  Integer.parseInt(yearPublishTextField.getText()), fileFormatTextField.getText(), Double.parseDouble(fileSizeTextField.getText()));
			        	Library library = new Library(databaseFilePath);
			          	  library.addBook(newEBook);
			            } catch (Exception ex) {
			                System.err.println("Error adding book: " + ex.getMessage());
			                ex.printStackTrace(); 
			            }
				}
	        	

	        });
	        
	    	VBox abc123deg = new VBox();
	    	abc123deg.setPrefSize(20, 20);
	        
    	VBox boxAddBook = new VBox(0);
	    boxAddBook.getChildren().addAll(titleBox,centerBox,abc123deg,addBookVvBox);
	    boxAddBook.setAlignment(Pos.TOP_CENTER);
    	return boxAddBook;

    	
    }
}
