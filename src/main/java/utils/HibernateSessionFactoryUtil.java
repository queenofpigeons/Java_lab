package utils;

import entities.Clients;
import entities.Orders;
import entities.Types;
import entities.Disks;
import entities.Movies;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Clients.class);
                configuration.addAnnotatedClass(Orders.class);
                configuration.addAnnotatedClass(Types.class);
                configuration.addAnnotatedClass(Disks.class);
                configuration.addAnnotatedClass(Movies.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("EXCEPTION: " + e);
            }
        }
        if (sessionFactory == null){
            System.err.println("WARNING sessionFactory == null");
        }
        return sessionFactory;
    }
}