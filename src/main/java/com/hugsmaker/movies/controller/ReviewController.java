package com.hugsmaker.movies.controller;

import com.hugsmaker.movies.model.Review;
import com.hugsmaker.movies.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @PostMapping("")
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        try {
            Review review = service.createReview(payload.get("reviewBody"), payload.get("imdbId"));
            return new ResponseEntity<>(review, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create review", e);
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
    }
}
