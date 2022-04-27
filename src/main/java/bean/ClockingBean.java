package bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DTO.ClockingAction;
import enums.ActionType;

@ManagedBean
@ViewScoped
public class ClockingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private LocalDateTime dateTime;
	private List<ClockingAction> lastActions;

	@PostConstruct
	public void init() {
		lastActions = new ArrayList<ClockingAction>();
		lastActions.add(new ClockingAction());
		lastActions.add(new ClockingAction());
		System.out.println("ClockingBean init");
		dateTime = LocalDateTime.now();
	}

	public void onPunchIn() {
		ClockingAction auxAction = lastActions.get(0);
		lastActions.set(1, auxAction);
		String timeStamp = LocalDateTime.now().format(dtf);
		ClockingAction newClockingAction = new ClockingAction(ActionType.PUNCH_IN, timeStamp);
		lastActions.set(0, newClockingAction);
	}

	public void onPunchOut() {
		ClockingAction auxAction = lastActions.get(0);
		lastActions.set(1, auxAction);
		String timeStamp = LocalDateTime.now().format(dtf);
		ClockingAction newClockingAction = new ClockingAction(ActionType.PUNCH_OUT, timeStamp);
		lastActions.set(0, newClockingAction);
	}

	// GETTERS AND SETTERS

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public List<ClockingAction> getLastActions() {
		return lastActions;
	}

	public void setLastActions(List<ClockingAction> lastActions) {
		this.lastActions = lastActions;
	}

}
