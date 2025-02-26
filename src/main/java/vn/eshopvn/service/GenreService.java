package vn.eshopvn.service;

import java.util.List;

import vn.eshopvn.entity.Genre;

public interface GenreService {

	int countGenres();

	int countProducts(String gid);

	boolean delete(String gid);

	boolean update(Genre genre);

	boolean insert(Genre genre);

	Genre findByName(String gname);

	Genre findById(String gid);

	List<Genre> findAll();

	public List<Genre> findAll(int page, int pagesize);
}
