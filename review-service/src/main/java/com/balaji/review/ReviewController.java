package com.balaji.review;

import com.balaji.review.messaging.ReviewMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMessageProducer reviewMessageProducer;

    public ReviewController(ReviewService reviewService,ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer=reviewMessageProducer;
    }

    @GetMapping
    public ResponseEntity<List<Review>> reviewsByCompany(@RequestParam Long companyId){
        List<Review> reviews= reviewService.getAllReviewsByCompany(companyId);
        if(reviews.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(reviews,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Review> reviewById(@PathVariable Long id){
        Review review=reviewService.reviewById(id);
        if(review!=null)
            return new ResponseEntity<>(review,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId,@RequestBody Review review) {
        Review createdReview = reviewService.createReview(companyId,review);
        if (createdReview != null) {
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Not Created", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id,@RequestBody Review review){
        Review updatedReview=reviewService.updateReview(id,review);
        if(updatedReview!=null)
            return new ResponseEntity<>("Review Successfully Updated",HttpStatus.OK);
        else
            return new ResponseEntity<>("Invalid Review or id",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id){
        Boolean isDelete=reviewService.deleteReview(id);
        if(isDelete)
            return new ResponseEntity<>("Review Successfully Deleted",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review Not Found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/average")
    public ResponseEntity<Double> averageRatingForCompany(@RequestParam Long companyId) {
        Double averageRating = reviewService.getAverageRatingForCompany(companyId);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }


}
