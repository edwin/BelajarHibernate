package com.edw.belajarhibernate;

import com.edw.bean.Dosen;
import com.edw.bean.Mahasiswa;
import com.edw.util.HibernateUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class Main 
{
    private static final Logger logger = Logger.getLogger(Main.class);
    
    public static void main( String[] args ) throws Exception
    {
        select();
    }

    private static void select() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        logger.debug("============ select all ===========");
        List<Dosen> dosens = session.createCriteria(Dosen.class).list();
        for (Dosen dosen : dosens) {
            logger.debug(dosen);
        }
        
        logger.debug("============ select dengan criteria primary key 1 ===========");
        Dosen dosen1 = (Dosen)session.get(Dosen.class, "002");
        logger.debug(dosen1);        
        logger.debug(dosen1.getMahasiswas());        
        
        logger.debug("============ select dengan criteria primary key 2 ===========");
        Dosen dosen2 = (Dosen)session.createCriteria(Dosen.class).add(Restrictions.eq("kodedosen", "002"))
                .addOrder(Order.asc("kodedosen")).uniqueResult();
        logger.debug(dosen2);        
        logger.debug(dosen2.getMahasiswas());        
        
        logger.debug("============ select dengan criteria primary key 3 ===========");
        Dosen dosen3 = (Dosen)session.createQuery("from Dosen d where kodedosen = :kodedosen").setString("kodedosen", "002")
                .setFirstResult(10).setMaxResults(10).uniqueResult();
        logger.debug(dosen3);             
        
        logger.debug("============ select dengan criteria primary key 4 ===========");
        Object[] dosen4 = (Object[])session.createSQLQuery("SELECT kodedosen, namadosen, usia FROM dosen where kodedosen = '002'")
                .uniqueResult();
        logger.debug(dosen4[0]);        
        logger.debug(dosen4[1]);        
        logger.debug(dosen4[2]);                
        
        logger.debug("============ query by example 5 ===========");
        Dosen parameterDosen = new Dosen();          
        parameterDosen.setNamadosen("a");
        parameterDosen.setUsia(20);
        
        Example dosenExample = Example.create(parameterDosen).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeNone().excludeZeroes();          
        List<Dosen> dosens2 = session.createCriteria(Dosen.class).add(dosenExample).list();
        logger.debug(dosens2);  
    }
    
    private static void insert() throws Exception {
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
            logger.error(e,e);
            transaction.rollback();
        } finally {            
            session.close();
        }
    }
    
    private static void delete() throws Exception {
        Dosen dosen = new Dosen();       
        dosen.setKodedosen("003");
        dosen.setNamadosen("Mr Z");
        dosen.setUsia(200);
                
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {       
            transaction = session.beginTransaction();
            session.delete(dosen);                    
            transaction.commit();
        } catch (Exception e) {
            logger.error(e,e);
            transaction.rollback();
        } finally {            
            session.close();
        }
    }
    
    private static void update() throws Exception {
        Dosen dosen = new Dosen();       
        dosen.setKodedosen("003");
        dosen.setNamadosen("Mr Z");
        dosen.setUsia(200);
                
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {       
            transaction = session.beginTransaction();
            session.update(dosen);                    
            transaction.commit();
        } catch (Exception e) {
            logger.error(e,e);
            transaction.rollback();
        } finally {            
            session.close();
        }
    }
}
