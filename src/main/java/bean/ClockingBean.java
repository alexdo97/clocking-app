package bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import enums.ActionType;
import model.ClockingEntry;
import model.Identity;
import service.ClockingService;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class ClockingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter DTF_DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	private static final DateTimeFormatter DTF_TIME = DateTimeFormatter.ofPattern("HH:mm:ss");

	private List<ClockingEntry> lastClockingActions;

	private Identity identity;

	private ClockingService clockingService;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@PostConstruct
	public void init() {
		clockingService = new ClockingService();
		identity = sessionBean.getIdentity();
		lastClockingActions = clockingService.getLastActions(identity.getId());
		System.out.println(ClockingBean.class.getName() + " init");
	}

	public void onPunchIn() {
		if (lastClockingActions.get(0) != null
				&& lastClockingActions.get(0).getPunchType().equals(ActionType.PUNCH_IN.toString())) {
			FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Warning:", "You have to Punch out your previous action before Punching In again"));
			return;
		}
		LocalDateTime currentDateTime = LocalDateTime.now();
		ClockingEntry newClockingEntry = saveClockingEntry(ActionType.PUNCH_IN, currentDateTime);
		ClockingEntry auxClockingAction = lastClockingActions.get(0);
		lastClockingActions.set(1, auxClockingAction);
		lastClockingActions.set(0, newClockingEntry);
	}

	public void onPunchOut() {
		if (lastClockingActions.get(0) != null
				&& lastClockingActions.get(0).getPunchType().equals(ActionType.PUNCH_OUT.toString())) {
			FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Warning:", "You have to Punch in before Punching Out again"));
			return;
		}
		LocalDateTime currentDateTime = LocalDateTime.now();
		ClockingEntry newClockingEntry = saveClockingEntry(ActionType.PUNCH_OUT, currentDateTime);
		ClockingEntry auxClockingAction = lastClockingActions.get(0);
		lastClockingActions.set(1, auxClockingAction);
		lastClockingActions.set(0, newClockingEntry);
	}

	private ClockingEntry saveClockingEntry(ActionType action, LocalDateTime dateTime) {
		String date = dateTime.toLocalDate().format(DTF_DATE);
		String time = dateTime.toLocalTime().format(DTF_TIME);
		ClockingEntry clockingEntry = new ClockingEntry(action.toString(), date, time, identity.getEmployee());
		clockingService.save(clockingEntry);
		return clockingEntry;
	}

	// GETTERS AND SETTERS

	public List<ClockingEntry> getLastClockingActions() {
		return lastClockingActions;
	}

	public void setLastClockingActions(List<ClockingEntry> lastClockingActions) {
		this.lastClockingActions = lastClockingActions;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

}
