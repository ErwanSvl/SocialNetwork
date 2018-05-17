/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */
package opinion;

public class Member {
	private String login;
	private String password;
	private String profile;

	public Member(String login, String password, String profile) {
		this.login = login;
		this.password = password;
		this.profile = profile;
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	@Override
	public String toString() {
		return this.login + " " + this.password + " : " + this.profile;
	}
	
	public boolean isEquals(String login) {
		return this.login.toLowerCase().trim().equals(login.toLowerCase().trim());
	}

	public String getLogin() {
		return login;
	}
}
