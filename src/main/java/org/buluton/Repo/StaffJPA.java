package org.buluton.Repo;

import org.buluton.Models.TblStaffs;
import javax.persistence.*;
import java.util.List;

public class StaffJPA {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlConnection");

    private EntityManager mgr = null;
    private EntityTransaction tr = null;

    public void create(int id, int departmentId, String identityNo, String name, String surname) {
        mgr = emf.createEntityManager();
        tr = mgr.getTransaction();

        TblStaffs staff = new TblStaffs();

        try {
            staff.setDepartmentId(departmentId);
            staff.setIdentityNo(identityNo);
            staff.setName(name);
            staff.setSurname(surname);

            tr.begin();
            mgr.persist(staff);
            tr.commit();

            System.out.println("Personel kayıdı eklendi!.");
        } catch (Exception ex) {
            System.out.println("Ekleme hatası");
            if(tr != null)
                tr.rollback();
            ex.printStackTrace();
        } finally {
            mgr.close();
        }
    }

    public TblStaffs read(int id){

        TblStaffs staff = null;
        mgr = emf.createEntityManager();

        try {
            staff = mgr.createQuery("FROM tbl_staffs s WHERE s.id = :id", TblStaffs.class).setParameter("id", id).getSingleResult();
        } catch(Exception ex) {
            System.out.println("Personel kayıdı bulunamadı!");
        } finally {
            mgr.close();
        }
        return staff;
    }

    public List<TblStaffs> readAll(){

        List<TblStaffs> staff = null;
        mgr = emf.createEntityManager();

        try {
            staff = mgr.createQuery("FROM tbl_staffs", TblStaffs.class).getResultList();
        } catch(Exception ex) {
            System.out.println("Öğrenci kayıdı bulunamadı!");
        } finally {
            mgr.close();
        }
        return staff;
    }

    public void update(int id, int departmentId, String identityNo, String name, String surname) {
        mgr = emf.createEntityManager();
        tr = mgr.getTransaction();

        TblStaffs staff = mgr.find(TblStaffs.class, id);
        tr.begin();

        try {
            Query q = mgr.createQuery("UPDATE tbl_staffs SET department_id = :department_id, identity_no = :identityNo, name = :name, surname = :surname WHERE id = :id");
            q.setParameter("id", id);
            q.setParameter("department_id", departmentId);
            q.setParameter("identity_no", identityNo);
            q.setParameter("name", name);
            q.setParameter("surname", surname);
            q.executeUpdate();

            tr.commit();
            System.out.println("Personel kayıdı güncellendi!.");
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
            Query q = mgr.createQuery("DELETE FROM tbl_staffs WHERE id = :id").setParameter("id", id);
            q.executeUpdate();
            tr.commit();
            System.out.println("Personel kayıdı silindi!.");
        } catch (Exception ex) {
            System.out.println("Silme hatası!");
            tr.rollback();
        } finally {
            mgr.close();
        }
    }

    public TblStaffs control(int id) {
        mgr = emf.createEntityManager();
        tr = mgr.getTransaction();
        TblStaffs staff = null;

        staff = mgr.find(TblStaffs.class, id);

        return staff;
    }
}
