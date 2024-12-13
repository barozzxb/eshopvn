package vn.eshopvn.dao;

import java.util.List;

import vn.eshopvn.entity.Favorites;

public interface FavoriteDAO {
	List<Favorites> findAll(); 
	Favorites findById(String fid); 
	void insert(Favorites favorite);  
	void delete (String fid)throws Exception; 
	List<Favorites> findByUserId(String userid);
	

}