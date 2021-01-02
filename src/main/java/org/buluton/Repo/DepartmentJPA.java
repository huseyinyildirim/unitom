package org.buluton.Repo;

import org.buluton.Models.TblDepartments;

import javax.persistence.*;
import java.util.List;

public class DepartmentJPA {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlConnection");

    private EntityManager mgr = null;
    private EntityTransaction tr = null;

    public void create(int id, String name) {
        mgr = emf.createEntityManager();
        tr = mgr.getTransaction();

        TblDepartments department = new TblDepartments();

        try {
            department.setName(name);

            tr.begin();
            mgr.persist(department);
            tr.commit();
            System.out.println("Departman kayıdı eklendi!.");
        } catch (Exception ex) {
            System.out.println("Ekleme hatası");
            if(tr != null)
                tr.rollback();
            ex.printStackTrace();
        } finally {
            mgr.close();
        }
    }

    public TblDepartments read(int id){

        TblDepartments department = null;
        mgr = emf.createEntityManager();

        try {
            department = mgr.createQuery("FROM tbl_departments s WHERE s.id = :id", TblDepartments.class).setParameter("id", id).getSingleResult();
        } catch(Exception ex) {
            System.out.println("Departman kayıdı bulunamadı!");
        } finally {
            mgr.close();
        }
        return department;
    }

    public List<TblDepartments> readAll(){

        List<TblDepartments> department = null;
        mgr = emf.createEntityManager();

        try {
            department = mgr.createQuery("FROM tbl_departments", TblDepartments.class).getResultList();
        } catch(Exception ex) {
            System.out.println("Öğrenci kayıdı bulunamadı!");
        } finally {
            mgr.close();
        }
        return department;
    }

    public void update(int id, String name) {
        mgr = emf.createEntityManager();
        tr = mgr.getTransaction();

        TblDepartments department = mgr.find(TblDepartments.class, id);
        tr.begin();

        try {
            Query q = mgr.createQuery("UPDATE tbl_departments SET identity_no = :identityNo, name = :name, surname = :surname WHERE id = :id");

            q.setParameter("id", id);
            q.setParameter("name", name);
            q.executeUpdate();

            tr.commit();
            System.out.println("Departman kayıdı güncellendi!.");
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
            Query q = mgr.createQuery("DELETE FROM tbl_departments WHERE id = :id").setParameter("id", id);
            q.executeUpdate();
            tr.commit();
            System.out.println("Departman kayıdı silindi!.");
        } catch (Exception ex) {
            System.out.println("Silme hatası!");
            tr.rollback();
        } finally {
            mgr.close();
        }
    }

    public TblDepartments control(int id) {
        mgr = emf.createEntityManager();
        tr = mgr.getTransaction();
        TblDepartments department = null;

        department = mgr.find(TblDepartments.class, id);

        return department;
    }
}
