package org.buluton;

import javafx.fxml.FXML;

import java.io.IOException;

public class DepartmentController {

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
}