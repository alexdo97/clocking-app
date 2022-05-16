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
	private boolean activeSession;

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

	public String getWelcomeMessage() {

		if (SessionUtils.getIdentityId() != null) {
			activeSession = true;
		} else {
			activeSession = false;
		}

		if (SessionUtils.getIdentityId() != null) {
			identity = identityService.getById(Long.parseLong(SessionUtils.getIdentityId()));
			welcomeMessage = "Welcome, " + identity.getUsername() + "!";
		} else {
			welcomeMessage = "";
		}
		return welcomeMessage;
	}

	// GETTERS AND SETTERS
	public void setWelcomeMessage(String welcomeMessage) {

		this.welcomeMessage = welcomeMessage;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public boolean isActiveSession() {
		return activeSession;
	}

	public void setActiveSession(boolean activeSession) {
		this.activeSession = activeSession;
	}
}
