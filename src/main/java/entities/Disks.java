package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "disks")
public class Disks {
    @Override
    public String toString() {
        return "Disks{" +
                "disk_id=" + disk_id +
                ", order=" + order +
                ", movie=" + movie +
                ", type=" + type +
                '}';
    }

    private int disk_id;

    private Orders order;

    private Movies movie;

    private Types type;

    public Disks() {
    }

    public Disks(Orders order, Movies movie, Types type) {
        this.order = order;
        this.movie = movie;
        this.type = type;
    }

    @Id
    @Column(name = "disk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getDisk_id() {
        return disk_id;
    }

    public void setDisk_id(int disk_id) {
        this.disk_id = disk_id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disk_order")
    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disk_movie")
    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disk_type")
    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disks disks = (Disks) o;
        boolean a = disk_id == disks.disk_id;
        boolean b = order.equals(disks.order);
        boolean c = movie.equals(disks.movie);
        boolean d = type.equals(disks.type);
        return disk_id == disks.disk_id && order.equals(disks.order) && movie.equals(disks.movie) && type.equals(disks.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disk_id, order, movie, type);
    }

}
