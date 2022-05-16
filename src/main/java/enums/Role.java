package enums;

public enum Role {
	Admin(1), User(2);

	private Integer code;

	Role(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

}
