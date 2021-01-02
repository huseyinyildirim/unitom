package org.buluton.Repo;

import org.buluton.Models.TblStudents;
import javax.persistence.*;
import java.util.List;

public class StudentJPA {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlConnection");

    private EntityManager mgr = null;
    private EntityTransaction tr = null;

    public void create(int id, int departmentId, String studentNo, String identityNo, String name, String surname) {
        mgr = emf.createEntityManager();
        tr = mgr.getTransaction();

        TblStudents student = new TblStudents();

        try {
            student.setDepartmentId(departmentId);
            student.setStudentNo(studentNo);
            student.setIdentityNo(identityNo);
            student.setName(name);
            student.setSurname(surname);

            tr.begin();
            mgr.persist(student);
            tr.commit();

            System.out.println("Öğrenci kayıdı eklendi!.");
        } catch (Exception ex) {
            System.out.println("Ekleme hatası!");
            if(tr != null)
                tr.rollback();
            ex.printStackTrace();
        } finally {
            mgr.close();
        }
    }

    public TblStudents read(int id){

        TblStudents student = null;
        mgr = emf.createEntityManager();

        try {
            student = mgr.createQuery("FROM tbl_students s WHERE s.id = :id", TblStudents.class).setParameter("id", id).getSingleResult();
        } catch(Exception ex) {
            System.out.println("Öğrenci kayıdı bulunamadı!");
        } finally {
            mgr.close();
        }
        return student;
    }

    public List<TblStudents> readAll(){

        List<TblStudents> student = null;
        mgr = emf.createEntityManager();

        try {
            student = mgr.createQuery("FROM tbl_students", TblStudents.class).getResultList();
        } catch(Exception ex) {
            System.out.println("Öğrenci kayıdı bulunamadı!");
        } finally {
            mgr.close();
        }
        return student;
    }

    public void update(int id, int departmentId, String studentNo, String identityNo, String name, String surname) {
        mgr = emf.createEntityManager();
        tr = mgr.getTransaction();

        TblStudents student = mgr.find(TblStudents.class, id);
        tr.begin();

        try {
            Query q = mgr.createQuery("UPDATE tbl_students SET department_id = :departmentId, student_no = :studentNo, identity_no = :identityNo, name = :name, surname = :surname WHERE id = :id");
            q.setParameter("id", id);
            q.setParameter("department_id", departmentId);
            q.setParameter("student_no", studentNo);
            q.setParameter("identity_no", identityNo);
            q.setParameter("name", name);
            q.setParameter("surname", surname);
            q.executeUpdate();

            tr.commit();
            System.out.println("Öğrenci kayıdı güncellendi!.");
        } catch(Exception ex) {
            System.out.println("Güncelleme hatası!");
            if(tr != null)
                tr.rollback();
            ex.printStackTrace();
        } finally {
            mgr.close();
        }
    }

    public void delete(int id) {
        mgr = emf.createEntityManager();
        tr = mgr.getTransaction();

        tr.begin();
        try {
            Query q = mgr.createQuery("DELETE FROM tbl_students WHERE id = :id").setParameter("id", id);
            q.executeUpdate();
            tr.commit();
            System.out.println("Öğrenci kayıdı silindi!.");
        } catch (Exception ex) {
            System.out.println("Silme hatası!");
            tr.rollback();
        } finally {
            mgr.close();
        }
    }

    public TblStudents control(int id) {
        mgr = emf.createEntityManager();
        tr = mgr.getTransaction();
        TblStudents student = null;

        student = mgr.find(TblStudents.class, id);

        return student;
    }
}
