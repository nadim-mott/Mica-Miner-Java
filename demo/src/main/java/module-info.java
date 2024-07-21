module micaminer {
    requires javafx.controls;
    requires javafx.fxml;

    opens micaminer to javafx.fxml;
    exports micaminer;
}
