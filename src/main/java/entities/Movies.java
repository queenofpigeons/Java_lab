package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movies {
    private int movie_id;
    private String movie_name;
    private int movie_date;
    private String movie_director;

    private List<Disks> movie_disks;

    public Movies() {
    }

    public Movies(int movie_id, String movie_name, int movie_date, String movie_director, List<Disks> order_disks) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_date = movie_date;
        this.movie_director = movie_director;
        this.movie_disks = order_disks;
    }

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    @Column(name = "movie_name")
    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    @Column(name = "movie_date")
    public int getMovie_date() {
        return movie_date;
    }

    public void setMovie_date(int movie_date) {
        this.movie_date = movie_date;
    }

    @Column(name = "movie_director")
    public String getMovie_director() {
        return movie_director;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Disks> getMovie_disks() {
        return movie_disks;
    }

    public void setMovie_disks(List<Disks> order_disks) {
        this.movie_disks = order_disks;
    }
}
