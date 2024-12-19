package com.balaji.review;


import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    Review reviewById(Long id);
    Review createReview(Long companyId, Review review);

    Review updateReview(Long id, Review review);

    Boolean deleteReview(Long id);

    List<Review> getAllReviewsByCompany(Long companyId);

    Double getAverageRatingForCompany(Long companyId);
}
