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

import java.lang.reflect.Type;
import java.util.List;

@Controller
public class TypeController {
    TypeService typeService = new TypeService();

    @GetMapping("/types")
    public String typesListPage(Model model) {
        List<Types> types = typeService.loadAllType();
        model.addAttribute("types", types);
        return "types";
    }

    @GetMapping("/type")
    public String typePage(@RequestParam(name = "type_id", required = true) int type_id,
                             Model model) {
        Types type = typeService.findByIdType(type_id);

        if (type == null){
            model.addAttribute("error_msg", "No type with id  " + type_id);
            return "error";
        }
        model.addAttribute("type", type);

        return "type";
    }

    @PostMapping("/typeSave")
    public String typeSavePage(@RequestParam(name = "type_id", required = false) Integer type_id,
                                 @RequestParam(name = "type_name") String type_name,
                                 @RequestParam(name = "type_cost") Integer type_cost,
                                 Model model) {
        Types type = null;

        if (type_id != null) {
            type = typeService.findByIdType(type_id);
            if (type != null) {
                type.setType_name(type_name);
                type.setType_cost(type_cost);
                typeService.updateType(type);
            }
        }
        if (type == null) {
            type = new Types(type_name, type_cost, null);
            typeService.saveType(type);
        }

        return String.format("redirect:/type?type_id=%d", type.getType_id());
    }

    @PostMapping("/typeAdd")
    public String typeAddPage(@RequestParam(name = "type_id", required = false) Integer type_id, Model model) {
        if (type_id == null) {
            model.addAttribute("type", new Types());
            return "typeAdd";
        }

        Types type = typeService.findByIdType(type_id);
        if (type == null) {
            model.addAttribute("error_msg", "No type with id " + type_id);
            return "error";
        }

        model.addAttribute("type", type);
        return "typeAdd";
    }

    @PostMapping("/typeDelete")
    public String typeDeletePage(@RequestParam(name = "type_id", required = true) Integer type_id, Model model){
        Types type = typeService.findByIdType(type_id);
        typeService.deleteType(type);
        return "redirect:/types";
    }

}
