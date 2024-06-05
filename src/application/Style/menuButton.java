package application.Style;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class menuButton {
    // Class members
    private HBox bookDisplayVBox;

    // Constructor
    public menuButton(String title, String imagePath) {
        // Text for book title
        Text bookTitleText = new Text(title);
        bookTitleText.setStyle(
                "-fx-font-family: Arial; " +
                "-fx-font-size: 15px; " +
                "-fx-fill: white;"
        );

        // Container for book display
        HBox bookDisplayHBox = new HBox();
        bookDisplayHBox.setPrefWidth(150);
        bookDisplayHBox.setPrefHeight(50);
        bookDisplayHBox.setAlignment(Pos.CENTER_LEFT);
        bookDisplayHBox.setStyle("-fx-background-color: rgb(55, 58, 64);");
        bookDisplayHBox.setOnMouseEntered(event -> {
            bookDisplayHBox.setStyle("-fx-background-color: rgb(100, 100, 100);");
        });
        bookDisplayHBox.setOnMouseExited(event -> {
            bookDisplayHBox.setStyle("-fx-background-color: rgb(55, 58, 64);");
        });

        // Image for book cover
        Image bookImage = new Image(imagePath);
        ImageView bookCoverImageView = new ImageView(bookImage);
        bookCoverImageView.setFitWidth(20);
        bookCoverImageView.setFitHeight(20);
        bookCoverImageView.setPreserveRatio(true);

        // Container for book cover
        VBox bookCoverVBox = new VBox();
        bookCoverVBox.setMaxWidth(30);
        bookCoverVBox.setPrefHeight(30);
        bookCoverVBox.setAlignment(Pos.CENTER);

        // Placeholders for alignment
        HBox spacer1 = new HBox();
        spacer1.setPrefWidth(10);
        HBox spacer2 = new HBox();
        spacer2.setPrefWidth(10);


        // Adding components to book display container
        bookCoverVBox.getChildren().add(bookCoverImageView);
        bookDisplayHBox.getChildren().addAll(spacer2, bookCoverVBox, spacer1, bookTitleText);

        // Assigning to class member
        bookDisplayVBox = new HBox();
        bookDisplayVBox.getChildren().add(bookDisplayHBox);
    }

    // Getter for bookDisplayVBox
    public HBox getBookDisplayVBox() {
        return bookDisplayVBox;
    }
}
