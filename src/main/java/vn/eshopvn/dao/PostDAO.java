package vn.eshopvn.dao;

import java.util.List;

import vn.eshopvn.entity.Post;

public interface PostDAO {

	List<Post> findAll();
	
	void censorPost(String poid);
	void uncensorPost(String poid);

	List<Post> findAllUncensoredPost();

	List<Post> findAllCensoredPost();
	
}
