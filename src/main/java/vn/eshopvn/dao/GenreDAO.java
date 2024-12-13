package vn.eshopvn.dao;

import java.util.List;

import vn.eshopvn.entity.Genre;

public interface GenreDAO {

	int countGenres();

	int countProducts(String gid);

	Genre findByName(String gname);

	Genre findById(String gid);

	List<Genre> findAll();

	void delete(String gid);

	void update(Genre genre);

	void insert(Genre genre);

	List<Genre> findAll(int page, int pagesize);

}
