package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.ClientService;
import entities.Clients;

import java.util.List;

@Controller
public class ClientController {
    ClientService clientService = new ClientService();

    @GetMapping("/clients")
    public String clientsListPage(Model model) {
        List<Clients> clients = clientService.loadAlClient();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/client")
    public String clientPage(@RequestParam(name = "client_id", required = true) int client_id,
                             Model model) {
        Clients client = clientService.findByIdClient(client_id);

        if (client == null){
            model.addAttribute("error_msg", "No client with id  " + client_id);
            return "error";
        }
        model.addAttribute("client", client);

        return "client";
    }

    @PostMapping("/clientSave")
    public String clientSavePage(@RequestParam(name = "client_id", required = false) Integer client_id,
                                 @RequestParam(name = "client_name") String client_name,
                                 @RequestParam(name = "phone") String phone,
                                 Model model) {
        Clients client = null;

        if (client_id != null) {
            client = clientService.findByIdClient(client_id);
            if (client != null) {
                client.setClient_name(client_name);
                client.setClient_phone(phone);
                clientService.updateClient(client);
            }
        }
        if (client == null) {
            client = new Clients(client_name, phone, null);
            clientService.saveClient(client);
        }

        return String.format("redirect:/client?client_id=%d", client.getClient_id());
    }

    @PostMapping("/clientAdd")
    public String clientAddPage(@RequestParam(name = "client_id", required = false) Integer client_id, Model model) {
        if (client_id == null) {
            model.addAttribute("client", new Clients());
            return "clientAdd";
        }

        Clients client = clientService.findByIdClient(client_id);
        if (client == null) {
            model.addAttribute("error_msg", "No client with id " + client_id);
            return "error";
        }

        model.addAttribute("client", client);
        return "clientAdd";
    }

    @PostMapping("/clientDelete")
    public String clientDeletePage(@RequestParam(name = "client_id", required = true) Integer client_id, Model model){
        Clients client = clientService.findByIdClient(client_id);
        clientService.deleteClient(client);
        return "redirect:/clients";
    }

}
