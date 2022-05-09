package bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import enums.ActionType;
import model.ClockingEntry;
import model.Employee;
import model.Identity;
import service.ClockingService;
import service.EmployeeService;
import service.IdentityService;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class ClockingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter DTF_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter DTF_TIME = DateTimeFormatter.ofPattern("HH:mm:ss");

	private List<ClockingEntry> lastClockingActions;

	private Employee identityEmployee;

	@EJB
	private EmployeeService employeeService;

	@EJB
	private ClockingService clockingService;

	@EJB
	private IdentityService identityService;

	@PostConstruct
	public void init() {
		employeeService = new EmployeeService();
		clockingService = new ClockingService();
		identityService = new IdentityService();
		identityEmployee = identityService.getLoggedUser().getEmployee();
		lastClockingActions = clockingService.getLastActions(identityEmployee.getId());
		System.out.println(ClockingBean.class.getName() + " init");
	}

	public void onPunchIn() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		ClockingEntry newClockingEntry = saveClockingEntry(ActionType.PUNCH_IN, currentDateTime);
		ClockingEntry auxClockingAction = lastClockingActions.get(0);
		lastClockingActions.set(1, auxClockingAction);
		lastClockingActions.set(0, newClockingEntry);
	}

	public void onPunchOut() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		ClockingEntry newClockingEntry = saveClockingEntry(ActionType.PUNCH_OUT, currentDateTime);
		ClockingEntry auxClockingAction = lastClockingActions.get(0);
		lastClockingActions.set(1, auxClockingAction);
		lastClockingActions.set(0, newClockingEntry);
	}

	private ClockingEntry saveClockingEntry(ActionType action, LocalDateTime dateTime) {
		String date = dateTime.toLocalDate().format(DTF_DATE);
		String time = dateTime.toLocalTime().format(DTF_TIME);
		ClockingEntry clockingEntry = new ClockingEntry(action.toString(), date, time, identityEmployee);
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

	public Employee getIdentityEmployee() {
		return identityEmployee;
	}

	public void setIdentityEmployee(Employee identityEmployee) {
		this.identityEmployee = identityEmployee;
	}

}
