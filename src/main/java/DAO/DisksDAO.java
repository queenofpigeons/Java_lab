package DAO;

import entities.Disks;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DisksDAO {
    public Disks findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Disks.class, id);
    }

    public void save(Disks disk) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(disk);
        tx1.commit();
        session.close();
    }

    public void update(Disks disk) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(disk);
        tx1.commit();
        session.close();
    }

    public void delete(Disks disk) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(disk);
        tx1.commit();
        session.close();
    }

    public List<Disks> loadAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Disks> criteria = builder.createQuery(Disks.class);
        criteria.from(Disks.class);
        List<Disks> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }
}