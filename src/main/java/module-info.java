module de.lubowiecki {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.lubowiecki to javafx.fxml;
    exports de.lubowiecki;
}
