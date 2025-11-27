package com.example.lendingplatform.controller;

import com.example.lendingplatform.model.Review;
import com.example.lendingplatform.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    // Create review
    @PostMapping
    public Review createReview(@RequestBody Review review) {
        review.setReviewDate(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    // Get all reviews
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}