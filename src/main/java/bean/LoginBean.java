package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import model.Identity;
import service.IdentityService;
import util.SessionUtils;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Identity identity;
	private IdentityService identityService;

	@PostConstruct
	public void init() {
		identityService = new IdentityService();
		identity = new Identity();
		System.out.println(LoginBean.class.getName() + " init");
	}

	// logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login.xhtml?faces-redirect=true";
	}

	public String login() {
		Identity checkedIdentity = identityService.findByUsernamePassword(this.identity.getUsername(),
				DigestUtils.shaHex(identity.getPassword()));

		if (checkedIdentity != null) {
			identity = checkedIdentity;
			identity.setPassword("");
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("identityId", identity.getId());
			return "clocking.xhtml?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Username or password incorrect"));
			return "";
		}
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
