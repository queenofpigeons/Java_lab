package DAO;

import entities.Orders;
import entities.Types;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TypesDAO {
    public Types findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Types type = session.get(Types.class, id);
        session.close();
        return type;
    }

    public void save(Types type) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(type);
        tx1.commit();
        session.close();
    }

    public void update(Types type) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(type);
        tx1.commit();
        session.close();
    }

    public void delete(Types type) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(type);
        tx1.commit();
        session.close();
    }

    public List<Types> loadAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Types> criteria = builder.createQuery(Types.class);
        criteria.from(Types.class);
        List<Types> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }
}