package vn.eshopvn.service.impl;

import java.util.List;

import vn.eshopvn.dao.FavoriteDAO;
import vn.eshopvn.dao.impl.FavoriteDAOImpl;
import vn.eshopvn.entity.Favorites;
import vn.eshopvn.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService{

	FavoriteDAO favDao=new FavoriteDAOImpl();
	@Override
	public List<Favorites> findAll() {
		return favDao.findAll();
	}

	@Override
	public Favorites findById(String fid) {
		return favDao.findById(fid);
	}

	@Override
	public boolean insert(Favorites favorite) {
		if (favDao.findById(favorite.getFid()) != null) {
			return false;
		}
		try {
			favDao.insert(favorite);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean delete(String fid) {
		try {
			favDao.delete(fid);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<Favorites> findByUserId(String userid) {
		return favDao.findByUserId(userid);
	}

}
