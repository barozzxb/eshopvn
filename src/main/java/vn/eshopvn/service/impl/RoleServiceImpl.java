package vn.eshopvn.service.impl;

import vn.eshopvn.dao.RoleDAO;
import vn.eshopvn.dao.impl.RoleDAOImpl;
import vn.eshopvn.entity.Role;
import vn.eshopvn.service.RoleService;

public class RoleServiceImpl implements RoleService{

	RoleDAO rDAO = new RoleDAOImpl();
	
	@Override
	public Role findById(int id) {
		return rDAO.findById(id);
	}

}
