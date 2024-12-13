package vn.eshopvn.service;

import java.util.List;

import vn.eshopvn.entity.Post;

public interface PostService {

	List<Post> findAll();
	
	List<Post> findAllUncensoredPost();

	List<Post> findAllCensoredPost();
}
