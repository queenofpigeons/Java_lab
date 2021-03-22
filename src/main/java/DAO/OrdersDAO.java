package DAO;

import classes.Movies;
import classes.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OrdersDAO {
    public OrdersDAO findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(OrdersDAO.class, id);
    }

    public void save(Orders order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(order);
        tx1.commit();
        session.close();
    }

    public void update(Orders order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(order);
        tx1.commit();
        session.close();
    }

    public void delete(Orders order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(order);
        tx1.commit();
        session.close();
    }

    public List<Orders> loadAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Orders> criteria = builder.createQuery(Orders.class);
        criteria.from(Orders.class);
        List<Orders> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }

    public List<Orders> selectUnreturnedOrders() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Orders> query = session.createQuery("FROM Orders WHERE order_returned is null");
        return query.getResultList();
    }

    public List<Orders> selectOrdersByStatus(boolean order_is_paid) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Orders> query = session.createQuery("FROM Orders WHERE order_is_paid = :param", Orders.class)
                .setParameter("param", order_is_paid);
        return query.getResultList();
    }
}