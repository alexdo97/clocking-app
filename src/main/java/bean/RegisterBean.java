package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import model.Employee;
import model.Identity;
import service.EmployeeService;
import service.IdentityService;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class RegisterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Employee newEmployee;
	private Identity newIdentity;
	private String confirmPassword;

	private IdentityService identityService;
	private EmployeeService employeeService;

	@PostConstruct
	public void init() {
		newEmployee = new Employee();
		newIdentity = new Identity();
		identityService = new IdentityService();
		employeeService = new EmployeeService();
		System.out.println(RegisterBean.class.getName() + " init");
	}

	public String validateRegistration() {
		boolean validCredentials = validCredentials();
		if (!validCredentials) {
			return "";
		} else {
			newIdentity.setPassword(DigestUtils.shaHex(newIdentity.getPassword()));
			newEmployee.setIdentity(newIdentity);
			employeeService.save(newEmployee);
		}

		return "login.xhtml?faces-redirect=true";
	}

	private boolean validCredentials() {
		boolean validation = true;
		Identity duplicateIdentity = null;

		// validate password
		if (!newIdentity.getPassword().equals(confirmPassword)) {
			FacesContext.getCurrentInstance().addMessage("form:confirmPassword",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Passwords do not match"));
			validation = false;
		}

		// check if username is already being used
		duplicateIdentity = identityService.findByUsername(newIdentity.getUsername());
		if (duplicateIdentity != null) {
			FacesContext.getCurrentInstance().addMessage("form:username",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "This username is already being used"));
			validation = false;
		}

		// check if email address is already being used
		duplicateIdentity = identityService.findByEmail(newIdentity.getEmail());
		if (duplicateIdentity != null) {
			FacesContext.getCurrentInstance().addMessage("form:email",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "This email address is already being used"));
			validation = false;
		}

		return validation;
	}

	public void showMessage(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage("Notice", msg);
		context.addMessage(null, message);
	}

	public Employee getNewEmployee() {
		return newEmployee;
	}

	public void setNewEmployee(Employee newEmployee) {
		this.newEmployee = newEmployee;
	}

	public Identity getNewIdentity() {
		return newIdentity;
	}

	public void setNewIdentity(Identity newIdentity) {
		this.newIdentity = newIdentity;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
