package Controllers;

import entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.DiskService;
import services.MovieService;
import services.OrderService;
import services.TypeService;

import java.util.List;

@Controller
public class DiskController {
    DiskService diskService = new DiskService();
    MovieService movieService = new MovieService();
    TypeService typeService = new TypeService();
    OrderService orderService = new OrderService();

    @GetMapping("/disks")
    public String disksListPage(Model model) {
        List<Disks> disks = diskService.loadAllDisk();
        model.addAttribute("disks", disks);
        return "disks";
    }

    @GetMapping("/disk")
    public String diskPage(@RequestParam(name = "disk_id", required = true) int disk_id,
                             Model model) {
        Disks disk = diskService.findByIdDisk(disk_id);

        if (disk == null){
            model.addAttribute("error_msg", "No disk with id  " + disk_id);
            return "error";
        }
        model.addAttribute("disk", disk);

        return "disk";
    }

    @PostMapping("/diskSave")
    public String clientSavePage(@RequestParam(name = "disk_id", required = true) Integer disk_id,
                                 @RequestParam(name = "disk_order", required = false) Integer disk_order,
                                 @RequestParam(name = "disk_type", required = true) Integer disk_type,
                                 @RequestParam(name = "disk_movie", required = true) Integer disk_movie,
                                 Model model) {
        if (disk_id == null){
            model.addAttribute("error_msg",
                            "No disk with id: " + disk_id);
            return "error";
        }

        Disks disk = null;
        Movies movie = null;
        Orders order = null;
        Types type = null;

        movie = movieService.findByIdMovie(disk_movie);
        if (movie == null){
            model.addAttribute("error_msg",
                    "No movie with id: " + disk_movie);
            return "error";
        }
        type = typeService.findByIdType(disk_type);
        if (type == null){
            model.addAttribute("error_msg",
                    "No type with id: " + disk_type);
            return "error";
        }
        if (disk_order != null) {
            order = orderService.findByIdOrder(disk_order);
        }


        if (disk_id != null) {
            disk = diskService.findByIdDisk(disk_id);
            if (disk != null) {
                disk.setMovie(movie);
                disk.setType(type);
                disk.setOrder(order);
                diskService.updateDisk(disk);
            }
        }
        if (disk == null) {
            disk = new Disks(order, movie, type);
            diskService.saveDisk(disk);
        }

        return String.format("redirect:/disk?disk_id=%d", disk.getDisk_id());
    }

    @PostMapping("/diskAdd")
    public String diskAddPage(@RequestParam(name = "disk_id", required = false) Integer disk_id,
                              Model model) {
        if (disk_id == null) {
            model.addAttribute("disk", new Disks());
            return "diskAdd";
        }

        Disks disk = diskService.findByIdDisk(disk_id);
        if (disk == null) {
            model.addAttribute("error_msg", "No disk with id " + disk_id);
            return "error";
        }

        model.addAttribute("disk", disk);
        return "diskAdd";
    }

    @PostMapping("/diskDelete")
    public String clientDeletePage(@RequestParam(name = "disk_id", required = true) Integer disk_id, Model model){
        Disks disk = diskService.findByIdDisk(disk_id);
        diskService.deleteDisk(disk);
        return "redirect:/disks";
    }
}
