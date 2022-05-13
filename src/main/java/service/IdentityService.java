package service;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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

	public Identity getLoggedUser() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Identity loggedUser = (Identity) externalContext.getSessionMap().get("loggedUser");

		return loggedUser;
	}
}
