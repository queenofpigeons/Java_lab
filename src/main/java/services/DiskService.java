package services;

import DAO.DisksDAO;
import entities.Disks;
import entities.Movies;

import java.util.List;

public class DiskService {
    private DisksDAO diskDAO = new DisksDAO();

    public Disks findByIdDisk(int id) {
        return diskDAO.findById(id);
    }

    public void saveDisk(Disks disk) {
        diskDAO.save(disk);
    }

    public void updateDisk(Disks disk) {
        diskDAO.update(disk);
    }

    public void deleteDisk(Disks disk) {
        diskDAO.delete(disk);
    }

    public List<Disks> loadAllDisk() {
        return diskDAO.loadAll();
    }

}
