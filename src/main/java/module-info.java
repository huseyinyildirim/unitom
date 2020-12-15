module org.buluton {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.persistence;

    opens org.buluton to javafx.fxml;
    exports org.buluton;
}