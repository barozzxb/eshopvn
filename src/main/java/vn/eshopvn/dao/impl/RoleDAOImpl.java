package vn.eshopvn.dao.impl;

import jakarta.persistence.EntityManager;
import vn.eshopvn.config.JPAConfig;
import vn.eshopvn.dao.RoleDAO;
import vn.eshopvn.entity.Role;

public class RoleDAOImpl implements RoleDAO{

	@Override
	public Role findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Role role = enma.find(Role.class, id);
		return role;
	}

}
