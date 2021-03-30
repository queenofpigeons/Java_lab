package DAO;

import entities.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class MoviesDAOTest {

    @Test
    public void testMovie() {
        Movies movie = new Movies();
        movie.setMovie_date(3000);
        movie.setMovie_name("Test Movie");
        movie.setMovie_director("Test Director");
        MoviesDAO moviesDAO = new MoviesDAO();
        moviesDAO.save(movie);
        /*
         * Checking save function
         */
        Assert.assertNotNull(movie);

        /*
         * Checking findById function
         */
        int id = movie.getMovie_id();
        Movies movie2 = moviesDAO.findById(id);
        Assert.assertEquals(movie, movie2);

        /*
         * Checking update function
         */
        movie.setMovie_director("Test Director2");
        moviesDAO.update(movie);
        Assert.assertEquals(movie.getMovie_director(), "Test Director2");


        /*
         * Checking selectMoviesByYear and selectMoviesByDirector functions
         */

        List<Movies> movies3000 = moviesDAO.selectMoviesByYear(3000);
        Assert.assertEquals(movies3000.size(), 1);
        Assert.assertEquals(movies3000.get(0), movie);

        List<Movies> moviesTestDirector = moviesDAO.selectMoviesByDirector("Test Director2");
        Assert.assertEquals(moviesTestDirector.size(), 1);
        Assert.assertEquals(moviesTestDirector.get(0), movie);

        Disks disk = new Disks();
        DisksDAO diskDAO = new DisksDAO();

        Clients client = new Clients();
        client.setClient_name("Test Client");
        ClientsDAO clientsDAO = new ClientsDAO();
        clientsDAO.save(client);

        Orders order = new Orders();
        order.setClient(client);
        order.setOrder_cost(1);
        order.setOrder_issued(java.sql.Date.valueOf("2021-01-01"));
        OrdersDAO ordersDAO = new OrdersDAO();
        ordersDAO.save(order);

        Types type = new Types();
        type.setType_name("Test Type");
        type.setType_cost(1);
        TypesDAO typesDAO = new TypesDAO();
        typesDAO.save(type);

        disk.setType(type);
        disk.setOrder(order);
        disk.setMovie(movie);
        diskDAO.save(disk);

        List<Movies> moviesUnreturned = moviesDAO.selectUnreturnedMoviesByClient(client);
        Assert.assertEquals(moviesUnreturned.size(), 1);
        Assert.assertEquals(moviesUnreturned.get(0), movie);

        /*
         * Checking delete function, we cannot delete movie directly, because of foreign key constraint
         */
        id = movie.getMovie_id();
        diskDAO.delete(disk);
        moviesDAO.delete(movie);
        Movies movie3 = moviesDAO.findById(id);
        Assert.assertNull(movie3);

    }
}