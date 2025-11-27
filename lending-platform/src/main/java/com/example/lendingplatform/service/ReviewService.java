package com.example.lendingplatform.service;

import com.example.lendingplatform.model.Review;
import com.example.lendingplatform.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review create(Review review) {
        review.setReviewDate(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public Review getById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public List<Review> getByReviewee(Long userId) {
        return reviewRepository.findByRevieweeId(userId);
    }

    public List<Review> getByReviewer(Long userId) {
        return reviewRepository.findByReviewerId(userId);
    }

    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}