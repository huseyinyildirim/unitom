package org.buluton;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import org.buluton.Models.TblDepartments;
import org.buluton.Models.TblStudents;
import org.buluton.Repo.DepartmentJPA;
import org.buluton.Repo.StudentJPA;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class StudentController {

    private static StudentJPA jpa = null;
    private Alert alert;

    @FXML
    public void initialize() {
        jpa = new StudentJPA();

        DepartmentJPA departmentJPA = new DepartmentJPA();
        List<TblDepartments> departmentsList = departmentJPA.readAll();

        for (TblDepartments tblDepartments : departmentsList) {
            int id = tblDepartments.getId();
            String name = tblDepartments.getName();

            org.buluton.Models.ComboBox cb = new org.buluton.Models.ComboBox(id, name);

            cbDepartment.setValue(cb);
        }
    }

    @FXML
    private Button btnStudent, btnStaff, btnDepartment;
    @FXML
    private TableView<TblStudents> tableList;
    @FXML
    private TableColumn<TblStudents, Integer> tcId;
    @FXML
    private TableColumn<TblStudents, String> tcIdentityNo, tcStudentNo, tcName, tcSurname;
    @FXML
    private ComboBox<org.buluton.Models.ComboBox> cbDepartment;
    @FXML
    private TextField txtStudentNo, txtIdentityNo, txtName, txtSurname;
    @FXML
    private Button btnUpdate, btnCreate, btnDelete, btnClear;

    @FXML
    private void btnCreate() throws IOException {

        int departmentId = Integer.parseInt(cbDepartment.getId());
        String studentNo = txtStudentNo.getText();
        String identityNo = txtIdentityNo.getText();
        String name = txtName.getText();
        String surname = txtSurname.getText();

        jpa.create(0, departmentId, studentNo, identityNo, name, surname);

        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Yeni Kayıt");
        alert.setContentText("Öğrenci başarıyla kayıt edildi!");
        alert.show();

        txtIdentityNo.setText("");
        txtStudentNo.setText("");
        txtName.setText("");
        txtSurname.setText("");
    }

    @FXML
    private void btnUpdate() throws IOException {

        int id = tableList.getSelectionModel().getSelectedItem().getId();
        int departmentId = Integer.parseInt(cbDepartment.getId());
        String studentNo = txtStudentNo.getText();
        String identityNo = txtIdentityNo.getText();
        String name = txtName.getText();
        String surname = txtSurname.getText();

        TblStudents student = jpa.read(id);

        if(student != null) {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Güncelleme");
            alert.setContentText("Öğrenci kayıdı güncellensin mi?");
            Optional<ButtonType> alertResult = alert.showAndWait();

            if (alertResult.get() == ButtonType.OK){
                jpa.update(id, departmentId, studentNo, identityNo, name,surname);

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Güncelleme");
                alert.setContentText("Öğrenci başarıyla güncellendi!");
                alert.show();
            }
        }
    }

    @FXML
    private void btnDelete() throws IOException {
        int id = tableList.getSelectionModel().getSelectedItem().getId();

        if(jpa.control(id) == null) {
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Bilgi");
            alert.setContentText("Silinecek öğrenci bulunamadı!");
            alert.show();
        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Silme");
            alert.setContentText("Öğrenci kayıdı silinsin mi?");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK) {
                jpa.delete(id);

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Silme");
                alert.setContentText("Öğrenci başarıyla silindi!");
                alert.show();

                txtIdentityNo.setText("");
                txtStudentNo.setText("");
                txtName.setText("");
                txtSurname.setText("");
            }
        }
    }

    @FXML
    public void tableClick(MouseEvent event)
    {
        if (event.getClickCount() == 2)
        {
            int id = tableList.getSelectionModel().getSelectedItem().getId();

            TblStudents student = jpa.read(id);

            if(student != null) {
                cbDepartment.getSelectionModel().select(student.getDepartmentId());
                txtStudentNo.setText(student.getStudentNo());
                txtIdentityNo.setText(student.getIdentityNo());
                txtName.setText(student.getName());
                txtSurname.setText(student.getSurname());
            }
        }
    }

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

    @FXML
    private void btnClear() throws IOException {
        txtIdentityNo.setText("");
        txtStudentNo.setText("");
        txtName.setText("");
        txtSurname.setText("");
    }
}
