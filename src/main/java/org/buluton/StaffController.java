package org.buluton;

import java.io.IOException;
import javafx.fxml.FXML;

public class StaffController {

    @FXML
    private void switchToStudent() throws IOException {
        App.setRoot("student");
    }

    @FXML
    private void switchToStaff() throws IOException {
        App.setRoot("staff");
    }

    @FXML
    private void switchToDepartment() throws IOException {
        App.setRoot("department");
    }

    @FXML
    private void close() throws IOException {
        System.exit(0);
    }
}