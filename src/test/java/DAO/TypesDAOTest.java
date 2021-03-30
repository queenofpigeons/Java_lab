package DAO;

import entities.Clients;
import entities.Types;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TypesDAOTest {

    @Test
    public void testType() {
        Types type = new Types();
        TypesDAO typesDAO = new TypesDAO();

        type.setType_name("Test Type");
        type.setType_cost(1);
        typesDAO.save(type);
        /*
         * Checking save function
         */
        Assert.assertNotNull(type);

        /*
         * Checking findById function
         */
        int id = type.getType_id();
        Types type2 = typesDAO.findById(id);
        Assert.assertEquals(type2, type);

        /*
         * Checking update function
         */
        type.setType_name("Test Type2");
        typesDAO.update(type);
        Assert.assertEquals(type.getType_name(), "Test Type2");
        type.setType_cost(2);
        typesDAO.update(type);
        Assert.assertEquals(type.getType_cost(), 2);

        /*
         * Checking delete function
         */
        typesDAO.delete(type);
        Types type3 = typesDAO.findById(id);
        Assert.assertNull(type3);
    }
}