package services;

import DAO.ClientsDAO;
import entities.Clients;

import java.util.List;

public class ClientService {
    private ClientsDAO clientDAO = new ClientsDAO();

    public Clients findByIdClient(int id) {
        return clientDAO.findById(id);
    }

    public void saveClient(Clients client) {
        clientDAO.save(client);
    }

    public void updateClient(Clients client) {
        clientDAO.update(client);
    }

    public void deleteClient(Clients client) {
        clientDAO.delete(client);
    }

    public List<Clients> loadAlClient() {
        return clientDAO.loadAll();
    }
}
