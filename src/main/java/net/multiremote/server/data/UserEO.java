package net.multiremote.server.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author gaetan
 */
@Entity
@Table(name = "t_user")
public class UserEO extends AbstractDataEO<Integer> implements Serializable {

	public static final String FIND_BY_LOGIN
			= " SELECT u "
			+ " FROM UserEO u "
			+ " WHERE u.login = :" + UserEO.PARAM_LOGIN;

	public static final String PARAM_LOGIN = "login";
	public static final String PARAM_RFID = "rfid";
	public static final String PARAM_IDS = "ids";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private Integer userId;

	@Size(min = 1, max = 45)
	@Column(name = "login", nullable = false, unique = true)
	private String login;

	@Size(min = 0, max = 64)
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	private String roles;

	public UserEO() {
	}

	public UserEO(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@XmlAttribute(required = true)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@XmlAttribute(required = true)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@XmlAttribute(required = true)
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
