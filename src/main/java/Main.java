import DAO.ClientsDAO;
import DAO.DisksDAO;
import DAO.OrdersDAO;
import DAO.MoviesDAO;
import DAO.TypesDAO;
import classes.Clients;
import classes.Movies;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("goodbye world!");
        MoviesDAO movie_dao = new MoviesDAO();
        Movies movie = new Movies();
        List<Movies> mov2000 = movie_dao.selectMoviesByYear(2000);
        for (Movies mov : mov2000) {
            System.out.println("  " + mov.getMovie_name());
        }
        /*ClientsDAO client_dao = new ClientsDAO();
        Clients client = new Clients();
        client.setClient_name("Hibernate Говно Иванович");
        client_dao.save(client);*/
    }
}
