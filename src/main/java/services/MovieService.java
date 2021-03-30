package services;

import DAO.MoviesDAO;
import entities.Clients;
import entities.Movies;

import java.util.List;

public class MovieService {
    private MoviesDAO movieDAO = new MoviesDAO();

    public Movies findByIdMovie(int id) {
        return movieDAO.findById(id);
    }

    public void saveMovie(Movies movie) {
        movieDAO.save(movie);
    }

    public void updateMovie(Movies movie) {
        movieDAO.update(movie);
    }

    public void deleteMovie(Movies movie) {
        movieDAO.delete(movie);
    }

    public List<Movies> loadAllMovie() {
        return movieDAO.loadAll();
    }

    public List<Movies> selectMoviesByYear(int movie_date) {
        return movieDAO.selectMoviesByYear(movie_date);
    }

    public List<Movies> selectMoviesByDirector(String movie_director) {
        return movieDAO.selectMoviesByDirector(movie_director);
    }

    public List<Movies> selectUnreturnedMoviesByClient(Clients client) {
        return movieDAO.selectUnreturnedMoviesByClient(client);
    }
}
