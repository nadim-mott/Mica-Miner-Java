module micaminer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens micaminer to javafx.fxml;
    exports micaminer;
}
