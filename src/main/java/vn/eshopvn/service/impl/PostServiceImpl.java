package vn.eshopvn.service.impl;

import java.util.List;

import vn.eshopvn.dao.PostDAO;
import vn.eshopvn.dao.impl.PostDAOImpl;
import vn.eshopvn.entity.Post;
import vn.eshopvn.service.PostService;

public class PostServiceImpl implements PostService{

	PostDAO poDAO = new PostDAOImpl();
	
	@Override
	public List<Post> findAll() {
		return poDAO.findAll();
	}

	@Override
	public List<Post> findAllUncensoredPost() {
		return poDAO.findAllUncensoredPost();
	}

	@Override
	public List<Post> findAllCensoredPost() {
		return poDAO.findAllCensoredPost();
	}

}
