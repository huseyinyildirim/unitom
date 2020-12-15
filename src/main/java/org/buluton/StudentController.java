package org.buluton;

import java.io.IOException;
import javafx.fxml.FXML;

public class StudentController {

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
