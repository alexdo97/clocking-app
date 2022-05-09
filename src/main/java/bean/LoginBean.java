package bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import model.Identity;
import service.IdentityService;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class LoginBean {

	private Identity identity;
	private IdentityService userService;

	@PostConstruct
	public void init() {
		userService = new IdentityService();
		identity = new Identity();
		System.out.println(LoginBean.class.getName() + " init");
	}

	public String logOut() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().clear();

		return "login.xhtml";
	}

	public String login() {
		Identity checkedIdentity = userService.findByUsernamePassword(this.identity.getUsername(),
				this.identity.getPassword());

		if (checkedIdentity != null) {
			identity = checkedIdentity;
			identity.setPassword("");
			userService.setLoggedUser(identity);
			return "clocking.xhtml?faces-redirect=true";
		} else {
			this.showMessage("Username or password incorrect!");
		}
		return "";
	}

	public void showMessage(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage("Notice", msg);
		context.addMessage(null, message);
	}

	public void clearForm() {
		this.identity = new Identity();

	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

}
