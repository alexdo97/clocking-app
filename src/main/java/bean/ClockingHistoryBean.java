package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import enums.Profile;
import model.ClockingEntry;
import model.Identity;
import service.ClockingService;
import service.IdentityService;

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
		adminRole = Profile.Admin.getCode().equals(identity.getProfile());
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

}
