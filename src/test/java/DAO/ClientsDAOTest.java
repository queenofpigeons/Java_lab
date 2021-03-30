package DAO;

import entities.Clients;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ClientsDAOTest {

    @Test
    public void testClient() {
        Clients client = new Clients();
        ClientsDAO clientDAO = new ClientsDAO();

        client.setClient_name("Test Client");
        clientDAO.save(client);
        /*
         * Checking save function
         */
        Assert.assertNotNull(client);

        /*
         * Checking findById function
         */
        int id = client.getClient_id();
        Clients client2 = clientDAO.findById(id);
        Assert.assertEquals(client, client2);

        /*
         * Checking update function
         */
        client.setClient_name("Test Client2");
        clientDAO.update(client);
        Assert.assertEquals(client.getClient_name(), "Test Client2");

        /*
         * Checking delete function
         */
        clientDAO.delete(client);
        Clients client3 = clientDAO.findById(id);
        Assert.assertNull(client3);
    }

}