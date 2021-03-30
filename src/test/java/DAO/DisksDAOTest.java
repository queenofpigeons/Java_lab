package DAO;

import entities.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Date;

import static org.testng.Assert.*;

public class DisksDAOTest {

    @Test
    public void testDisk() {
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
        disk.setOrder(order);

        Movies movie = new Movies();
        movie.setMovie_date(2021);
        movie.setMovie_name("Test Movie");
        movie.setMovie_director("Test Director");
        MoviesDAO moviesDAO = new MoviesDAO();
        moviesDAO.save(movie);
        disk.setMovie(movie);

        Types type = new Types();
        type.setType_name("Test Type");
        type.setType_cost(1);
        TypesDAO typesDAO = new TypesDAO();
        typesDAO.save(type);
        disk.setType(type);

        diskDAO.save(disk);
        /*
         * Checking save function
         */
        Assert.assertNotNull(disk);

        /*
         * Checking findById function
         */
        int id = disk.getDisk_id();
        Disks disk2 = diskDAO.findById(id);
        Assert.assertEquals(disk, disk2);

        /*
         * Checking update function
         */
        Types type2 = new Types();
        type2.setType_name("Test Type2");
        type2.setType_cost(2);
        typesDAO.save(type2);
        disk.setType(type2);
        diskDAO.update(disk);
        Assert.assertEquals(disk.getType(), type2);

        /*
         * Checking delete function
         */
        diskDAO.delete(disk);
        Disks disk3 = diskDAO.findById(id);
        Assert.assertNull(disk3);
    }
}