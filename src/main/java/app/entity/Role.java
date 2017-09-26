package app.entity;

public enum Role {
	BOSS, ADMIN, OPERATOR, CHANNEL, ADFINANCIAL, CHFINANCIAL;

	public String toRoleString() {
		return User.ROLE_PREFIX + this;
	}

	public static Role parse(String s) {
		if (s != null) {
			for (Role r : Role.values()) {
				if (s.equalsIgnoreCase(r.toString()))
					return r;
			}
		}
		throw new IllegalArgumentException();
	}

}
