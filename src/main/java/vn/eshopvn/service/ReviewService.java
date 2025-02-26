package vn.eshopvn.service;

import java.util.List;

import vn.eshopvn.entity.Review;

public interface ReviewService {
	void insert(Review review); 

    void deleteById(String reviewId) throws Exception; 

    void update(Review review); 

    Review findById(String reviewId); 

    List<Review> findAll(); 

    List<Review> findByProductId(String pid); 

    List<Review> findByUserId(String userid);

    double averageRatingByProductId(String pid); 

}