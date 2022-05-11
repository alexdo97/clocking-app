package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

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
			// print error msgs
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

		if (StringUtils.isBlank(newIdentity.getPassword())) {
			// error
			validation = false;
		} else if (!newIdentity.getPassword().equals(confirmPassword)) {
			// error
			validation = false;
		}

		Identity duplicateIdentity = null;
		duplicateIdentity = identityService.findByUsername(newIdentity.getUsername());
		if (duplicateIdentity != null) {
			// error
			validation = false;
		} else {
			duplicateIdentity = identityService.findByEmail(newIdentity.getEmail());
		}
		if (duplicateIdentity != null) {
			// error
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
