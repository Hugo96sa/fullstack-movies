package com.hugsmaker.movies.dao;

import com.hugsmaker.movies.model.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    // Useful when there are unique values for identification
    Optional<Movie> findMovieByImdbId(String imdbId);
}
