package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Orders {

    private int order_id;
    private java.sql.Date order_issued;
    private java.sql.Date order_returned;
    private float order_cost;
    private boolean order_is_paid;

    private Clients client;

    private List<Disks> order_disks;

    public Orders() {
    }

    public Orders(Date order_issued, Date order_returned, float order_cost, boolean order_is_paid, Clients client, List<Disks> order_disks) {
        this.order_issued = order_issued;
        this.order_returned = order_returned;
        this.order_cost = order_cost;
        this.order_is_paid = order_is_paid;
        this.client = client;
        this.order_disks = order_disks;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_client")
    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Disks> getOrder_disks() {
        return order_disks;
    }

    public void setOrder_disks(List<Disks> order_disks) {
        this.order_disks = order_disks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return order_id == orders.order_id && Float.compare(orders.order_cost, order_cost) == 0 && order_is_paid == orders.order_is_paid && Objects.equals(order_issued, orders.order_issued) && Objects.equals(order_returned, orders.order_returned) && client.equals(orders.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, order_issued, order_returned, order_cost, order_is_paid, client);
    }
}
