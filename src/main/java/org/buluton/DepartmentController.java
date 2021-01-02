package org.buluton;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.buluton.Models.TblDepartments;
import org.buluton.Repo.DepartmentJPA;

import java.io.IOException;
import java.util.Optional;

public class DepartmentController {

    private static DepartmentJPA jpa = null;
    private Alert alert;

    @FXML
    public void initialize() {
        jpa = new DepartmentJPA();
    }

    @FXML
    private Button btnStudent, btnStaff, btnDepartment;

    @FXML
    private TableView<TblDepartments> tableList;
    @FXML
    private TableColumn<TblDepartments, Integer> tcId;
    @FXML
    private TableColumn<TblDepartments, String> tcName;
    @FXML
    private TextField txtName;
    @FXML
    private Button btnUpdate, btnCreate, btnDelete, btnClear;

    @FXML
    private void btnCreate() throws IOException {

        String name = txtName.getText();

        jpa.create(0, name);

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Yeni Kayıt");
        alert.setContentText("Departman başarıyla kayıt edildi!");
        alert.show();

        txtName.setText("");
    }

    @FXML
    private void btnUpdate() throws IOException {

        int id = tableList.getSelectionModel().getSelectedItem().getId();
        String name = txtName.getText();

        TblDepartments department = jpa.read(id);

        if(department != null) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Güncelleme");
            alert.setContentText("Departman kayıdı güncellensin mi?");
            Optional<ButtonType> alertResult = alert.showAndWait();

            if (alertResult.get() == ButtonType.OK){
                jpa.update(id, name);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Güncelleme");
                alert.setContentText("Departman başarıyla güncellendi!");
                alert.show();
            }
        }
    }

    @FXML
    private void btnDelete() throws IOException {
        int id = tableList.getSelectionModel().getSelectedItem().getId();

        if(jpa.control(id) == null) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bilgi");
            alert.setContentText("Silinecek departman bulunamadı!");
            alert.show();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Silme");
            alert.setContentText("Departman kayıdı silinsin mi?");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK) {
                jpa.delete(id);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Silme");
                alert.setContentText("Departman başarıyla silindi!");
                alert.show();

                txtName.setText("");
            }
        }
    }

    @FXML
    public void tableClick(MouseEvent event)
    {
        if (event.getClickCount() == 2)
        {
            int id = tableList.getSelectionModel().getSelectedItem().getId();

            TblDepartments department = jpa.read(id);

            if(department != null) {
                txtName.setText(department.getName());
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
        txtName.setText("");
    }
}