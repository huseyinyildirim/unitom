package org.buluton;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.buluton.Models.ComboBox;
import org.buluton.Models.TblDepartments;
import org.buluton.Models.TblStaffs;
import org.buluton.Repo.DepartmentJPA;
import org.buluton.Repo.StaffJPA;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class StaffController {

    private static StaffJPA jpa = null;
    private Alert alert;

    @FXML
    public void initialize() {
        jpa = new StaffJPA();

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
    private TableView<TblStaffs> tableList;
    @FXML
    private TableColumn<TblStaffs, Integer> tcId;
    @FXML
    private TableColumn<TblStaffs, String> tcIdentityNo, tcName, tcSurname;
    @FXML
    private javafx.scene.control.ComboBox<ComboBox> cbDepartment;
    @FXML
    private TextField txtIdentityNo, txtName, txtSurname;
    @FXML
    private Button btnUpdate, btnCreate, btnDelete, btnClear;

    @FXML
    private void btnCreate() throws IOException {

        int departmentId = Integer.parseInt(cbDepartment.getId());
        String identityNo = txtIdentityNo.getText();
        String name = txtName.getText();
        String surname = txtSurname.getText();

        jpa.create(0, departmentId, identityNo, name, surname);

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Yeni Kayıt");
        alert.setContentText("Personel başarıyla kayıt edildi!");
        alert.show();

        txtIdentityNo.setText("");
        txtName.setText("");
        txtSurname.setText("");
    }

    @FXML
    private void btnUpdate() throws IOException {

        int id = tableList.getSelectionModel().getSelectedItem().getId();
        int departmentId = Integer.parseInt(cbDepartment.getId());
        String identityNo = txtIdentityNo.getText();
        String name = txtName.getText();
        String surname = txtSurname.getText();

        TblStaffs staff = jpa.read(id);

        if(staff != null) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Güncelleme");
            alert.setContentText("Personel kayıdı güncellensin mi?");
            Optional<ButtonType> alertResult = alert.showAndWait();

            if (alertResult.get() == ButtonType.OK){
                jpa.update(id, departmentId, identityNo, name,surname);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Güncelleme");
                alert.setContentText("Personel başarıyla güncellendi!");
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
            alert.setContentText("Silinecek personel bulunamadı!");
            alert.show();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Silme");
            alert.setContentText("Personel kayıdı silinsin mi?");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK) {
                jpa.delete(id);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Silme");
                alert.setContentText("Personel başarıyla silindi!");
                alert.show();

                txtIdentityNo.setText("");
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

            TblStaffs staff = jpa.read(id);

            if(staff != null) {
                cbDepartment.getSelectionModel().select(staff.getDepartmentId());
                txtIdentityNo.setText(staff.getIdentityNo());
                txtName.setText(staff.getName());
                txtSurname.setText(staff.getSurname());
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
        txtName.setText("");
        txtSurname.setText("");
    }
}