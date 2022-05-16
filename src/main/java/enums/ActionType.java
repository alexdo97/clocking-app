package enums;

public enum ActionType {
	PUNCH_IN, PUNCH_OUT;

	public String[] getActionTypes() {
		String[] actionTypes = { ActionType.PUNCH_IN.toString(), ActionType.PUNCH_OUT.toString() };
		return actionTypes;
	}
}
