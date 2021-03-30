package services;

import DAO.OrdersDAO;
import entities.Clients;
import entities.Orders;

import java.util.List;

public class OrderService {
    private OrdersDAO orderDAO = new OrdersDAO();

    public Orders findByIdOrder(int id) {
        return orderDAO.findById(id);
    }

    public void saveOrder(Orders order) {
        orderDAO.save(order);
    }

    public void updateOrder(Orders order) {
        orderDAO.update(order);
    }

    public void deleteOrder(Orders order) {
        orderDAO.delete(order);
    }

    public List<Orders> loadAllOrder() {
        return orderDAO.loadAll();
    }

    public List<Orders> selectUnreturnedOrders() {
        return orderDAO.selectUnreturnedOrders();
    }

    public List<Orders> selectOrdersByStatus(boolean order_is_paid) {
        return orderDAO.selectOrdersByStatus(order_is_paid);
    }

}
