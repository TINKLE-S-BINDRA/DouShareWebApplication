package com.example.lendingplatform.repository;

import com.example.lendingplatform.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRevieweeId(Long revieweeId);

    List<Review> findByReviewerId(Long reviewerId);

}