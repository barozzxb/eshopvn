package vn.eshopvn.service.impl;

import java.util.List;

import vn.eshopvn.dao.GenreDAO;
import vn.eshopvn.dao.impl.GenreDAOImpl;
import vn.eshopvn.entity.Genre;
import vn.eshopvn.service.GenreService;

public class GenreServiceImpl implements GenreService{

	GenreDAO genreDAO = new GenreDAOImpl();
	@Override
	public List<Genre> findAll() {
		return genreDAO.findAll();
	}

	@Override
	public Genre findById(String gid) {
		return genreDAO.findById(gid);
	}

	@Override
	public Genre findByName(String gname) {
		return genreDAO.findByName(gname);
	}

	@Override
	public boolean insert(Genre genre) {
		if(genreDAO.findById(genre.getGid()) != null) {
			return false;
		}
		try {
			genreDAO.insert(genre);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean update(Genre genre) {
		try {
			genreDAO.update(genre);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String gid){
		try {
			genreDAO.delete(gid);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int countProducts(String gid) {
		return 0;
	}

	@Override
	public int countGenres() {
		return genreDAO.countGenres();
	}

	@Override
	public List<Genre> findAll(int page, int pagesize) {
		return genreDAO.findAll(page, pagesize);
	}

}
