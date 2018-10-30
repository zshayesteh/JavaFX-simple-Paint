package javaFXpaintOO;

public class Person {
	private static String username;
	private String password;

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String un) {
		if (un != null)
			username = un;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password != null)
			if (password.length() > 3)
				this.password = password;
	}

	public Person(String u, String p) {
		setUsername(u);
		setPassword(p);
	}
}
