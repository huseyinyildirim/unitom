module org.buluton {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires org.hibernate.orm.core;
    requires javafx.graphics;
    requires javax.persistence;

    opens org.buluton to javafx.fxml;
    exports org.buluton;
}