package bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import DTO.ClockingAction;
import enums.ActionType;
import model.Student;

@Named(value = "indexBean")
@ViewScoped
public class IndexBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocalDateTime dateTime;
	public List<ClockingAction> lastActions;
	public String test = "TEST";

	@PostConstruct
	public void init() {
		lastActions = new ArrayList<ClockingAction>();
		lastActions.add(new ClockingAction());
		lastActions.add(new ClockingAction());
		System.out.println("IndexBean init");
		dateTime = LocalDateTime.now();
	}

	public void onPunchIn() {
		ClockingAction auxAction = lastActions.get(0);
		lastActions.set(1, auxAction);
		ClockingAction newClockingAction = new ClockingAction(ActionType.PUNCH_IN, dateTime);
		lastActions.set(0, newClockingAction);
	}

	public void onPunchOut() {
		ClockingAction auxAction = lastActions.get(0);
		lastActions.set(1, auxAction);
		ClockingAction newClockingAction = new ClockingAction(ActionType.PUNCH_OUT, dateTime);
		lastActions.set(0, newClockingAction);
	}

	// GETTERS AND SETTERS

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

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
