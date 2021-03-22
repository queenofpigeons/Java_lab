package DAO;

import classes.Types;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TypesDAO {
    public TypesDAO findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(TypesDAO.class, id);
    }

    public void save(TypesDAO type) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(type);
        tx1.commit();
        session.close();
    }

    public void update(TypesDAO type) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(type);
        tx1.commit();
        session.close();
    }

    public void delete(TypesDAO type) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(type);
        tx1.commit();
        session.close();
    }

    public List<TypesDAO> loadAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TypesDAO> criteria = builder.createQuery(TypesDAO.class);
        criteria.from(TypesDAO.class);
        List<TypesDAO> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }
}