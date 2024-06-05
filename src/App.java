
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import application.Style.menuButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.BasicClass.Account;
import application.BasicClass.AccountManager;
import application.Display.DisplayAccount;
import application.Display.DisplayAddBook;
import application.Display.DisplayBorrowNReturn;
import application.Display.DisplayShowBook;
    

public class App extends Application {
    int isPressed = 0;
    private VBox display;
    @Override
    public void start(Stage primaryStage) {
    // bool isLogin = false;


// Create the main layout VBox
    display = new VBox();
    display.setAlignment(Pos.CENTER);


    Text titleTextMenu = new Text("Menu");
    titleTextMenu.setStyle(
            "-fx-font-family: Arial; "
            + "-fx-font-size: 20px; "
            + "-fx-font-weight: bold; "
            + "-fx-fill: white;" 
    );
    
    VBox titleBox = new VBox(10);
    titleBox.setPrefWidth(150); 
    titleBox.setPrefHeight(60);
    titleBox.setAlignment(Pos.CENTER);
    titleBox.getChildren().addAll(titleTextMenu);
    titleBox.setStyle( "-fx-background-color: rgb(255, 143, 0);");

    
    // List button menu
    menuButton bookShowBox = new menuButton("Show books", "/assets/search.png");
    HBox showBookVBox = bookShowBox.getBookDisplayVBox();

    menuButton brNRtBox = new menuButton("Br & Rt", "/assets/digital-library.png");
    HBox brNRtHBox = brNRtBox.getBookDisplayVBox();

    menuButton addBook = new menuButton("Add books", "/assets/book.png");
    HBox addHBox = addBook.getBookDisplayVBox();

    // menuButton editNRm = new menuButton("Edit & Remove", "/assets/edit.png");
    // HBox editNRmHBox = editNRm.getBookDisplayVBox();

    menuButton accountButton = new menuButton("Account", "/assets/id-card.png");
    HBox accountHbox = accountButton.getBookDisplayVBox();

    // menuButton bookShow = new menuButton("Book Title", "file:C:/Users/Admin/OneDrive/Desktop/l_ex_java/project_java/src/assets/search.png");
    // HBox showBookVBox = bookShow.getBookDisplayVBox();

    VBox listButtonBox = new VBox();
    listButtonBox.setPrefWidth(150);
    listButtonBox.setPrefHeight(440);
    listButtonBox.setAlignment(Pos.TOP_CENTER);
    listButtonBox.getChildren().addAll(showBookVBox, brNRtHBox, addHBox,accountHbox);
    listButtonBox.setStyle("-fx-background-color: rgb(55, 58, 64);");
    
    VBox menu = new VBox();
    menu.setPrefWidth(150); 
    menu.setPrefHeight(500);
    menu.getChildren().addAll(titleBox,listButtonBox);
    
    
    DisplayShowBook displayShowBook = new DisplayShowBook();
    VBox showBookBox = displayShowBook.showBook();
    DisplayAddBook displayAddBook = new DisplayAddBook(); 
    VBox addBookBox = displayAddBook.addBook("path_to_your_database_file"); 
    DisplayBorrowNReturn displayBrNRtBook = new DisplayBorrowNReturn(); 
    VBox BrNRtBox = displayBrNRtBook.BookBoxInfor("path_to_your_database_file", "123");
    
    DisplayAccount displayAccount = new DisplayAccount("None", "None");
    VBox accountBox = displayAccount.getBookDisplayVBox();


    HBox mainBox = new HBox();
    mainBox.getChildren().addAll(menu,showBookBox);


    showBookVBox.setOnMouseClicked(event -> {
        displayShowBook.reloadBooks();
        mainBox.getChildren().clear(); 
        mainBox.getChildren().addAll(menu, showBookBox); 
    });

    brNRtHBox.setOnMouseClicked(event -> {
        mainBox.getChildren().clear(); 
        mainBox.getChildren().addAll(menu, BrNRtBox); 
    });

    addHBox.setOnMouseClicked(event -> {
        mainBox.getChildren().clear(); 
        mainBox.getChildren().addAll(menu, addBookBox); 
    });
        
    accountHbox.setOnMouseClicked(event -> {
        mainBox.getChildren().clear(); 
        mainBox.getChildren().addAll(menu, accountBox); 
    });
        
    Label lblTitle = new Label("Pluto");
    lblTitle.setFont(new Font("Arial", 40));
    lblTitle.setTextFill(Color.WHITE);

    Label lblSubtitle = new Label("Planet Nine");
    lblSubtitle.setFont(new Font("Arial", 24));
    lblSubtitle.setTextFill(Color.WHITE);

    VBox brandingBox = new VBox(10, lblTitle, lblSubtitle);
    brandingBox.setAlignment(Pos.CENTER);
    brandingBox.setPadding(new Insets(50, 20, 20, 20));
    brandingBox.setStyle("-fx-background-color: #ff4b4b;");

    Label lblLoginTitle = new Label("Secure Login");
    lblLoginTitle.setFont(new Font("Arial", 20));
    lblLoginTitle.setAlignment(Pos.CENTER_RIGHT);

    Label lblUsername = new Label("Username:");
    TextField txtUsername = new TextField();
    txtUsername.setPromptText("Enter your username");
    txtUsername.setStyle("-fx-border-color: lightblue; -fx-border-width: 2px; -fx-border-radius: 5px;");
    txtUsername.setPrefWidth(200);

    Label lblPassword = new Label("Password:");
    PasswordField txtPassword = new PasswordField();
    txtPassword.setPromptText("Password");
    txtPassword.setStyle("-fx-border-color: lightgrey; -fx-border-width: 2px; -fx-border-radius: 5px;");
    txtPassword.setPrefWidth(200);

    Button btnLogin = new Button("Login");
    btnLogin.setStyle("-fx-background-color: #ff4b4b; -fx-text-fill: white; -fx-font-size: 16px;");
    btnLogin.setPrefWidth(200);

    Button btnCancel = new Button("Cancel");
    btnCancel.setStyle("-fx-background-color: #ff4b4b; -fx-text-fill: white; -fx-font-size: 16px;");
    btnCancel.setPrefWidth(200);


    Button SighUp = new Button("Sigh Up");
    SighUp.setStyle("-fx-background-color: #ff4b4b; -fx-text-fill: white; -fx-font-size: 16px;");
    SighUp.setPrefWidth(200);

    VBox loginBox = new VBox(10, lblLoginTitle, lblUsername, txtUsername, lblPassword, txtPassword, btnLogin,SighUp , btnCancel);
    loginBox.setAlignment(Pos.CENTER_LEFT);
    loginBox.setPadding(new Insets(20));
    


    Label lblSighInUsername = new Label("Username:");
    TextField txtSighInUsername = new TextField();
    txtSighInUsername.setPromptText("Enter your username");
    txtSighInUsername.setStyle("-fx-border-color: lightblue; -fx-border-width: 2px; -fx-border-radius: 5px;");
    txtSighInUsername.setPrefWidth(200);

    Label lblSighInPassword = new Label("Password:");
    TextField txtSighInPassword = new TextField();
    txtSighInPassword.setPromptText("Enter your password");
    txtSighInPassword.setStyle("-fx-border-color: lightblue; -fx-border-width: 2px; -fx-border-radius: 5px;");
    txtSighInPassword.setPrefWidth(200);

    Label lblSighInName = new Label("Name:");
    TextField txtSighInName = new TextField();
    txtSighInName.setPromptText("Enter your Name");
    txtSighInName.setStyle("-fx-border-color: lightblue; -fx-border-width: 2px; -fx-border-radius: 5px;");
    txtSighInName.setPrefWidth(200);



    Label lblSighID = new Label("ID:");
    TextField txtSighID = new TextField();
    txtSighID.setPromptText("Enter your ID");
    txtSighID.setStyle("-fx-border-color: lightblue; -fx-border-width: 2px; -fx-border-radius: 5px;");
    txtSighID.setPrefWidth(200);




    Label lblSighInTitle = new Label("Secure Login");
    lblSighInTitle.setFont(new Font("Arial", 20));
    lblSighInTitle.setAlignment(Pos.CENTER_RIGHT);

    Button btnSighUp = new Button("Sigh Up");
    btnSighUp.setStyle("-fx-background-color: #ff4b4b; -fx-text-fill: white; -fx-font-size: 16px;");
    btnSighUp.setPrefWidth(200);

    Button btnCancelSighUp = new Button("Cancel");
    btnCancelSighUp.setStyle("-fx-background-color: #ff4b4b; -fx-text-fill: white; -fx-font-size: 16px;");
    btnCancelSighUp.setPrefWidth(200);

    VBox sighInBox1 = new VBox(10, lblSighInTitle, lblSighInUsername, txtSighInUsername, lblSighInPassword, txtSighInPassword, lblSighInName, 
    txtSighInName, lblSighID, txtSighID, btnSighUp, btnCancelSighUp
    );
    sighInBox1.setAlignment(Pos.CENTER_LEFT);
    sighInBox1.setPadding(new Insets(20));


    
    Image PheImage = new Image("/assets/phe.png");
    ImageView PheImageView = new ImageView(PheImage);
    PheImageView.setFitWidth(460);
    PheImageView.setFitHeight(500);

    HBox imgBox = new HBox();
    imgBox.setPrefHeight(500);
    imgBox.setPrefWidth(460);
    // imgBox.setStyle("-fx-background-color: #ff4b4b");
    imgBox.setAlignment(Pos.CENTER);
    imgBox.getChildren().addAll(PheImageView);
    PheImageView.setPreserveRatio(true);

    HBox loginScreen = new HBox();
    imgBox.setPrefHeight(500);
    imgBox.setPrefWidth(450);
    imgBox.setAlignment(Pos.CENTER);
    // imgBox.setStyle("-fx-background-color: #ff4b4b");
    loginScreen.getChildren().addAll(imgBox,loginBox); 

    HBox login = new HBox();
    login.setAlignment(Pos.CENTER);
    login.getChildren().addAll(loginScreen);
    // Add title text
    display.getChildren().addAll(loginScreen);
    



    AccountManager accountManager = new AccountManager("accounts.dat");

    btnCancelSighUp.setOnAction(event -> {
        loginScreen.getChildren().clear(); 
        loginScreen.getChildren().addAll(imgBox,loginBox); 
    });
    SighUp.setOnAction(event -> {
        loginScreen.getChildren().clear(); 
        loginScreen.getChildren().addAll(imgBox,sighInBox1); 
    });

    btnSighUp.setOnAction(event -> {
        try {
            boolean isAuthenticated = accountManager.authenticate(txtSighInUsername.getText(), txtSighInPassword.getText());
            if (isAuthenticated) {
                System.out.println("Account already exists");
            } else {
                Account ex = new Account(txtSighInUsername.getText(), txtSighInPassword.getText(), txtSighInName.getText(), txtSighID.getText());
                accountManager.addAccount(ex);
                loginScreen.getChildren().clear(); 
                loginScreen.getChildren().addAll(imgBox,loginBox); 

                System.out.println("Successful");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    });

btnLogin.setOnAction(event -> {
    try {
        boolean isAuthenticated = accountManager.authenticate(txtUsername.getText(), txtPassword.getText());
        if (isAuthenticated) {
            Account a = accountManager.searchAccountByUsername(txtUsername.getText());
            DisplayAccount displayAccountLogin = new DisplayAccount(a.getName(),a.getId());
            VBox accountBoxLogin = displayAccountLogin.getBookDisplayVBox();
            mainBox.getChildren().clear(); 
            mainBox.getChildren().addAll(menu, accountBoxLogin);
            display.getChildren().clear(); 
            display.getChildren().addAll(mainBox);
        } else {
            System.out.println("False");
        }
    } catch (IOException e) {
        System.out.println("An error occurred: " + e.getMessage());
        e.printStackTrace();
    }
});


    
    // Set up the scene
    Scene scene = new Scene(display, 1000, 500);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Library Management System");
    primaryStage.setResizable(false);
    primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }
}
