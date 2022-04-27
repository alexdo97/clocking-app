package DTO;

import enums.ActionType;

public class ClockingAction {

	private String timeStamp;
	private String action;

	public ClockingAction() {

	}

	public ClockingAction(ActionType actionType, String timeStamp) {
		if (actionType.equals(ActionType.PUNCH_IN)) {
			this.action = "Punch in";
		} else if (actionType.equals(ActionType.PUNCH_OUT)) {
			this.action = "Punch out";
		}
		this.timeStamp = timeStamp;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
