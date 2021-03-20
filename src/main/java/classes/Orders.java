package classes;

import javax.persistence.*;
import java.sql.Date;

public class Orders {
    private int order_id;
    private int order_client;
    private java.sql.Date order_issued;
    private java.sql.Date order_returned;
    private float order_cost;
    private boolean order_is_paid;

    public Orders() {
    }

    public Orders(int order_id, int order_client, Date order_issued, Date order_returned, float order_cost, boolean order_is_paid) {
        this.order_id = order_id;
        this.order_client = order_client;
        this.order_issued = order_issued;
        this.order_returned = order_returned;
        this.order_cost = order_cost;
        this.order_is_paid = order_is_paid;
    }

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Column(name = "order_client")
    public int getOrder_client() {
        return order_client;
    }

    public void setOrder_client(int order_client) {
        this.order_client = order_client;
    }

    @Column(name = "order_issued")
    public Date getOrder_issued() {
        return order_issued;
    }

    public void setOrder_issued(Date order_issued) {
        this.order_issued = order_issued;
    }

    @Column(name = "order_returned")
    public Date getOrder_returned() {
        return order_returned;
    }

    public void setOrder_returned(Date order_returned) {
        this.order_returned = order_returned;
    }

    @Column(name = "order_cost")
    public float getOrder_cost() {
        return order_cost;
    }

    public void setOrder_cost(float order_cost) {
        this.order_cost = order_cost;
    }

    @Column(name = "order_is_paid")
    public boolean isOrder_is_paid() {
        return order_is_paid;
    }

    public void setOrder_is_paid(boolean order_is_paid) {
        this.order_is_paid = order_is_paid;
    }
}
