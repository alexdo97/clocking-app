package bean;

import java.util.List;

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

	private Identity loggedUser;
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
		List<Identity> list = userService.findByUsernamePassword(this.identity.getName(),
				DigestUtils.shaHex(this.identity.getPassword()));

		if (list.size() > 0) {
			loggedUser = list.get(0);
			identity.setPassword("");

			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("loggedUser", identity);

			return "clocking.xhtml";
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

	public Identity getLoggedUser() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Identity loggedUser = (Identity) externalContext.getSessionMap().get("loggedUser");

		return loggedUser;
	}

	public void setLoggedUser(Identity loggedUser) {
		this.loggedUser = loggedUser;
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
