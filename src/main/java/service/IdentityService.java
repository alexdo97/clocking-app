package service;

import java.util.List;

import dao.IdentityDAO;
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

	public Identity findByUsernamePassword(String username, String password) {
		return dao.findByUsernamePassword(username, password);
	}

	public Identity findByUsername(String username) {
		return dao.findByUsername(username);
	}

	public Identity findByEmail(String email) {
		return dao.findByEmail(email);
	}

}
