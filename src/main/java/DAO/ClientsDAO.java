package DAO;

import classes.Clients;
import classes.Orders;
import classes.Movies;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ClientsDAO {
    public ClientsDAO findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ClientsDAO.class, id);
    }

    public void save(Clients client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(client);
        tx1.commit();
        session.close();
    }

    public void update(Clients client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(client);
        tx1.commit();
        session.close();
    }

    public void delete(Clients client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(client);
        tx1.commit();
        session.close();
    }

    public List<Clients> loadAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Clients> criteria = builder.createQuery(Clients.class);
        criteria.from(Clients.class);
        List<Clients> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }

    public List<Orders> selectOrdersByClientId(int client_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Clients client = session.get(Clients.class, client_id);
        List<Orders> result = client.getClient_orders();
        session.close();
        return result;
    }
}