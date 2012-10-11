package com.edw.belajarhibernate;

import com.edw.bean.Dosen;
import com.edw.bean.Mahasiswa;
import com.edw.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main 
{
    public static void main( String[] args )
    {
        Dosen dosen = new Dosen();       
        dosen.setKodedosen("003");
        dosen.setNamadosen("Mr Z");
        dosen.setUsia(200);
        
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNama("Si Bacullers");
        mahasiswa.setNim("8263538");
        mahasiswa.setDosen(dosen);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {       
            transaction = session.beginTransaction();
            session.save(dosen);            
            session.save(mahasiswa);            
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {            
            session.close();
        }
    }
}
