package de.lubowiecki;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {

    @FXML // Kommuniziert mit der GUI
    private TextField input;

    @FXML
    private Label output;

    private StringBuilder buffer = new StringBuilder();

    @FXML
    public void click() {
        String text = input.getText(); // TextFeld auslesen
        buffer.append("Hi ").append(text).append("!\n"); // Text-Speicher erweitern
        output.setText(buffer.toString()); // Text-Speicher in das Label ausgeben
        input.clear(); // TextFeld leeren
    }
}
