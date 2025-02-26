package vn.eshopvn.service.impl;

import java.util.List;

import vn.eshopvn.dao.ReviewDAO;
import vn.eshopvn.dao.impl.ReviewDAOImpl;
import vn.eshopvn.entity.Review;
import vn.eshopvn.service.ReviewService;

public class ReviewServiceImpl implements ReviewService{

	ReviewDAO revDAO= new ReviewDAOImpl();
	@Override
	public void insert(Review review) {
		revDAO.insert(review);
		
	}

	@Override
	public void deleteById(String reviewId) throws Exception {
		revDAO.deleteById(reviewId);
		
	}

	@Override
	public void update(Review review) {
		revDAO.update(review);
		
	}

	@Override
	public Review findById(String reviewId) {
		return revDAO.findById(reviewId);
	}

	@Override
	public List<Review> findAll() {
		return revDAO.findAll();
	}

	@Override
	public List<Review> findByProductId(String pid) {
		return revDAO.findByProductId(pid);
	}

	@Override
	public List<Review> findByUserId(String userid) {
		return revDAO.findByUserId(userid);
	}

	@Override
	public double averageRatingByProductId(String pid) {
		return revDAO.averageRatingByProductId(pid);
	}

}
