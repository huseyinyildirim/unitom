package org.buluton;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class StudentController {

    @FXML
    private Button btnStudent, btnStaff, btnDepartment;

    @FXML
    private ComboBox cbDepartment;
    @FXML
    private TextField txtStudentNo, txtName, txtSurname;
    @FXML
    private Button btnUpdate, btnSave, btnDelete;

    @FXML
    private void btnSwitchToStudent() throws IOException {
        App.setRoot("student");
    }

    @FXML
    private void btnSwitchToStaff() throws IOException {
        App.setRoot("staff");
    }

    @FXML
    private void btnSwitchToDepartment() throws IOException {
        App.setRoot("department");
    }

    @FXML
    private void btnClose() throws IOException {
        System.exit(0);
    }
}
