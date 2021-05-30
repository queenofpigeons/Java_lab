package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movies")
public class Movies {
    @Override
    public String toString() {
        return "Movies{" +
                "movie_id=" + movie_id +
                ", movie_name='" + movie_name + '\'' +
                ", movie_date=" + movie_date +
                ", movie_director='" + movie_director + '\'' +
                '}';
    }

    private int movie_id;
    private String movie_name;
    private Integer movie_date;
    private String movie_director;

    private List<Disks> movie_disks;

    public Movies() {
    }

    public Movies(String movie_name, int movie_date, String movie_director, List<Disks> movie_disks) {
        this.movie_name = movie_name;
        this.movie_date = movie_date;
        this.movie_director = movie_director;
        this.movie_disks = movie_disks;
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
    public Integer getMovie_date() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return movie_id == movies.movie_id && movie_date == movies.movie_date && Objects.equals(movie_name, movies.movie_name) && Objects.equals(movie_director, movies.movie_director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie_id, movie_name, movie_date, movie_director);
    }
}
