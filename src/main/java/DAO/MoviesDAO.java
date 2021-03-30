package DAO;

import entities.Clients;
import entities.Movies;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MoviesDAO {
    public Movies findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Movies.class, id);
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

    public List<Movies> selectMoviesByYear(int movie_date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Movies> query = session.createQuery("FROM Movies WHERE movie_date = :param", Movies.class)
                .setParameter("param", movie_date);
        return query.getResultList();
    }

    public List<Movies> selectMoviesByDirector(String movie_director) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Movies> query = session.createQuery("FROM Movies WHERE movie_director = :param", Movies.class)
                .setParameter("param", movie_director);
        return query.getResultList();
    }

    public List<Movies> selectUnreturnedMoviesByClient(Clients client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Movies> query = session.createQuery("select mov FROM Clients cli" +
                " JOIN cli.client_orders ord JOIN ord.order_disks ds JOIN ds.movie mov" +
                " WHERE ord.order_returned IS NULL", Movies.class);
        return query.getResultList();
    }
}