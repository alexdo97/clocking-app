package service;

import java.util.List;

import dao.IdentityDAO;
import model.Employee;
import model.Identity;

public class IdentityService {

	private IdentityDAO dao;

	public IdentityService() {
		this.dao = new IdentityDAO();
	}

	public Identity getById(Long id) {
		return dao.getById(id);
	}

	public List<Identity> getAll() {
		return dao.findAll();
	}

	public List<Identity> findByUsernamePassword(String userName, String password) {
		return dao.findByUsernamePassword(userName, password);
	}
}
