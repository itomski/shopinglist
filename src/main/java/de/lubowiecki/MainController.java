package de.lubowiecki;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

// Initializable = beim Inbetriebnehmen wird die initialize_methode automatisch ausgeführt
public class MainController implements Initializable {

    // user.home = Benutzerordner
    private static final String FILE = System.getProperty("user.home") + "/shoppinglist.ser";

    @FXML // Kommuniziert mit der GUI
    private TextField name;

    @FXML
    private TextField count;

    @FXML
    private TextField description;

    @FXML
    private ListView<Item> output;

    private List<Item> items = new ArrayList<>();

    private Predicate<Item> filter;

    @FXML
    public void save() {
        String name = this.name.getText(); // TextFeld auslesen
        String count = this.count.getText();
        String description = this.description.getText();

        Item item = new Item(name); // TODO: Validierung
        try {
            item.setCount(Integer.parseInt(count)); // TODO: Validierung
        }
        catch (NumberFormatException e) {
            item.setCount(1);
        }
        item.setDescription(description); // TODO: Validierung

        items.add(item);

        try {
            saveToFile();
        }
        catch (IOException e) {
            // TODO: Meldung ausgeben...
        }

        clearFields();
        updateOutput();
    }

    public void filter(ActionEvent event) {

        String btnText = ((Button)event.getSource()).getText();
        switch (btnText) {
            case "alle":
                filter = null;
                break;

            case "offen":
                filter = i -> !i.isDone();
                break;

            case "erledigt":
                filter = i -> i.isDone();
                break;
        }
        updateOutput();
    }


    private void updateOutput() {
        var items = FXCollections.observableList(this.items);

        if(filter == null) {
            output.setItems(items);
        }
        else {
            output.setItems(new FilteredList<Item>(items, filter));
        }
    }

    private void clearFields() {
        name.clear();
        count.clear();
        description.clear();
    }

    private void saveToFile() throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(items);
        }
    }

    public void checkKey(KeyEvent event) {
        switch (event.getCode()) {
            case BACK_SPACE: delete();
                break;
            case SPACE: toggleDone();
                break;
        }
    }

    private void toggleDone() {
        Item item = output.getSelectionModel().getSelectedItem(); // Was wurde ausgewählt
        if(item != null) {
            item.toggleDone();
            try {
                saveToFile();
                updateOutput();
            }
            catch (IOException e) {
                // TODO: Meldung ausgeben...
            }
        }
    }

    private void delete() {
        Item item = output.getSelectionModel().getSelectedItem(); // Was wurde ausgewählt
        if(item != null) {
            items.remove(item);
            try {
                saveToFile();
                updateOutput();
            }
            catch (IOException e) {
                // TODO: Meldung ausgeben...
            }
        }
    }

    private void loadFromFile() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            items = (List<Item>) in.readObject();
        }
        catch(Exception e) {
            // TODO: Auf verschiedene Exceptions eingehen
            e.printStackTrace();
            items = new ArrayList<>();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFromFile();
        updateOutput();
    }
}
