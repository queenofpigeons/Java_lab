package services;

import DAO.TypesDAO;
import entities.Types;

import java.util.List;

public class TypeService {
    private TypesDAO typeDAO = new TypesDAO();

    public Types findByIdType(int id) {
        return typeDAO.findById(id);
    }

    public void saveType(Types type) {
        typeDAO.save(type);
    }

    public void updateType(Types type) {
        typeDAO.update(type);
    }

    public void deleteType(Types type) {
        typeDAO.delete(type);
    }

    public List<Types> loadAllType() {
        return typeDAO.loadAll();
    }
}