package org.buluton.Models;

import javax.persistence.*;

@Entity
@Table(name = "tbl_students")
public class TblStudents {
    private int id;
    private int departmentId;
    private String studentNo;
    private String identityNo;
    private String name;
    private String surname;

    @Id
    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "department_id")
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "student_no")
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    @Basic
    @Column(name = "identity_no")
    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblStudents that = (TblStudents) o;

        if (id != that.id) return false;
        if (departmentId != that.departmentId) return false;
        if (studentNo != null ? !studentNo.equals(that.studentNo) : that.studentNo != null) return false;
        if (identityNo != null ? !identityNo.equals(that.identityNo) : that.identityNo != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + departmentId;
        result = 31 * result + (studentNo != null ? studentNo.hashCode() : 0);
        result = 31 * result + (identityNo != null ? identityNo.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
