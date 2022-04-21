package DTO;

import java.time.LocalDateTime;

import enums.ActionType;

public class ClockingAction {

	private LocalDateTime timeStamp;
	private String action;

	public ClockingAction() {

	}

	public ClockingAction(ActionType actionType, LocalDateTime timeStamp) {
		if (actionType.equals(ActionType.PUNCH_IN)) {
			this.action = "Punch in";
		} else if (actionType.equals(ActionType.PUNCH_OUT)) {
			this.action = "Punch out";
		}
		this.timeStamp = timeStamp;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
