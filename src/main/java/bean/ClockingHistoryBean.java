package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import enums.ActionType;
import enums.Role;
import model.ClockingEntry;
import model.Identity;
import service.ClockingService;
import service.IdentityService;
import util.EnumUtil;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class ClockingHistoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Identity identity;
	private boolean adminRole;
	private List<ClockingEntry> clockingEntryList = new ArrayList<ClockingEntry>();
	private String[] actionTypes;
	private String[] roles;

	@EJB
	private IdentityService identityService;
	@EJB
	private ClockingService clockingService;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@PostConstruct
	public void init() {
		identityService = new IdentityService();
		clockingService = new ClockingService();
		identity = sessionBean.getIdentity();
		actionTypes = EnumUtil.getNames(ActionType.class);
		roles = EnumUtil.getNames(Role.class);
		adminRole = identity.getRole().equals(Role.Admin.toString());
		if (adminRole) {
			clockingEntryList = clockingService.getAll();
		} else {
			clockingEntryList = clockingService.getAllByEmployeeId(identity.getId());
		}
		System.out.println(ClockingHistoryBean.class.getName() + " init");
	}

	// GETTERS AND SETTERS

	public List<ClockingEntry> getClockingEntryList() {
		return clockingEntryList;
	}

	public void setClockingEntryList(List<ClockingEntry> clockingEntryList) {
		this.clockingEntryList = clockingEntryList;
	}

	public boolean isAdminRole() {
		return adminRole;
	}

	public void setAdminRole(boolean adminRole) {
		this.adminRole = adminRole;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public String[] getActionTypes() {
		return actionTypes;
	}

	public void setActionTypes(String[] actionTypes) {
		this.actionTypes = actionTypes;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

}
