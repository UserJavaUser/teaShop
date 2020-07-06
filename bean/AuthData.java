package by.htp.ishop.bean;

import java.util.Arrays;

public class AuthData {
	private String login;
	private byte[] salt;
	private byte[] hash;

	public AuthData() {
		
	}

	public AuthData(String login, byte[] salt, byte[] hash) {
		super();
		this.login = login;
		this.salt = salt;
		this.hash = hash;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public byte[] getHash() {
		return hash;
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(hash);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + Arrays.hashCode(salt);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthData other = (AuthData) obj;
		if (!Arrays.equals(hash, other.hash))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (!Arrays.equals(salt, other.salt))
			return false;
		return true;
	}

}
