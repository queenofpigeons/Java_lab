package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "types")
public class Types {

    private int type_id;
    private String type_name;
    private int type_cost;

    private List<Disks> type_disks;

    public Types() {
    }

    public Types(String type_name, int type_cost, List<Disks> order_disks) {
        this.type_name = type_name;
        this.type_cost = type_cost;
        this.type_disks = order_disks;
    }

    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @Column(name = "type_name")
    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Column(name = "type_cost")
    public int getType_cost() {
        return type_cost;
    }

    public void setType_cost(int type_cost) {
        this.type_cost = type_cost;
    }

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Disks> getType_disks() {
        return type_disks;
    }

    public void setType_disks(List<Disks> order_disks) {
        this.type_disks = order_disks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Types types = (Types) o;
        return type_id == types.type_id && type_cost == types.type_cost && Objects.equals(type_name, types.type_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type_id, type_name, type_cost);
    }
}
