package DAO;

import classes.Movies;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MoviesDAO {
    public MoviesDAO findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(MoviesDAO.class, id);
    }

    public void save(Movies movie) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(movie);
        tx1.commit();
        session.close();
    }

    public void update(Movies movie) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(movie);
        tx1.commit();
        session.close();
    }

    public void delete(Movies movie) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(movie);
        tx1.commit();
        session.close();
    }

    public List<Movies> loadAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Movies> criteria = builder.createQuery(Movies.class);
        criteria.from(Movies.class);
        List<Movies> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }
}