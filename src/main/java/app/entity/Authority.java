package app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 7935064899339631537L;

	@EmbeddedId
	private AuthorityKey key;

	@Transient
	private User user;

	@Transient
	private String authority;

	public Authority() {
	}

	public Authority(User user, Role role) {
		this.user = user;
		this.authority = role.toRoleString();
	}

	public Authority(Long userId, Role role) {
		this(userId, role.toRoleString());
	}

	public Authority(Long userId, String authority) {
		key = new AuthorityKey(userId, authority);
	}

	@PrePersist
	private void generateUUID() {
		if (key == null)
			key = new AuthorityKey(user.getId(), authority);
	}

	@Override
	public String getAuthority() {
		return key != null ? key.getAuthority() : authority;
	}

	public AuthorityKey getKey() {
		return key;
	}

	public void setKey(AuthorityKey key) {
		this.key = key;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this == other)
			return true;
		if (!(other instanceof Authority))
			return false;
		Authority auth = (Authority) other;
		if (key != null)
			return key.equals(auth.key);
		else if (authority != null)
			return authority.equals(auth.authority);
		else
			return false;
	}

	@Override
	public int hashCode() {
		if (key != null)
			return key.hashCode();
		else if (authority != null)
			return authority.hashCode();
		else
			return 0;
	}

	@Override
	public String toString() {
		return getAuthority();
	}

	public static class AuthorityKey implements Serializable {

		private static final long serialVersionUID = -1504878627101873334L;

		@Column(name = "user_id", insertable = false, updatable = false)
		private Long userId;

		@Column(insertable = false, updatable = false)
		private String authority;

		public AuthorityKey() {
		}

		public AuthorityKey(Long userId, String authority) {
			this.userId = userId;
			this.authority = authority;
		}

		@Override
		public boolean equals(Object other) {
			if (other == null)
				return false;
			if (this == other)
				return true;
			if (!(other instanceof AuthorityKey))
				return false;
			AuthorityKey ak = (AuthorityKey) other;
			return userId.equals(ak.userId) && authority.equals(ak.authority);
		}

		@Override
		public int hashCode() {
			int result = userId.hashCode();
			result += result * 31 + authority.hashCode();
			return result;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getAuthority() {
			return authority;
		}

		public void setAuthority(String authority) {
			this.authority = authority;
		}

	}

}
