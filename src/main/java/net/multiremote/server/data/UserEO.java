package net.multiremote.server.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author gaetan
 */
@Entity
@Table(name = "t_user")
public class UserEO extends AbstractDataEO implements Serializable {

	public static final String FIND_BY_LOGIN
			= " SELECT u "
			+ " FROM UserEO u "
			+ " LEFT JOIN FETCH u.groups AS g "
			+ " LEFT JOIN FETCH g.roles AS r "
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

	@Size(min = 0, max = 45)
	@Column(name = "firstname", nullable = false)
	private String firstname;

	@Size(min = 0, max = 45)
	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Size(min = 0, max = 100)
	@Column(name = "email", nullable = true, unique = true)
	private String email;

	@Column(name = "date_inscr", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateInscr;

	@Column(name = "balance", nullable = false)
	private float balance;

	@Column(name = "rfid", nullable = true)
	private String rfid;

	@Column(name = "last_subscription_confirmation", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastSubscriptionConfirmation;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "auth_by_sql", nullable = false)
	private boolean authBySql;

	@Column(name = "phone", nullable = true)
	private String phone;

	@Column(name = "address", nullable = true)
	private String address;


	public UserEO() {
	}

	public UserEO(Integer userId) {
		this.userId = userId;
	}

	public UserEO(Integer userId, boolean authBySql, String login, String password, String firstname, String lastname, Date dateInscr, float balance, String rfid) {
		this.userId = userId;
		this.authBySql = authBySql;
		this.login = login;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.dateInscr = dateInscr;
		this.balance = balance;
		this.rfid = rfid;
		this.enabled = true;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isAuthBySql() {
		return authBySql;
	}

	public void setAuthBySql(Boolean authBySql) {
		this.authBySql = authBySql;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateInscr() {
		return dateInscr;
	}

	public void setDateInscr(Date dateInscr) {
		this.dateInscr = dateInscr;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getFirstLastName() {
		return firstname + " " + lastname;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userId != null ? userId.hashCode() : 0);
		return hash;
	}

	public Date getLastSubscriptionConfirmation() {
		return lastSubscriptionConfirmation;
	}

	public void setLastSubscriptionConfirmation(Date lastSubscriptionConfirmation) {
		this.lastSubscriptionConfirmation = lastSubscriptionConfirmation;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserEO)) {
			return false;
		}
		UserEO other = (UserEO) object;
		if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
			return false;
		}
		return true;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
