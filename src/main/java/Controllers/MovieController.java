package Controllers;

import entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.MovieService;
import services.OrderService;
import services.TypeService;

import java.util.List;

@Controller
public class MovieController {
    MovieService movieService = new MovieService();

    @GetMapping("/movies")
    public String moviesListPage(Model model) {
        List<Movies> movies = movieService.loadAllMovie();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/movie")
    public String moviePage(@RequestParam(name = "movie_id", required = true) int movie_id,
                           Model model) {
        Movies movie = movieService.findByIdMovie(movie_id);

        if (movie == null){
            model.addAttribute("error_msg", "No movie with id  " + movie_id);
            return "error";
        }
        model.addAttribute("movie", movie);

        return "movie";
    }

    @PostMapping("/movieSave")
    public String movieSavePage(@RequestParam(name = "movie_id", required = true) Integer movie_id,
                                 @RequestParam(name = "movie_name", required = true) String movie_name,
                                 @RequestParam(name = "movie_director", required = true) String movie_director,
                                 @RequestParam(name = "movie_date", required = true) Integer movie_date,
                                 Model model) {
        if (movie_id == null){
            model.addAttribute("error_msg",
                    "No movie with id: " + movie_id);
            return "error";
        }

        Movies movie = null;

        if (movie_id != null) {
            movie = movieService.findByIdMovie(movie_id);
            if (movie != null) {
                movie.setMovie_director(movie_director);
                movie.setMovie_name(movie_name);
                movie.setMovie_date(movie_date);
                movieService.updateMovie(movie);
            }
        }
        if (movie == null) {
            movie = new Movies(movie_name, movie_date, movie_director, null );
            movieService.saveMovie(movie);
        }

        return String.format("redirect:/movie?movie_id=%d", movie.getMovie_id());
    }

    @PostMapping("/movieAdd")
    public String movieAddPage(@RequestParam(name = "movie_id", required = false) Integer movie_id,
                              Model model) {
        if (movie_id == null) {
            model.addAttribute("movie", new Movies());
            return "movieAdd";
        }

        Movies movie = movieService.findByIdMovie(movie_id);
        if (movie == null) {
            model.addAttribute("error_msg", "No movie with id " + movie_id);
            return "error";
        }

        model.addAttribute("movie", movie);
        return "movieAdd";
    }

    @PostMapping("/movieDelete")
    public String movieDeletePage(@RequestParam(name = "movie_id", required = true) Integer movie_id, Model model){
        Movies movie = movieService.findByIdMovie(movie_id);
        movieService.deleteMovie(movie);
        return "redirect:/movies";
    }
}
