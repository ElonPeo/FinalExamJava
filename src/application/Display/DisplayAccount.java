package application.Display;

import application.Style.fontText;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DisplayAccount {
    // Class members
    private VBox accountHbox;
    
    // Constructor
    public DisplayAccount(String Name , String ID) {
    Text titleTextAccount = new Text("Account"); 
        titleTextAccount.setStyle(
                "-fx-font-family: Arial; " +
                "-fx-font-size: 20px; " +
                "-fx-font-weight: bold; "
        );
    VBox titleAccountBox = new VBox(0);
    titleAccountBox.setPrefWidth(850);
    titleAccountBox.setPrefHeight(60);
    titleAccountBox.setAlignment(Pos.CENTER);
    titleAccountBox.getChildren().add(titleTextAccount);
    titleAccountBox.setStyle("-fx-background-color: rgb(200, 100, 250);");


    
    Image bookImage = new Image("file:C:/Users/Admin/OneDrive/Desktop/l_ex_java/project_java/bin/assets/student.png");
    ImageView ImageView = new ImageView(bookImage);
    ImageView.setFitWidth(60);
    ImageView.setFitHeight(80);
    ImageView.setPreserveRatio(true);
    
    HBox bookBorder = new HBox();
    bookBorder.setPrefWidth(80);
    bookBorder.setPrefHeight(100);
    bookBorder.setAlignment(Pos.CENTER);
    bookBorder.setStyle(
        "-fx-border-color: black;" + 
        "-fx-border-width: 2px;" + 
        "-fx-border-radius: 18px;" 
    );
    bookBorder.getChildren().add(ImageView);

    HBox imgInfo = new HBox(0);
    imgInfo.setPrefWidth(100);
    imgInfo.setPrefHeight(150);
    // imgInfo.setStyle("-fx-background-color: rgb(0, 0, 250);");
    imgInfo.setAlignment(Pos.CENTER);
    imgInfo.getChildren().addAll(bookBorder);
    
    fontText nameAc = new fontText("Name:" + Name);
    fontText idAc = new fontText("ID:" + ID);
    fontText phoneNumAc = new fontText("Phone Number:");
    fontText emailAc = new fontText("Email:");
    
    VBox align1 = new VBox();
    align1.setPrefWidth(15);

    VBox mainInfor = new VBox();
    mainInfor.setPrefWidth(300);
    mainInfor.setPrefHeight(150);   
    // mainInfor.setStyle("-fx-background-color: rgb(0, 0, 250);");
    mainInfor.setSpacing(10);
    mainInfor.setAlignment(Pos.CENTER_LEFT);
    mainInfor.getChildren().addAll(nameAc.getText(),idAc.getText(),phoneNumAc.getText(),emailAc.getText()); 
    
    HBox inforAccountBox = new HBox(0);
    inforAccountBox.setPrefWidth(850);
    inforAccountBox.setPrefHeight(150);
    inforAccountBox.setStyle("-fx-padding: 15px;"
    );
    inforAccountBox.setAlignment(Pos.CENTER_LEFT);
    inforAccountBox.getChildren().addAll(imgInfo,align1,mainInfor);
        // Khởi tạo accountHbox và thêm bookTitleText vào đó
    accountHbox = new VBox();
    accountHbox.getChildren().addAll(titleAccountBox,inforAccountBox);

    }

    // Getter for bookDisplayVBox
    public VBox getBookDisplayVBox() {
        return accountHbox;
    }
}
