import DAO.ClientsDAO;
import DAO.DisksDAO;
import DAO.OrdersDAO;
import DAO.MoviesDAO;
import DAO.TypesDAO;
import classes.Clients;

public class Main {
    public static void main(String[] args) {
        System.out.println("goodbye world!");
        ClientsDAO client_dao = new ClientsDAO();
        Clients client = new Clients();
        client.setClient_name("Hibernate Говно Иванович");
        client_dao.save(client);
    }
}