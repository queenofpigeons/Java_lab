package Controllers;

import com.sun.tools.corba.se.idl.constExpr.Or;
import entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.ClientService;
import services.MovieService;
import services.OrderService;
import services.TypeService;

import java.sql.Date;
import java.util.List;

@Controller
public class OrderController {
    OrderService orderService = new OrderService();
    ClientService clientService = new ClientService();

    @GetMapping("/orders")
    public String disksListPage(Model model) {
        List<Orders> orders = orderService.loadAllOrder();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/order")
    public String orderPage(@RequestParam(name = "order_id", required = true) int order_id,
                           Model model) {
        Orders order = orderService.findByIdOrder(order_id);

        if (order == null){
            model.addAttribute("error_msg", "No order with id  " + order_id);
            return "error";
        }
        model.addAttribute("order", order);

        return "order";
    }

    @PostMapping("/orderSave")
    public String orderSavePage(@RequestParam(name = "order_id", required = true) Integer order_id,
                                @RequestParam(name = "order_client", required = true) Integer order_client,
                                @RequestParam(name = "order_cost", required = true) Float order_cost,
                                @RequestParam(name = "order_issued", required = true) String order_issued,
                                @RequestParam(name = "order_returned", required = false) String order_returned,
                                @RequestParam(name = "order_is_paid", required = true) String order_is_paid,
                                Model model) {
        boolean is_paid = Boolean.parseBoolean(order_is_paid);

        if (order_id == null){
            model.addAttribute("error_msg",
                    "No order with id: " + order_id);
            return "error";
        }

        Orders order = null;
        Clients client = null;

        client = clientService.findByIdClient(order_client);
        if (client == null){
            model.addAttribute("error_msg",
                    "No client with id: " + order_client);
            return "error";
        }
        Date issued = java.sql.Date.valueOf(order_issued);
        Date returned = null;
        if (order_returned != null) {
            returned = java.sql.Date.valueOf(order_returned);
        }

        if (order_id != null) {
            order = orderService.findByIdOrder(order_id);
            if (order != null) {
                order.setOrder_cost(order_cost);
                order.setOrder_is_paid(is_paid);
                if (client != null) {
                    order.setClient(client);
                }
                order.setOrder_issued(issued);
                order.setOrder_returned(returned);
                orderService.updateOrder(order);
            }
        }
        if (order == null) {
            order = new Orders(issued, returned, order_cost, is_paid, client, null);
            orderService.saveOrder(order);
        }

        return String.format("redirect:/order?order_id=%d", order.getOrder_id());
    }

    @PostMapping("/orderAdd")
    public String diskAddPage(@RequestParam(name = "order_id", required = false) Integer order_id,
                              Model model) {
        if (order_id == null) {
            model.addAttribute("order", new Orders());
            return "orderAdd";
        }

        Orders order = orderService.findByIdOrder(order_id);
        if (order == null) {
            model.addAttribute("error_msg", "No order with id " + order_id);
            return "error";
        }

        model.addAttribute("order", order);
        return "orderAdd";
    }

    @PostMapping("/orderDelete")
    public String orderDeletePage(@RequestParam(name = "order_id", required = true) Integer order_id, Model model){
        Orders order = orderService.findByIdOrder(order_id);
        orderService.deleteOrder(order);
        return "redirect:/orders";
    }

}