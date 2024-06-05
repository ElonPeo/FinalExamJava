package application.Style;

import javafx.scene.text.Text;

public class fontText { 
    private Text text;

    public fontText(String content) {
        this.text = new Text(content);
        this.text.setStyle(
            "-fx-font-family: Arial; " +
            "-fx-font-size: 15px; " +
            "-fx-fill: black;"
        );
    }

    // Method to get the Text object
    public Text getText() {
        return text;
    }
}
