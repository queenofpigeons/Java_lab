package Controllers;

import entities.Clients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.MovieService;
import services.ClientService;

import java.util.List;

@Controller
public class GreetingController {
    MovieService movieService = new MovieService();
    ClientService clientService = new ClientService();

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


}