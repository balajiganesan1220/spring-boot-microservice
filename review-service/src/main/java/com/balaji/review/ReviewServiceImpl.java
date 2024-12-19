package com.balaji.review;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews() {
        return List.of();
    }

    @Override
    public Review reviewById(Long id) {
        Optional<Review> review=reviewRepository.findById(id);
        return review.orElse(null);
    }

    @Override
    public Review createReview(Long companyId, Review review) {
        if(review==null || companyId==null)
        {
            return null;
        }
        review.setCompanyId(companyId);
        return reviewRepository.save(review);

    }

    @Override
    public Review updateReview(Long id, Review review) {
        if(id==null || review==null){
            return null;

        }
        Review updateReview=reviewRepository.findById(id).orElse(null);
        System.out.println(updateReview);
        if(updateReview!=null) {
            updateReview.setDescription(review.getDescription());
            updateReview.setTitle(review.getTitle());
            updateReview.setRating(review.getRating());
            return reviewRepository.save(updateReview);
        }
        else {
            return null;
        }
    }

    @Override
    public Boolean deleteReview(Long id) {

        if(id==null||!reviewRepository.existsById(id))
          return false;
        reviewRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Review> getAllReviewsByCompany(Long companyId) {
        if(companyId==null)
            return Collections.emptyList();

        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Double getAverageRatingForCompany(Long companyId) {
       List<Review> reviews= reviewRepository.findByCompanyId(companyId);
        return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}
