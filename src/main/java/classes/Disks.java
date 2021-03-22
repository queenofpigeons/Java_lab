package classes;

import javax.persistence.*;

@Entity
@Table(name = "disks")
public class Disks {
    private int disk_id;

    private Orders order;

    private Movies movie;

    private Types type;

    public Disks() {
    }

    public Disks(int disk_id, Orders order, Movies movie, Types type) {
        this.disk_id = disk_id;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disk_order")
    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disk_movie")
    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disk_type")
    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }
}
