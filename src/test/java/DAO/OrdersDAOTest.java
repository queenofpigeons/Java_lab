package DAO;

import entities.Clients;
import entities.Orders;
import DAO.ClientsDAO;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class OrdersDAOTest {

    @Test
    public void testOrder() {
        Clients client = new Clients();
        ClientsDAO clientsDAO = new ClientsDAO();

        Orders order = new Orders();
        order.setClient(client);
        order.setOrder_cost(1);
        order.setOrder_is_paid(false);
        order.setOrder_issued(java.sql.Date.valueOf("2021-01-01"));
        clientsDAO.save(client);
        OrdersDAO ordersDAO = new OrdersDAO();
        ordersDAO.save(order);

        /*
         * Checking save function
         */
        Assert.assertNotNull(order);

        /*
         * Checking findById function
         */
        int id = order.getOrder_id();
        Orders order2 = ordersDAO.findById(id);
        Assert.assertEquals(order2, order);

        /*
         * Checking update function
         */
        order.setOrder_cost(2);
        ordersDAO.update(order);
        Assert.assertEquals(order.getOrder_cost(), 2);

        /*
         * Checking selectUnreturnedOrders function
         */
        List<Orders> unreturned = ordersDAO.selectUnreturnedOrders();
        Assert.assertNotEquals(unreturned.size(), 0);

        /*
         * Checking selectOrdersByStatus function
         */
        List<Orders> unpaid = ordersDAO.selectOrdersByStatus(false);
        Assert.assertNotEquals(unpaid.size(), 0);


        ordersDAO.delete(order);
        Orders order3 = ordersDAO.findById(id);
        Assert.assertNull(order3);
    }
}