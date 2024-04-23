package com.hugsmaker.movies.controller;

import com.hugsmaker.movies.model.Movie;
import com.hugsmaker.movies.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        try {
            List<Movie> movies = service.allMovies();
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch movies");
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Movie>> getAMovie(@PathVariable ObjectId id) {
        try {
            Optional<Movie> movie = service.singleMovieId(id);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch a movie by ID", e);
        }
    }

    @GetMapping("/imdb_id/{imdbId}")
    public ResponseEntity<Optional<Movie>> getAMovie(@PathVariable String imdbId) {
        try {
            Optional<Movie> movie = service.singleMovieImdbId(imdbId);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch a movie by IMDB ID", e);
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleExceptions(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
    }
}
