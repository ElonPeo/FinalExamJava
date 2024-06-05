package application.Display;
import application.BasicClass.Book;
import application.BasicClass.EBook;
import application.BasicClass.Library;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DisplayShowBook extends Application {
    private Library library;
    private ListView<Book> bookListView;
    private TextField titleRemoveTextField; 
    private TextField titleEditTextField;
    private TextField authorEditTextField;
    private TextField yearPublishTextField;
    private TextField fileSizeTextField;
    private TextField fileFormatTextField;

    private HBox BookBoxInfor (String title, String author, int YearPublish, String fileFormat, double fileSize, boolean isTabbar) {
        boolean[] isEdit = {true};
        titleEditTextField = new TextField(title);
        authorEditTextField = new TextField(author);
        yearPublishTextField = new TextField(String.valueOf(YearPublish)); 
        fileSizeTextField = new TextField(fileSize >= 0 ? String.valueOf(fileSize) : "");
        fileFormatTextField = new TextField(fileFormat);

        Text bookTitleInforText = new Text(title);
    	VBox bookTitleInforBox = new VBox();
      	bookTitleInforBox.setPrefWidth(350);
    	bookTitleInforBox.setAlignment(Pos.CENTER);
    	bookTitleInforBox.getChildren().addAll(bookTitleInforText);
    	bookTitleInforBox.setStyle("-fx-background-color: rgb(211, 118, 118);");
    	
    	Text bookAuthorInforText = new Text(author);
    	VBox bookAuthorInforBox = new VBox();
    	bookAuthorInforBox.setPrefWidth(150);
    	bookAuthorInforBox.setAlignment(Pos.CENTER);
    	bookAuthorInforBox.getChildren().addAll(bookAuthorInforText);
    	bookAuthorInforBox.setStyle("-fx-background-color: rgb(235, 196, 159);");
    	
    	Text bookYearPublishInforText = new Text(YearPublish == 0 ? "Year Publish" : String.valueOf(YearPublish));
    	VBox bookYearPublishInforBox = new VBox();
    	bookYearPublishInforBox.setPrefWidth(100);
    	bookYearPublishInforBox.setAlignment(Pos.CENTER);
    	bookYearPublishInforBox.getChildren().addAll(bookYearPublishInforText);
    	bookYearPublishInforBox.setStyle("-fx-background-color: rgb(241, 239, 153);");
    	
    	Text bookFileFormatInforText = new Text(fileFormat);
    	VBox bookFileFormatInforBox = new VBox();
    	bookFileFormatInforBox.setPrefWidth(100);
    	bookFileFormatInforBox.setAlignment(Pos.CENTER);
    	bookFileFormatInforBox.getChildren().addAll(bookFileFormatInforText);
    	bookFileFormatInforBox.setStyle("-fx-background-color: rgb(200, 100, 250);");

    	Text bookFileSizeInforText = new Text(fileSize == -100 ? "File size" :(fileSize == 0 ? "N/A" : String.valueOf(fileSize)));
    	VBox bookFileSizeInforBox = new VBox();

    	bookFileSizeInforBox.setPrefWidth(80);
    	bookFileSizeInforBox.setAlignment(Pos.CENTER);
    	bookFileSizeInforBox.getChildren().addAll(bookFileSizeInforText);
    	bookFileSizeInforBox.setStyle("-fx-background-color: rgb(254, 253, 237);");
    	
        
        Image editImage = new Image("/assets/edit.png");
        ImageView editimg = new ImageView(editImage);
        editimg.setFitWidth(20);
        editimg.setFitHeight(20);
        
        VBox editVBox = new VBox();
        editVBox.setMaxWidth(30); 
        editVBox.setPrefHeight(20);
        editVBox.setAlignment(Pos.CENTER);
        editVBox.getChildren().addAll(editimg);

        



        Image bookImage = new Image("/assets/delete.png");
        ImageView delImageView = new ImageView(bookImage);
        delImageView.setFitWidth(30);
        delImageView.setFitHeight(20);
        delImageView.setPreserveRatio(true);
        VBox delVBox = new VBox();
        delVBox.setPrefWidth(30);
        delVBox.setPrefHeight(20);
        // delVBox.setStyle( "-fx-background-color: rgb(255, 0, 0);");
        delVBox.setAlignment(Pos.CENTER);
        delVBox.getChildren().addAll(delImageView);



        delVBox.setOnMouseClicked(event -> {
            try {
                library.removeBook(bookTitleInforText.getText());
                reloadBooks();
            } catch (Exception ex) {
                System.err.println("Lỗi khi xoá sách: " + ex.getMessage());
                ex.printStackTrace();
            }
        });


        editVBox.setOnMouseEntered(event -> {
            editVBox.setStyle("-fx-background-color: rgb(255, 255, 255);");
        });
        editVBox.setOnMouseExited(event -> {
            editVBox.setStyle("-fx-background-color: rgb(90, 114, 160);");
        });

        delVBox.setOnMouseEntered(event -> {
            delVBox.setStyle("-fx-background-color: rgb(255, 255, 255);");
        });
        delVBox.setOnMouseExited(event -> {
            delVBox.setStyle("-fx-background-color: rgb(90, 114, 160);");
        });

        VBox coBox = new VBox();
        coBox.setPrefWidth(30);
        coBox.setPrefHeight(20);
        VBox coBox2 = new VBox();
        coBox2.setPrefWidth(30);
        coBox2.setPrefHeight(20);
        VBox coBox3 = new VBox();
        coBox3.setPrefWidth(30);
        coBox3.setPrefHeight(20);

        editVBox.setOnMouseClicked(event -> {
            if(isEdit[0] && isTabbar == false) {
                editimg.setImage(new Image("/assets/save.png"));
                if(!(fileFormat == "N/A" || fileSize == -100)){
                    bookFileSizeInforBox.getChildren().clear();
                    bookFileSizeInforBox.getChildren().addAll(fileSizeTextField);
                    bookFileFormatInforBox.getChildren().clear();
                    bookFileFormatInforBox.getChildren().addAll(fileFormatTextField);
                }
                bookTitleInforBox.getChildren().clear();
                bookTitleInforBox.getChildren().addAll(titleEditTextField);
                bookAuthorInforBox.getChildren().clear();
                bookAuthorInforBox.getChildren().addAll(authorEditTextField);
                bookYearPublishInforBox.getChildren().clear();
                bookYearPublishInforBox.getChildren().addAll(yearPublishTextField);

            } else {
                editimg.setImage(new Image("/assets/edit.png"));

                if(!(fileFormat == "N/A" || fileSize == -100)){
                    bookFileSizeInforBox.getChildren().clear();
                    bookFileSizeInforBox.getChildren().addAll(bookFileSizeInforText);
                    bookFileFormatInforBox.getChildren().clear();
                    bookFileFormatInforBox.getChildren().addAll(bookFileFormatInforText);
                    try {
                        EBook newEBook = new EBook(titleEditTextField.getText(),authorEditTextField.getText(),Integer.parseInt(yearPublishTextField.getText()),fileFormatTextField.getText(), Double.parseDouble(fileSizeTextField.getText()));
                        Library library = new Library("path_to_your_database_file");
                        library.editBook(title, newEBook);
                        reloadBooks();
                    } catch (Exception ex) {
                        System.err.println("Lỗi khi sửa: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else{
                    try {
                        Book newBook = new Book(titleEditTextField.getText(),authorEditTextField.getText(),Integer.parseInt(yearPublishTextField.getText()));
                        Library library = new Library("path_to_your_database_file");
                        library.editBook(title, newBook);
                        reloadBooks();
                    } catch (Exception ex) {
                        System.err.println("Lỗi khi sửa: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
                bookTitleInforBox.getChildren().clear();
                bookTitleInforBox.getChildren().addAll(bookTitleInforText);
                bookAuthorInforBox.getChildren().clear();
                bookAuthorInforBox.getChildren().addAll(bookAuthorInforText);
                bookYearPublishInforBox.getChildren().clear();
                bookYearPublishInforBox.getChildren().addAll(bookYearPublishInforText);
            }
            isEdit[0] = !isEdit[0]; 
            
        });
        
    	HBox bookInfor = new HBox(0);
    	bookInfor.getChildren().addAll(bookTitleInforBox,bookAuthorInforBox,bookYearPublishInforBox,bookFileSizeInforBox,bookFileFormatInforBox, isTabbar ? coBox : editVBox, isTabbar ? coBox2 : delVBox  );
        bookInfor.setPrefHeight(30);
        bookInfor.setAlignment(Pos.CENTER_LEFT);
        bookInfor.setStyle("-fx-background-color: rgb(90, 114, 160);");




    	return bookInfor;
    }
    
    public DisplayShowBook() {	
        this.library = new Library("path_to_your_database_file");
        this.bookListView = new ListView<>();
    }

   
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = showBook();
        Scene scene = new Scene(root, 900, 500);
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public VBox showBook() {
        VBox boxShowBook = new VBox(0);
        Text titleTextShowBook = new Text("List of Books");
        titleTextShowBook.setStyle("-fx-font-family: Arial; -fx-font-size: 20px; -fx-font-weight: bold;");

        VBox titleBox = new VBox(0);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefSize(850, 80);
        
        titleBox.getChildren().add(titleTextShowBook);
        titleBox.setStyle("-fx-background-color: rgb(176, 197, 164);");




        
        bookListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Book item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (item instanceof EBook) {
                        EBook eBook = (EBook) item;
                        HBox b = BookBoxInfor(eBook.getTitle(), eBook.getAuthor(), eBook.getYearPublished(), eBook.getFileFormat(), eBook.getFileSize(), false);
                        setGraphic(b);
                    } else {
                        HBox a = BookBoxInfor(item.getTitle(), item.getAuthor(), item.getYearPublished(), "N/A", 0, false);
                        setGraphic(a);
                    }
                }
            }
        });
        

        reloadBooks();
        
        
    	titleRemoveTextField = new TextField();
        HBox removeBookBox = new HBox(10);
        removeBookBox.setPrefHeight(30);
        removeBookBox.setAlignment(Pos.CENTER);
    	Label titleRemoveBookLabel = new Label("Enter title to remove book: ");
    	Button removeBookButton = new Button("Remove Book");
    	removeBookButton.setOnAction(e -> {
          try {
      		library.removeBook(titleRemoveTextField.getText());
      		reloadBooks();
        } catch (Exception ex) {
            System.err.println("Error removing book: " + ex.getMessage());
            ex.printStackTrace(); 
        }
	    });
        removeBookBox.getChildren().addAll(titleRemoveBookLabel,titleRemoveTextField,removeBookButton);

        
        HBox aKJH = BookBoxInfor("Title", "Author", 0, "File Format", -100,true);
        HBox titleBookBoxInfor = new HBox();
        HBox aksdUG = new HBox();
        aksdUG.setPrefWidth(9);
//        VBox blackBox = new VBox();
//        blackBox.setPrefHeight(2);
//        blackBox.setPrefWidth(850);
        titleBookBoxInfor.getChildren().addAll(aksdUG,aKJH);
        titleBookBoxInfor.setStyle("-fx-background-color: rgb(200, 200, 200);");
        boxShowBook.getChildren().addAll(titleBox,titleBookBoxInfor, bookListView,removeBookBox);
        return boxShowBook;
    }

    public void reloadBooks() {
        Platform.runLater(() -> {
            try {
                ObservableList<Book> bookList = FXCollections.observableArrayList(library.loadBooks());
                bookListView.setItems(bookList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}


