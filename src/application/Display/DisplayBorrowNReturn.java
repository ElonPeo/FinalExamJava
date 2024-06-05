package application.Display;



import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import application.BasicClass.Book;
import application.BasicClass.HistoryBrNRt;
import application.BasicClass.Library;

public class DisplayBorrowNReturn {


      private TextField titleBrTextField; 
      private TextField titleRtTextField; 
      
      public VBox BookBoxInfor(String pathDataBaseString, String NameAccBr ) {
    	  String databaseFilePath = "fileDataBrNRt";
    	  HistoryBrNRt history = new HistoryBrNRt(databaseFilePath);
    	  
    	  LocalDateTime currentDateTime = LocalDateTime.now();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	  String dateString = currentDateTime.format(formatter);
    	  
    	  
      Text titleTextBrNRt = new Text("Borrow and Return"); 
      titleTextBrNRt.setStyle(
              "-fx-font-family: Arial; " +
              "-fx-font-size: 20px; " +
              "-fx-font-weight: bold; "
      );
      VBox titleBrNRtBox = new VBox(0);
      titleBrNRtBox.setPrefWidth(850);
      titleBrNRtBox.setPrefHeight(60);
      titleBrNRtBox.setAlignment(Pos.CENTER);
      titleBrNRtBox.getChildren().add(titleTextBrNRt);
      titleBrNRtBox.setStyle("-fx-background-color: rgb(200, 100, 250);");
      
      


       List<String> dataFromFile = history.readAllLinesFromFile();

	   ListView<String> listView = new ListView<>();
	   ObservableList<String> items = FXCollections.observableArrayList(dataFromFile);
	   listView.setItems(items);
	   listView.setPrefWidth(850);
	
	   HBox hisBrNRtBookBox = new HBox();
	   hisBrNRtBookBox.setPrefWidth(850);
	   hisBrNRtBookBox.setPrefHeight(250);
	   hisBrNRtBookBox.getChildren().addAll(listView);
	      
      
      
      Text titleReturnBox = new Text("Return"); 
      titleReturnBox.setStyle(
              "-fx-font-family: Arial; " +
              "-fx-font-size: 15px; " +
              "-fx-font-weight: bold; "
      );
      VBox titleReturnBoxa = new VBox(0);
      titleReturnBoxa.setPrefWidth(400);
      titleReturnBoxa.setPrefHeight(50);
      titleReturnBoxa.setAlignment(Pos.CENTER);
      titleReturnBoxa.getChildren().add(titleReturnBox);
      titleReturnBoxa.setStyle("-fx-background-color: rgb(163, 255, 214);");
      
  	  Label titleBookRtLabel = new Label("Enter the book title: ");
  	  titleRtTextField = new TextField();
  	  
  	  HBox mainRtBox = new HBox();
  	  mainRtBox.setAlignment(Pos.CENTER);
  	  mainRtBox.getChildren().addAll(titleBookRtLabel,titleRtTextField);
  	  
      VBox returnVvBox = new VBox();
      returnVvBox.setAlignment(Pos.CENTER);
      Button ReturnButton = new Button("Return");
      returnVvBox.getChildren().addAll(ReturnButton);
  	  
      VBox returnBox = new VBox(20);
      returnBox.setAlignment(Pos.TOP_CENTER);
      returnBox.getChildren().addAll(titleReturnBoxa,mainRtBox,returnVvBox);
      returnBox.setPrefWidth(400);
      returnBox.setPrefHeight(200);
      
      
      
      
      
      
      Text titleBorrowBox = new Text("Borrow"); 
      titleBorrowBox.setStyle(
              "-fx-font-family: Arial; " +
              "-fx-font-size: 15px; " +
              "-fx-font-weight: bold; "
      );
      VBox titleBorrowBoxa = new VBox(0);
      titleBorrowBoxa.setPrefWidth(400);
      titleBorrowBoxa.setPrefHeight(50);
      titleBorrowBoxa.setAlignment(Pos.CENTER);
      titleBorrowBoxa.getChildren().add(titleBorrowBox);
      titleBorrowBoxa.setStyle("-fx-background-color: rgb(163, 255, 214);");
      
  	  Label titleBookBrLabel = new Label("Enter the book title: ");
  	  titleBrTextField = new TextField();
  	  
  	  HBox mainBrBox = new HBox();
  	  mainBrBox.setAlignment(Pos.CENTER);
  	  mainBrBox.getChildren().addAll(titleBookBrLabel,titleBrTextField);
  	  
      VBox borrowVvBox = new VBox();
      borrowVvBox.setAlignment(Pos.CENTER);
      Button borrowButton = new Button("Borrow");
      borrowVvBox.getChildren().addAll(borrowButton);
  	  
      VBox borowBox = new VBox(20);
      borowBox.setAlignment(Pos.TOP_CENTER);
      borowBox.getChildren().addAll(titleBorrowBoxa,mainBrBox,borrowVvBox);
      borowBox.setPrefWidth(400);
      borowBox.setPrefHeight(200);
      
      

      
      
      
      borrowButton.setOnAction(event -> {
    	    try {
    	        Library library = new Library("path_to_your_database_file");
    	        Library libraryHistory = new Library("libraryHistory");

    	        String title = titleBrTextField.getText();
    	        Optional<Book> foundBook = library.searchBookByTitle(title);
    	        if (foundBook.isPresent()) {
    	            Book tempBook = foundBook.get();
    	            System.out.println("Book found: " + tempBook.getTitle());

    	            libraryHistory.addBook(tempBook); 
    	            library.removeBook(title); 

    	            history.saveStringToFile(title + " was borrowed at time: " + dateString);

    	            List<String> updatedData = history.readAllLinesFromFile();
    	            items.setAll(updatedData); 
    	        } else {
    	            System.out.println("Book not found.");
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	});

      ReturnButton.setOnAction(event -> {
          try {
        	  Library library = new Library("path_to_your_database_file");
  	        Library libraryHistory = new Library("libraryHistory");

  	        String title = titleBrTextField.getText();
  	        Optional<Book> foundBook = libraryHistory.searchBookByTitle(title);
  	        if (foundBook.isPresent()) {
  	            Book tempBook = foundBook.get();
  	            System.out.println("Book found: " + tempBook.getTitle());

  	            library.addBook(tempBook); 
  	          libraryHistory.removeBook(title); 

  	            history.saveStringToFile(title + " was return at time: " + dateString);

  	            List<String> updatedData = history.readAllLinesFromFile();
  	            items.setAll(updatedData); 
  	        } else {
  	            System.out.println("Book not found.");
  	        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        });
      
      HBox mainBrNRtBox = new HBox(50);
      mainBrNRtBox.getChildren().addAll(borowBox, returnBox);
      mainBrNRtBox.setPrefWidth(850);
      mainBrNRtBox.setPrefHeight(200);
      mainBrNRtBox.setStyle("-fx-background-color: rgb(200, 200, 250);");
      
      VBox BrNRtBox = new VBox();
      BrNRtBox.getChildren().addAll(titleBrNRtBox,hisBrNRtBookBox,mainBrNRtBox);
      
      return BrNRtBox;
      }
}
