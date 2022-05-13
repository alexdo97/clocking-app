package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import model.Identity;
import service.IdentityService;
import util.SessionUtils;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class SessionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Identity identity;
	private IdentityService identityService;
	private String welcomeMessage;

	@PostConstruct
	public void init() {
		identityService = new IdentityService();
		System.out.println(SessionBean.class.getName() + " init");
	}

	// logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login.xhtml?faces-redirect=true";
	}

	// GETTERS AND SETTERS

	public String getWelcomeMessage() {
		if (SessionUtils.getIdentityId() != null) {
			identity = identityService.getById(Long.parseLong(SessionUtils.getIdentityId()));
			welcomeMessage = "Welcome, " + identity.getUsername() + "!";
		} else {
			welcomeMessage = "";
		}
		return welcomeMessage;
	}

	public void setWelcomeMessage(String welcomeMessage) {

		this.welcomeMessage = welcomeMessage;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
}
